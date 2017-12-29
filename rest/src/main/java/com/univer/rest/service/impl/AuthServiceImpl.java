package com.univer.rest.service.impl;

import com.univer.rest.repo.UserRepo;
import com.univer.rest.repo.model.User;
import com.univer.rest.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Random;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 * @version $Id$
 * @since 1.0
 */
@Service
public class AuthServiceImpl implements AuthService {

    private UserRepo userRepo;

    @Autowired
    public AuthServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User register(String login, String password) {
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        return userRepo.save(
                new User(login, password)
        );
    }

    @Override
    public String auth(String login) {
        final User user = userRepo.findFirstByLogin(login);
        if (user == null) {
            return "empty";
        }
        final String number = String.valueOf(new Random().nextLong());
        user.setBigNumber(number);
        userRepo.save(user);
        return number;
    }

    @Override
    public String authStep(String login, String hash) {
        final User user = userRepo.findFirstByLogin(login);
        final String hashUser = user.getPassword() + user.getBigNumber();

        final String result = DigestUtils.md5DigestAsHex(hashUser.getBytes());

        if (result.equals(hash)) {
            return "OK";
        }
        return "BAD";
    }
}
