package io.team21.userservice.entity;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "ROLE")
@ApiModel(description = "All details about the Role. ")
public class Role {
    @Id
    @Column(name = "ID")
    @ApiModelProperty(notes = "The database generated role ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ApiModelProperty(notes = "The role name")
    @NotBlank
    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role() {
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

    @Override
    public String toString() {
        return "Role [id=" + id + ", name=" + name + "]";
    }
}
