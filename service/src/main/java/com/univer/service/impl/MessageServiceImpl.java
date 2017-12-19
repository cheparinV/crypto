package com.univer.service.impl;

import crypto.algorithm.PrimeGenerator;
import crypto.algorithm.RSA;
import com.univer.service.MessageService;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 * @version $Id$
 * @since 1.0
 */
public class MessageServiceImpl implements MessageService {

    private BigInteger q;
    private BigInteger n;
    private BigInteger x;
    private BigInteger cX;


    public Map<String, String> returnTriple() {
        final Map<String, String> map = new HashMap<>();
        final PrimeGenerator primeGenerator = new PrimeGenerator();
        n = primeGenerator.primeGenerationBySize(100);
        q = primeGenerator.primeGenerationBySize(100);
        do {
            x = new BigInteger(n.bitLength(), new Random());
        } while (x.compareTo(n) >= 0);
        map.put("n", n.toString());
        map.put("q", q.toString());
        map.put("x", x.toString());
        return map;
    }

    public void setK(String primeK) {
        cX = new BigInteger(primeK).modPow(x, n);
    }

    public String sendMessage(String message) {
        new RSA();
        return "";
    }
}
