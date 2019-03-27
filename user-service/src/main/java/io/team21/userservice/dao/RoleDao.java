package io.team21.userservice.dao;

import io.team21.userservice.entity.Role;

import javax.persistence.*;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {
}




