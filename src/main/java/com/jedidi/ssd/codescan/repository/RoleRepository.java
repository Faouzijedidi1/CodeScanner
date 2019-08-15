package com.jedidi.ssd.codescan.repository;

/*
 * Code Made by Faouzi Jedidi
 * S1719017
 *
 */

import com.jedidi.ssd.codescan.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    public Role findByRole(String role);
}

