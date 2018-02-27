package com.univer.rest.resource;

import com.univer.algorithm.Cesar;
import com.univer.algorithm.Gronsfeld;
import com.univer.algorithm.XORCipher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 * @version $Id$
 * @since 1.0
 */
@RestController
@RequestMapping("/crypto/algo")
public class AlgoResource {

    @RequestMapping(value = "/cesar/crypt", method = RequestMethod.POST)
    public String cesarCrypt(
            @RequestParam("message") String message,
            @RequestParam("shift") Integer shift
    ) {
        final Cesar cesar = new Cesar();
        return cesar.encrypt(message, shift);
    }


    @RequestMapping(value = "/cesar/encrypt", method = RequestMethod.POST)
    public String cesarEncrypt(
            @RequestParam("message") String message,
            @RequestParam("shift") Integer shift
    ) {
        final Cesar cesar = new Cesar();
        return cesar.decrypt(message, shift);
    }

    @RequestMapping(value = "/xor/crypt", method = RequestMethod.POST)
    public String xorCrypt(
            @RequestParam("message") String message,
            @RequestParam("key") Integer key
    ) {
        final XORCipher cipher = new XORCipher();
        return cipher.crypt(message, key);
    }


    @RequestMapping(value = "/xor/encrypt", method = RequestMethod.POST)
    public String xorEncrypt(
            @RequestParam("message") String message,
            @RequestParam("key") Integer key
    ) {
        final XORCipher cipher = new XORCipher();
        return cipher.crypt(message, key);
    }

    @RequestMapping(value = "/gron/crypt", method = RequestMethod.POST)
    public String gronCrypt(
            @RequestParam("message") String message,
            @RequestParam("key") String key
    ) {
        return new Gronsfeld().encrypt(message, key);
    }


    @RequestMapping(value = "/gron/encrypt", method = RequestMethod.POST)
    public String gronEncrypt(
            @RequestParam("message") String message,
            @RequestParam("key") String key
    ) {
        return new Gronsfeld().decrypt(message, key);
    }


}
