package io.team21.userservice.model;

public class RoleModel {
    private int id;

    private String name;

    public RoleModel() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

