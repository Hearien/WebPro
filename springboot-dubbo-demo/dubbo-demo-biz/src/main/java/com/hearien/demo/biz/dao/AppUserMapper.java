package com.hearien.demo.biz.dao;

import com.hearien.demo.biz.model.AppUser;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface AppUserMapper extends Mapper<AppUser> {
}