package com.hearien.demo.controller;

import com.github.pagehelper.PageInfo;
import com.hearien.demo.user.model.AppUser;
import com.hearien.demo.user.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Author WangHaiyang
 * @Date 2018/10/26 15:12
 **/
@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private AppUserService appUserService;

    @GetMapping("list")
    public String getList(){
        PageInfo<AppUser> pageInfo = appUserService.getList(1,5);
        return pageInfo.toString();
    }
}
