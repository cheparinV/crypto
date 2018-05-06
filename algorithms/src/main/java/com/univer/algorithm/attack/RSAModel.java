package com.univer.algorithm.attack;

import java.math.BigInteger;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 * @version $Id$
 * @since 1.0
 */
public class RSAModel {

    private BigInteger N;
    private BigInteger e;
    private BigInteger cipherMessage;

    public BigInteger getN() {
        return N;
    }

    public RSAModel setN(BigInteger n) {
        N = n;
        return this;
    }

    public BigInteger getE() {
        return e;
    }

    public RSAModel setE(BigInteger e) {
        this.e = e;
        return this;
    }

    public BigInteger getCipherMessage() {
        return cipherMessage;
    }

    public RSAModel setCipherMessage(BigInteger cipherMessage) {
        this.cipherMessage = cipherMessage;
        return this;
    }
}
