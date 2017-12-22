package com.univer.rest.repo;

import com.univer.rest.repo.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 * @version $Id$
 * @since 1.0
 */
public interface UserRepo extends CrudRepository<User, Long> {

    User findFirstByLogin(String login);
}
