package com.univer.rest.resource;

import com.univer.algorithm.RSA;
import com.univer.rest.repo.UserRepo;
import com.univer.rest.service.AuthService;
import com.univer.rest.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 * @version $Id$
 * @since 1.0
 */
@RestController
@RequestMapping("/crypto/auth")
public class AuthResource {


    private RSA rsa;
    private UserRepo repo;
    private AuthService authService;
    MessageService service = new MessageService();


    @Autowired
    public AuthResource(UserRepo repo, AuthService authServiceImpl) {
        this.repo = repo;
        this.authService = authServiceImpl;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(
            @RequestParam("login") String login,
            @RequestParam("pass") String password
    ) {
        return authService.register(login, password).toString();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String findAllUser() {

        final String[] result = {""};
        repo.findAll().forEach(
                user -> result[0] += user.toString() + "\n"
        );
        return result[0];
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String authUser(
            @RequestParam("login") String login
    ) {
        return authService.auth(login);
    }

    @RequestMapping(value = "/authStep", method = RequestMethod.POST)
    public String authStepUser(
            @RequestParam("login") String login,
            @RequestParam("hash") String hash
    ) {
        return authService.authStep(login, hash);
    }

    @RequestMapping(value = "/authDiff", method = RequestMethod.POST)
    public Map<String, String> authStepUserDiff(
            @RequestParam("login") String login,
            @RequestParam("hash") String hash
    ) {
        final String step = authService.authStep(login, hash);
        if (step.equals("OK")) {
            final Map<String, String> map = service.returnTriple();
            map.put("result", "OK");
            return map;
        }
        final HashMap<String, String> map = new HashMap<>();
        map.put("result", "BAD");
        return map;
    }

    @RequestMapping(value = "/authDiff/ck", method = RequestMethod.POST)
    public String authStepUserDiff(
            @RequestParam("login") String login,
            @RequestParam("hash") String hash,
            @RequestParam("k") String k
    ) {
        final String step = authService.authStep(login, hash);
        if (step.equals("OK")) {
            service.setK(k);
            return "OK";
        }
        return "BAD";
    }

    @RequestMapping(value = "/rsa", method = RequestMethod.POST)
    public Map<String, String> authStepUserDiff(
            @RequestParam("login") String login,
            @RequestParam("hash") String hash,
            @RequestParam("e") String e,
            @RequestParam("n") String n
    ) {
        final String step = authService.authStep(login, hash);
        if (step.equals("OK")) {
            this.rsa = new RSA();
            rsa.generateAll(100);
            final Map<String, String> map = new HashMap<>();
            map.put("e", rsa.getE().toString());
            map.put("n", rsa.getN().toString());
            map.put("result", "OK");
            this.rsa.setE(new BigInteger(e));
            this.rsa.setN(new BigInteger(n));
            return map;
        }
        final HashMap<String, String> map = new HashMap<>();
        map.put("result", "BAD");
        return map;
    }

    @RequestMapping(value = "/rsa/message", method = RequestMethod.POST)
    public String authRsaMessage(
            @RequestParam("login") String login,
            @RequestParam("hash") String hash,
            @RequestParam("message") String message
    ) {
        final String decrypt = this.rsa.decrypt(message);
        System.out.println(decrypt);
        return this.rsa.encrypt(decrypt + "  server number:" + String.valueOf(new Random().nextInt()));
    }

    @RequestMapping(value = "/diff/message", method = RequestMethod.POST)
    public String authDiffMessage(
            @RequestParam("login") String login,
            @RequestParam("hash") String hash,
            @RequestParam("message") String message
    ) {
        final String decrypt = this.service.decryptMessage(message);
        System.out.println(decrypt);
        return this.service.encryptMessage(decrypt + "  server number:" + String.valueOf(new Random().nextInt()));
    }




}
