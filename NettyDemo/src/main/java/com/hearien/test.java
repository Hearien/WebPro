package com.hearien;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @ClassName test
 * @Author WangHaiyang
 * @Date 2018/11/28 16:31
 **/
public class test {
    public static void main(String[] args) {
        String s = "MTIzQHF3ZT09Lj4=";
        byte[] b = Base64.getDecoder().decode(s.getBytes());
        String pwd = null;
        try {
            pwd = new String(b,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(pwd);
    }
}
