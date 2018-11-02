package com.hearien.demo.controller;

import com.github.pagehelper.PageInfo;
import com.hearien.demo.user.model.AppUser;
import com.hearien.demo.user.service.AppUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Author WangHaiyang
 * @Date 2018/10/26 15:12
 **/
@Api(tags="Swagger2测试类")
@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private AppUserService appUserService;

    @ApiOperation(value = "获取列表", notes = "获取列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一标识", dataType = "String")
    })
    @GetMapping("list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @Cacheable(value = "redisuser")
    public String getList(String id){
        PageInfo<AppUser> pageInfo = appUserService.getList(1,5);
        return pageInfo.toString();
    }
}
