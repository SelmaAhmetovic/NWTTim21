package io.team21.userservice.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "USERROLE")
public class UserRole {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "USERID")
    private int userId;

    @Column(name = "ROLEID")
    private int roleId;

    protected UserRole() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getRoleId() {
        return roleId;
    }
}