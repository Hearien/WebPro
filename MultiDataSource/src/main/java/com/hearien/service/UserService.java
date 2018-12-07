package com.hearien.service;

import com.hearien.dao.db1.mapper.Users1Mapper;
import com.hearien.dao.db1.model.Users1;
import com.hearien.dao.db2.mapper.Users2Mapper;
import com.hearien.dao.db2.model.Users2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserService
 * @Author WangHaiyang
 * @Date 2018/12/6 17:01
 **/
@Service
public class UserService {

    @Autowired
    private Users1Mapper mapper1;

    @Autowired
    private Users2Mapper mapper2;

    public boolean addDB1(Users1 users1){
        return mapper1.insert(users1)>0?true:false;
    }

    public boolean addDB2(Users2 users2){
        return mapper2.insert(users2)>0?true:false;
    }

    @Transactional
    public void add(){
        Users1 users1 = new Users1();
        users1.setId(10l);
        users1.setNickName("xiaodong");
        users1.setPassword("123123");
        users1.setUserSex("w");
        users1.setNickName("jack");
        mapper1.insert(users1);
        Users2 users2 = new Users2(10l,null,"1231312","m","haha");
        mapper2.insertSelective(users2);
    }

    public Users1 getFromDB1(){
        return mapper1.selectByPrimaryKey(1l);
    }

    public Users2 getFromDB2(){
        return mapper2.selectByPrimaryKey(1l);
    }
}
