package com.univer.rest.resource;

import com.univer.algorithm.RSA;
import com.univer.rest.repo.UserRepo;
import com.univer.rest.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.Map;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 * @version $Id$
 * @since 1.0
 */
@RestController
@RequestMapping("/crypto/message")
public class ClientResource {


    @Autowired
    UserRepo userRepo;

    String hash;
    RSA rsa;
    private String cX;

    MessageService service = new MessageService();

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String reigster(
            @RequestParam("login") String login,
            @RequestParam("pass") String password
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("login", login);
        map.add("pass", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        return new RestTemplate().postForObject("http://localhost:8080/crypto/auth/add",request, String.class);

    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String auth(
            @RequestParam("login") String login,
            @RequestParam("pass") String password
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("login", login);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        final String object = new RestTemplate().postForObject("http://localhost:8080/crypto/auth/auth", request, String.class);

        password = DigestUtils.md5DigestAsHex(password.getBytes());
        final String hash = DigestUtils.md5DigestAsHex((password + object).getBytes());

        map= new LinkedMultiValueMap<>();
        map.add("login", login);
        map.add("hash", hash);

        request = new HttpEntity<>(map, headers);
        final String result = new RestTemplate().postForObject("http://localhost:8080/crypto/auth/authStep", request, String.class);
        this.hash = hash;
        return result + " | hash: " + hash;
    }



    @RequestMapping(value = "/diff", method = RequestMethod.GET)
    public String diffAuth(
            @RequestParam("login") String login,
            @RequestParam("pass") String password
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("login", login);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        final String object = new RestTemplate().postForObject("http://localhost:8080/crypto/auth/auth", request, String.class);

        password = DigestUtils.md5DigestAsHex(password.getBytes());
        final String hash = DigestUtils.md5DigestAsHex((password + object).getBytes());

        map= new LinkedMultiValueMap<>();
        map.add("login", login);
        map.add("hash", hash);

        request = new HttpEntity<>(map, headers);
        final Map map1 = new RestTemplate().postForObject("http://localhost:8080/crypto/auth/authDiff", request, Map.class);
        if (map1.get("result").equals("BAD")) {
            return "not correct auth";
        }
        this.hash = hash;
        final BigInteger bigInteger = service.getK(((Map<String, String>) map1));

        map.add("k", bigInteger.toString());
        request = new HttpEntity<>(map, headers);
        final String result = new RestTemplate().postForObject("http://localhost:8080/crypto/auth/authDiff/ck", request, String.class);
        this.cX = service.getcX().toString();
        return result + " | cY: "+ service.getcX();
    }

    @RequestMapping(value = "/rsa", method = RequestMethod.POST)
    public String rsa(
            @RequestParam("login") String login
    ) {
        this.rsa = new RSA();
        this.rsa.generateAll(100);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("login", login);
        map.add("hash", this.hash);
        map.add("e", rsa.getE().toString());
        map.add("n", rsa.getN().toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        final Map object = new RestTemplate().postForObject("http://localhost:8080/crypto/auth/rsa", request, Map.class);
        if (object.get("result").equals("BAD")) {
            return "not correct auth";
        }
        final String e = object.get("e").toString();
        final String n = object.get("n").toString();
        this.rsa.setE(new BigInteger(e));
        this.rsa.setN(new BigInteger(n));

        return "OK";
    }

    @RequestMapping(value = "/rsa/message", method = RequestMethod.POST)
    public String rsaMessage(
            @RequestParam("login") String login,
            @RequestParam("message") String message
    ) {
        final String encrypt = this.rsa.encrypt(message);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("login", login);
        map.add("hash", this.hash);
        map.add("message", encrypt);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        return this.rsa.decrypt(
                new RestTemplate().postForObject("http://localhost:8080/crypto/auth/rsa/message", request, String.class)
        );
    }

    @RequestMapping(value = "/diff/message", method = RequestMethod.POST)
    public String diffMessage(
            @RequestParam("login") String login,
            @RequestParam("message") String message
    ) {
        final String encrypt = this.service.encryptMessage(message);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("login", login);
        map.add("hash", this.hash);
        map.add("message", encrypt);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        return this.service.decryptMessage(
                new RestTemplate().postForObject("http://localhost:8080/crypto/auth/diff/message", request, String.class)
        );
    }



}
