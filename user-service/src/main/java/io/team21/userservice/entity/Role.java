package io.team21.userservice.entity;

import java.util.List;

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

    protected Role() {
    }

    public void setId(int id) {
          this.id = id;
    }

    public void setName(int id) {
              this.id = id;
    }

    public int getId() {
           return id;
    }

    public String getName() {
         return name;
    }
}
