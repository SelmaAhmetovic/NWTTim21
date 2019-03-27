package io.team21.userservice.dao;

import io.team21.userservice.entity.User;

import javax.persistence.*;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
}
