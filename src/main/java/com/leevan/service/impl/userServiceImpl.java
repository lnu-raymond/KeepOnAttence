package com.leevan.service.impl;

import com.leevan.Utils.JwtUtil;
import com.leevan.Utils.RedisUtil;
import com.leevan.mapper.RFIDCardMapper;
import com.leevan.mapper.RoleMenuMapper;
import com.leevan.mapper.UserRoleMapper;
import com.leevan.pojo.ResultCodeEnum;
import com.leevan.pojo.menu;
import com.leevan.service.menuService;
import com.leevan.service.rfidCardService;
import com.leevan.vo.userVo;
import com.leevan.Request.userQueryRequest;
import com.leevan.mapper.UserMapper;
import com.leevan.pojo.ServiceRes;
import com.leevan.pojo.user;
import com.leevan.service.userService;
import com.leevan.vo.pageBeanVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.service
 * @className:      userService
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/24 16:24
 * @version:    1.0
 */ 
@Service
public class userServiceImpl implements userService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private menuService menuService;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private RFIDCardMapper rfidCardMapper;
//    private rfidCardService rfidCardService;





//    实现user表更新
    public Integer save(userQueryRequest userQueryRequest)
    {
//        判断用户id,不存在就新增
        if(userQueryRequest.getId()==null)
        {
            if(userQueryRequest.getEpc()!=null)
            {

                rfidCardMapper.updateBindStatusByEpc(userQueryRequest.getEpc(), rfidCardServiceImpl.Bound);
            }
          return userMapper.save(userQueryRequest);
        }
        else
        {
            if (userQueryRequest.getEpc()!=null)
            {
                rfidCardMapper.updateBindStatusByEpc(userQueryRequest.getEpc(), rfidCardServiceImpl.Bound);
            }
            // 存在就更新
            return  userMapper.update(userQueryRequest);
        }


    }

    public List<userVo> findAll() {
//       返回用户id,userName,nickName,identity,phonenumber等 排除passWord
        List<user> allUser = userMapper.findAll();
//        返回List<userVo>集合
        List<userVo> userVoList = new ArrayList<>();
//        封装成userVo
        for(user user: allUser)
        {
            userVo userVo = new userVo();
            BeanUtils.copyProperties(user,userVo);
            userVoList.add(userVo);
        }
        return userVoList;
    }

    //这是实现校验用户是否存在业务
    @Override
    public ServiceRes CheckUser(userQueryRequest userQueryRequest) {

        if(userQueryRequest.getUserName()==null)
            return new ServiceRes(-1,"用户名为空");

//        根据用户identity唯一标识去sys_user_role找对应的roleId,再根据roleId到sys_role_menu查找角色对应的menu或者具有的权限
        user userOfDao = userMapper.findUser(userQueryRequest);

        if(userOfDao!=null&&userOfDao.getUserName()!=null) {

            if (Objects.equals(userQueryRequest.getUserName(), userOfDao.getUserName()) != false) {

                if (Objects.equals(userQueryRequest.getPassWord(), userOfDao.getPassWord()) != false)
                {

                    String token = JwtUtil.getToken(userOfDao.getId(), userOfDao.getUserName());
//                    获取到用户唯一权限标识
                    String identity = userOfDao.getIdentity();
//                    根据唯一权限标识查找sys_user_role找到roleId
                    Integer roleId = userRoleMapper.selectRoleIdByIdentity(identity);
//                    根据roleId查找sys_role_menu表的menuId
                    List<Integer> menuIds = roleMenuMapper.findRoleMenuByRoleId(roleId);
//                    根据menuIds返回角色对应的权限或者接口
//                    根据menuIds遍历所有对应的menu组成集合
//                    再递归调用
                    List<menu> roleMenus = getRoleMenus(menuIds);
//                    递归调用获取子菜单,返回包含子菜单的全部菜单
                    List<menu> roleParentMenu = menuService.findAllMenus(roleMenus);
                    
                    userVo userVo = new userVo();

                    userVo.setToken(token);

                    userVo.setMenus(roleParentMenu);
                    BeanUtils.copyProperties(userOfDao,userVo);
                    return new ServiceRes(ResultCodeEnum.SUCCESS.getCode(),ResultCodeEnum.SUCCESS.getMessage(),token,userVo);
                }else
                {
                    return new ServiceRes(ResultCodeEnum.PASSWORD_ERROR.getCode(),ResultCodeEnum.PASSWORD_ERROR.getMessage());
                }
            }else
            {
                return new ServiceRes(ResultCodeEnum.ACCOUNT_ERROR.getCode(),ResultCodeEnum.ACCOUNT_ERROR.getMessage());
            }
        }
        return new ServiceRes(ResultCodeEnum.LOGIN_MOBLE_ERROR.getCode(),ResultCodeEnum.LOGIN_MOBLE_ERROR.getMessage());

    }

    @Override
    public userVo findUserById(userQueryRequest queryRequest)
    {
        userVo userVo = new userVo();
        BeanUtils.copyProperties(userMapper.findUser(queryRequest),userVo);
        return userVo;
    }

    @Override
    public pageBeanVo<user> getPage(Integer pageNum, Integer pageSize) {
//        Integer currentPage=pageNum;
        Integer countPages = userMapper.countPages();
        pageNum=(pageNum-1)*pageSize;
        List<user> recordList = userMapper.findAllWithPage(pageNum,pageSize);
        pageBeanVo<user> userpageBean = new pageBeanVo<>();
//        userpageBean.setCurrentPage(currentPage);
//        userpageBean.setPageSize(pageSize);
        userpageBean.setRecordList(recordList);
        userpageBean.setTotalPage(countPages);
        return userpageBean;
    }

    @Override
    public Integer del(Integer userId) {
        return userMapper.delete(userId);
    }

    @Override
    public Integer batchDel(List<Integer> userIdList) {
        Integer count=0;
        for (Integer userid: userIdList)
        {
            count =userMapper.delete(userid)+1;
        }
        return count;
    }

    @Override
    public pageBeanVo getPageByCondition(userQueryRequest userQueryRequest) {
        Integer TotalPage  = userMapper.countPagesByCondition(userQueryRequest);

        Integer currentPage = userQueryRequest.getCurrentPage();
        Integer pageNum=(currentPage-1)*userQueryRequest.getPageSize();
        userQueryRequest.setPageNum(pageNum);
        pageBeanVo<user> userpageBean = new pageBeanVo<>();
        List<user> allWithPageAndCondition = userMapper.findAllWithPageAndCondition(userQueryRequest);
        userpageBean.setRecordList(allWithPageAndCondition);
        userpageBean.setTotalPage(TotalPage);
        return userpageBean;
    }


    public List<menu> getRoleMenus(List<Integer> menusId)
    {
        List<menu> roleMenus = new ArrayList<>();
        for(Integer menuId : menusId)
        {
            menu one = menuService.findMenuById(menuId);
            roleMenus.add(one);
        }
        return roleMenus;

    }



}