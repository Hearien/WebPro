package com.hearien.demo.controller;

import com.hearien.demo.user.model.User;
import com.hearien.demo.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags="HELLO用户")
@RestController
@RequestMapping("api/user")
public class HelloWorldRestController {
  
    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work
  
      
    //-------------------Retrieve All Users--------------------------------------------------------

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @GetMapping("user")
    public List<User> listAllUsers() {
        List<User> users = userService.getList(1,10).getList();
        if(users.isEmpty()){
            return null;
        }
        return users;
    }
  
}