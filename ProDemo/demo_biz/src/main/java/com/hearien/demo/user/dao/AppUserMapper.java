package com.hearien.demo.user.dao;

import com.hearien.demo.user.model.AppUser;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface AppUserMapper extends Mapper<AppUser> {
}