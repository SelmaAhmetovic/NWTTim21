package io.team21.userservice.model;

public class UserRoleModel {
    private int id;

    private int userId;

    private int roleId;

    public UserRoleModel() {
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

