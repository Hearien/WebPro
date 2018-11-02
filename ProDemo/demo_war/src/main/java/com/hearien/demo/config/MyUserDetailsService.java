package com.hearien.demo.config;

import com.hearien.demo.user.model.User;
import com.hearien.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by lxg
 * on 2017/2/20.
 */
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * 根据用户名获取登录用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userTmp = new User();
        userTmp.setUsername(username);
        User user = userService.selectOne(userTmp);

        if(user == null){
             throw new UsernameNotFoundException("用户名："+ username + "不存在！");
        }
        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
//        Iterator<Role> iterator =  user.getList().iterator();
//        while (iterator.hasNext()){
//            collection.add(new SimpleGrantedAuthority(iterator.next().getRole_name()));
//        }
        collection.add(new SimpleGrantedAuthority("ROLE_CLIENT"));

        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),collection);
    }
}
