package com.univer.rest.service;

import com.univer.rest.repo.model.User;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 * @version $Id$
 * @since 1.0
 */
public interface AuthService {

    public User register(String login, String password);

    public String auth(String login);

    public String authStep(String login, String hash);
}
