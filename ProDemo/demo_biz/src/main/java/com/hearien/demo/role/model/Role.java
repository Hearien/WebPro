package com.hearien.demo.role.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by lxg
 * on 2017/2/20.
 */
@Entity
@Table(name = "role")
@ApiModel
public class Role implements Serializable{
    @Id
    @Column(name = "id")
    @ApiModelProperty(value="id", required = true, dataType = "int")
    private int id;
    @Column(name = "role_name")
    @ApiModelProperty(value="较色名", required = true, dataType = "String")
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
