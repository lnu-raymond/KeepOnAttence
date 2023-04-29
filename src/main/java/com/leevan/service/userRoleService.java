package com.leevan.service;

import com.leevan.pojo.userRole;

import java.util.List;

public interface userRoleService {


    Integer selectRoleIdByIdentity(String identity);

    Integer insert(userRole userRole);

    Integer delete(userRole userRole);

    List<userRole> findAll();
}
