package io.team21.userservice.dao;

import io.team21.userservice.entity.Role;
import io.team21.userservice.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Integer> {

    @Transactional
    @Query("DELETE FROM UserRole WHERE roleId = ?1")
    void deleteUserRoleByRoleId(int roleId);

}






