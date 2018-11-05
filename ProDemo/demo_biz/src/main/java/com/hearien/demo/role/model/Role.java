package com.hearien.demo.role.model;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by lxg
 * on 2017/2/20.
 */
@Entity
@Table(name = "role")
public class Role implements Serializable{
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "role_name")
    private String role_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
