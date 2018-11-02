package com.hearien.demo.user.service;

import com.hearien.demo.common.BaseService;
import com.hearien.demo.user.dao.UserMapper;
import com.hearien.demo.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName AppUserService
 * @Author WangHaiyang
 * @Date 2018/10/26 14:18
 **/
@Service
public class UserService extends BaseService<UserMapper,User> {

}
