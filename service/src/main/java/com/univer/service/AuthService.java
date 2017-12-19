package com.univer.service;


import com.univer.repo.model.User;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public interface AuthService {

    String callResponseByUserId(String name);

    Boolean callResponseByHash(String name, String hash);

    User newUser(String name, String password);


}
