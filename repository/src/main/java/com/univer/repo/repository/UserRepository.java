package com.univer.repo.repository;


import com.univer.repo.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findFirstByName(String name);
}
