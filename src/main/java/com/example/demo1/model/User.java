package com.example.demo1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="user")
public class User {

    @Id
    @Column(name = "user_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message="fullName must be specified.")
    @Size(min = 2, max = 50, message = "fullName must be between 2 and 50.")
    private String fullName;


    @Column(unique = true)
    @NotNull(message="username must be specified.")
    @Size(min = 2, max = 50, message = "username must be between 2 and 50.")
    private String username;

    @Column
    private  int status;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Address> address = new ArrayList<>();

    public User() {

    }

    public User(Long id, String fullName, String username, int status) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return status == user.status &&
                Objects.equals(id, user.id) &&
                Objects.equals(fullName, user.fullName) &&
                Objects.equals(username, user.username) &&
                Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fullName, username, status, address);
    }
}
