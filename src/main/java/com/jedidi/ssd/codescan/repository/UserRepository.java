package com.jedidi.ssd.codescan.repository;

/*
 * Code Made by Faouzi Jedidi
 * S1719017
 *
 */

import com.jedidi.ssd.codescan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);
}
