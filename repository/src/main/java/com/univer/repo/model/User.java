package com.univer.repo.model;

import org.springframework.util.DigestUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;
    private String bigNumber;

    public User() {
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getBigNumber() {
        return bigNumber;
    }

    public User setBigNumber(String bigNumber) {
        this.bigNumber = bigNumber;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getPassword().equals(user.getPassword())) return false;
        return getBigNumber().equals(user.getBigNumber());
    }


    public String hashCoder() {
        String result = password + bigNumber;
        final byte[] md5Digest = DigestUtils.md5Digest(result.getBytes());
        return Arrays.toString(md5Digest);
    }
}
