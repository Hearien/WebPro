package com.imooc.springboot.dubbo.demo;

import com.hearien.demo.biz.model.AppUser;

import java.util.List;

public interface DemoService {
    String sayHello(String name);

    List<AppUser> getUserList();
}