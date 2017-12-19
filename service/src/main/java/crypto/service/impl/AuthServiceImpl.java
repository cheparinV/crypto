package crypto.service.impl;

import crypto.model.User;
import crypto.repository.UserRepository;
import crypto.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String callResponseByUserId(String name) {
        final User one = userRepository.findFirstByName(name);
        if (one != null) {
            final long bigNumber = new Random().nextLong();
            userRepository.save(
                    one.setBigNumber(String.valueOf(bigNumber))
            );
            return String.valueOf(bigNumber);
        }
        return "";
    }

    @Override
    public Boolean callResponseByHash(String name, String hash) {
        final User user = userRepository.findFirstByName(name);
        return user.hashCoder().equals(hash);
    }

    public User newUser(String name, String password) {
        return userRepository.save(
                new User().setName(name).setPassword(password)
        );
    }
}
