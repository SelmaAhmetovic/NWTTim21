package io.team21.userservice.dao;

import io.team21.userservice.entity.User;

import javax.persistence.*;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id = ?1")
    void deleteUserById(int id);
    
    @Transactional
    @Query("SELECT u from User u WHERE u.userName = :userName")
    User getUserByUserName(@Param("userName") String userName);
}
