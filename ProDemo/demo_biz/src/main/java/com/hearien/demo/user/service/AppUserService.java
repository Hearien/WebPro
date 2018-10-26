package com.hearien.demo.user.service;

import com.hearien.demo.common.BaseService;
import com.hearien.demo.user.dao.AppUserMapper;
import com.hearien.demo.user.model.AppUser;
import org.springframework.stereotype.Service;

/**
 * @ClassName AppUserService
 * @Author WangHaiyang
 * @Date 2018/10/26 14:18
 **/
@Service
public class AppUserService extends BaseService<AppUserMapper,AppUser> {
}
