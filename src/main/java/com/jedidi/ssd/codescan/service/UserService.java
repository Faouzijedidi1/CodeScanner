package com.jedidi.ssd.codescan.service;

/*
 * Code Made by Faouzi Jedidi
 * S1719017
 *
 */

import com.jedidi.ssd.codescan.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public static final String USER_STATUS= "VERIFIED";


    public void saveUser(User user);


    public boolean isUserAlreadyPresent(User user);

}
