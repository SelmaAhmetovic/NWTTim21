package io.team21.userservice.dao;

import io.team21.userservice.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

    @Transactional
    @Query(value = "SELECT * from Role where Role.name in ?1",
            nativeQuery = true)
    List<Role> findByName(List<String> roleName);

}




