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
    public String callResponseByUserId(Long id) {
        final User one = userRepository.findOne(id);
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
    public Boolean callResponseByHash(Long id, String hash) {
        final User user = userRepository.findOne(id);
        return String.valueOf(user.hashCode()).equals(hash);
    }
}
