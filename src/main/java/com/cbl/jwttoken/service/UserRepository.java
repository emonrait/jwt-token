package com.cbl.jwttoken.service;

import com.cbl.jwttoken.dto.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public User findUserByUserName(String userName, String password) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        return user;
    }
}
