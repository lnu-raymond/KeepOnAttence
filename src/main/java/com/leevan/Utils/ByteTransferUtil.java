package com.leevan.Utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.Utils
 * @className:      ByteTransferUtil
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/14 11:30
 * @version:    1.0
 */ 

public class ByteTransferUtil {

    public String  getBodyTxt(HttpServletRequest request) throws IOException {
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while ((str = br.readLine()) != null) {
            wholeStr += str;
        }
        return wholeStr;
    }
}