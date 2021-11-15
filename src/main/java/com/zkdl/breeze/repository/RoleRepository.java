package com.zkdl.breeze.repository;


import com.zkdl.breeze.model.ERole;
import com.zkdl.breeze.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}