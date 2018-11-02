package com.hearien.demo.user.model;

import com.hearien.demo.role.model.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by lxg
 * on 2017/2/20.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
//    private List<Role> list = new ArrayList<Role>();

    /*public List<Role> getList() {
        return list;
    }

    public void setList(List<Role> list) {
        this.list = list;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
