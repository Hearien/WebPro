package com.imooc.springboot.dubbo.demo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.hearien.demo.biz.dao.AppUserMapper;
import com.hearien.demo.biz.model.AppUser;
import com.imooc.springboot.dubbo.demo.DemoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private AppUserMapper userMapper;

    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot)";
    }

    @Override
    public List<AppUser> getUserList() {
        return userMapper.selectAll();
    }

}