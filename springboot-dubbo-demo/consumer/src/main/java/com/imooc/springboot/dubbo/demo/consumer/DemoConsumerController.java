package com.imooc.springboot.dubbo.demo.consumer;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.dubbo.config.annotation.Reference;
import com.hearien.demo.biz.model.AppUser;
import com.imooc.springboot.dubbo.demo.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoConsumerController {

    @Reference
    private DemoService demoService;

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        return demoService.sayHello(name);
    }

    @RequestMapping("/list")
    public String list() {
        List<AppUser> list = demoService.getUserList();
        JSONArray json = new JSONArray();
        json.addAll(list);
        return json.toString();
    }

}