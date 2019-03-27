package io.team21.userservice.dao;

import io.team21.userservice.entity.UserRole;

import javax.persistence.*;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Integer> {
}
