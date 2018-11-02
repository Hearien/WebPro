package com.hearien.demo.user.dao;

import com.hearien.demo.user.model.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName UserMapper
 * @Author WangHaiyang
 * @Date 2018/11/2 15:12
 **/
@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {
}
