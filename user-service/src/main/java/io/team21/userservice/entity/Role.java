package io.team21.userservice.entity;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

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
}
