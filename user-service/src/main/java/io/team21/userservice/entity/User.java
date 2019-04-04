package io.team21.userservice.entity;
import javax.persistence.*;
import java.util.Collection;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "USER")
@ApiModel(description = "All details about the User. ")
public class User {
    @Id
    @Column(name = "ID")
    @ApiModelProperty(notes = "The database generated user ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "FIRSTNAME")
    @ApiModelProperty(notes = "The user first name")
    private String firstName;

    @Column(name = "LASTNAME")
    @ApiModelProperty(notes = "The user last name")
    private String lastName;

    @Column(name = "USERNAME")
    @ApiModelProperty(notes = "The user username")
    private String userName;

    @Column(name = "PASSWORD")
    @ApiModelProperty(notes = "The user password")
    private String password;

    @ManyToMany
    @JoinTable(name = "userrole",
            joinColumns = { @JoinColumn(name = "userid", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "roleid", referencedColumnName = "id") })
    private Collection<Role> roles;

    public User() {
    }

    public void setId(int id) {
          this.id = id;
    }

    public void setFirstName(String firstName) {
          this.firstName = firstName;
    }

    public void setLastName(String lastName) {
          this.lastName = lastName;
    }

    public void setUserName(String userName) {
          this.userName = userName;
    }

    public void setPassword(String password) {
          this.password = password;
    }

    public int getId() {
         return id;
    }

    public String getFirstName() {
         return firstName;
    }
    public String getLastName() {
         return lastName;
    }

    public String getUserName() {
         return userName;
    }

    public String getPassword() {
         return password;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName +
                "]";
    }
}
