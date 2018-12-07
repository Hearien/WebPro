package com.hearien.controller;

import com.hearien.dao.db1.model.Users1;
import com.hearien.dao.db2.model.Users2;
import com.hearien.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName UserController
 * @Author WangHaiyang
 * @Date 2018/12/6 16:46
 **/
@Api(tags="Swagger2测试类")
@RestController
@RequestMapping("/api/test")
public class UserController {


    @Autowired
    private UserService userService;

    @ApiOperation(value = "测试", notes = "测试")
    @PostMapping("/test")
    public String test() {
        return "hello world";
    }

    @ApiOperation(value = "数据源1添加用户", notes = "数据源1添加用户")
    @PostMapping("/addDB1")
    public boolean addDB1(Users1 users1) {
        return userService.addDB1(users1);
    }

    @ApiOperation(value = "数据源2添加用户", notes = "数据源2添加用户")
    @PostMapping("/addDB2")
    public boolean addDB2(Users2 users2) {
        return userService.addDB2(users2);
    }

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @PostMapping("/add")
    public boolean add() {
        userService.add();
        return true;
    }

    @ApiOperation(value = "数据源1获取用户", notes = "数据源1获取用户")
    @PostMapping("/get1")
    public Users1 getFromDB1() {
        return userService.getFromDB1();
    }

    @ApiOperation(value = "数据源2获取用户", notes = "数据源2获取用户")
    @PostMapping("/get2")
    public Users2 getFromDB2() {
        return userService.getFromDB2();
    }


    /*@ApiOperation(value = "数据源1获取列表", notes = "数据源1获取列表")
    @PostMapping("/getUsers")
    public List<Users> getUsers() {
        List<Users> users=user1Mapper.selectByPrimaryKey();
        return users;
    }

    @ApiOperation(value = "数据源2获取用户", notes = "数据源2获取用户")
    @PostMapping("/getUser")
    public UserEntity getUser(Long id) {
        UserEntity user=user2Mapper.getOne(id);
        return user;
    }*/

    /*@ApiOperation(value = "数据源2添加用户", notes = "数据源2添加用户")
    @PostMapping("/add2")
    public void save2(UserEntity user) {
        userService.insert2(user);
    }

    @ApiOperation(value = "数据源1添加用户", notes = "数据源1添加用户")
    @PostMapping("/add1")
    public void save1(UserEntity user) {
        userService.insert1(user);
    }

    @ApiOperation(value = "12添加用户", notes = "12添加用户")
    @PostMapping("/add")
    public void save(UserEntity user) {
        userService.insert2(user);
    }

    @ApiOperation(value = "数据源2更新用户", notes = "数据源1更新用户")
    @PostMapping(value="update")
    public void update(UserEntity user) {
        user2Mapper.update(user);
    }

    @ApiOperation(value = "数据源1删除用户", notes = "数据源1删除用户")
    @PostMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        user1Mapper.delete(id);
    }*/

}
