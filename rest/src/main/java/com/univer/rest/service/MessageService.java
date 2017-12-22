package com.univer.rest.service;

import com.univer.algorithm.PrimeGenerator;
import com.univer.algorithm.RSA;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 * @version $Id$
 * @since 1.0
 */
public class MessageService {

    private BigInteger q;
    private BigInteger n;
    private BigInteger x;
    private BigInteger cX;


    public Map<String, String> returnTriple() {
        final Map<String, String> map = new HashMap<>();
        PrimeGenerator primeGenerator = new PrimeGenerator();
        n = BigInteger.probablePrime(1024, new Random());
        q = BigInteger.probablePrime(1024, new Random());
        do {
            x = new BigInteger(n.bitLength(), new Random());
        } while (x.compareTo(n) >= 0);
        BigInteger m = q.modPow(x, n);
        map.put("n", n.toString());
        map.put("q", q.toString());
        map.put("m", m.toString());
        return map;
    }

    public BigInteger getK(Map<String, String> map) {
        q = new BigInteger(map.get("q"));
        n = new BigInteger(map.get("n"));
        BigInteger m = new BigInteger(map.get("q"));
        BigInteger y = BigInteger.ONE;
        do {
            y = new BigInteger(n.bitLength(), new Random());
        } while (y.compareTo(n) >= 0);
        cX = m.modPow(y, n);
        return q.modPow(y, n);
    }

    public BigInteger setK(String primeK) {
        return cX = new BigInteger(primeK).modPow(x, n);
    }

    public BigInteger getcX() {
        return cX;
    }

    public String sendMessage(String message) {
        new RSA();
        return "";
    }

}
