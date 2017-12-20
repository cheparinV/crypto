package com.univer.algorithm;

import java.math.BigInteger;

/**
 * Created by ASUS-PC on 23.09.2017.
 */
public class RSA {

    private final PrimeGenerator primeGenerator;
    private final Euclid euclid;
    private final PowMod powMod;
    private BigInteger q;
    private BigInteger p;
    private BigInteger N;
    private BigInteger fN;
    private BigInteger e;
    private BigInteger d;

    public RSA() {
        this.primeGenerator = new PrimeGenerator();
        this.euclid = new Euclid();
        this.powMod = new PowMod();
    }

    public void generateAll(int size) {
        this.q = primeGenerator.primeGenerationBySize(size);
        this.p = primeGenerator.primeGenerationBySize(size);
        this.N = p.multiply(q);
        this.fN = p.subtract(BigInteger.ONE)
                .multiply(
                        q.subtract(BigInteger.ONE)
                );
        do {
            this.e = primeGenerator.primeGenerationBySize(size / 3);
        } while (this.fN.mod(this.e).equals(BigInteger.ZERO));

        this.euclid.countEuclid(this.fN, this.e);
        d = new BigInteger(
                this.euclid.getY()
        );
        if (this.d.compareTo(BigInteger.ZERO) < 0) {
            System.out.println("minus");
            this.d = this.d.add(this.fN);
        }
    }

    public String encrypt(String info) {
        byte[] bytes = info.getBytes();

        char[] chars = info.toCharArray();
        StringBuilder builder = new StringBuilder();
        builder.append(1);
        for (char aChar : chars) {
            final int aInt = aChar;
            String aStr = aInt < 1000 ? "0" + aInt : "" + aInt;
            aStr = aInt < 100 ? "00" + aInt : aStr;
            builder.append(aStr);
        }
        BigInteger bigInteger = new BigInteger(builder.toString());
        BigInteger res = this.powMod.powMod(bigInteger, this.e, this.N);
        return res.toString();
    }

    public String decrypt(String info) {
        BigInteger data = new BigInteger(info);
        BigInteger bigInteger = this.powMod.powMod(data, this.d, this.N);
        final String result = bigInteger.toString();
        final char[] chars = result.toCharArray();
        final StringBuilder tmp = new StringBuilder();
        final StringBuilder builder = new StringBuilder();
        for (int i = 1; i < chars.length; ++i) {
            tmp.append(chars[i]);
            if (i%4 == 0) {
                final char aChar = (char) Integer.parseInt(tmp.toString());
                tmp.setLength(0);
                builder.append(aChar);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        RSA rsa = new RSA();
        rsa.generateAll(309);
        BigInteger mod = rsa.getE().multiply(rsa.getD()).mod(rsa.getfN());
        if (!mod.equals(BigInteger.ONE)) {
            System.out.println("Problem-----------------------");
        }
        String hello = rsa.encrypt("hello привет");
        System.out.println(hello);
        System.out.println(rsa.decrypt(hello));
    }

    @Override
    public String toString() {
        return "RSA{" +
                "q=" + q +
                ", p=" + p +
                ", N=" + N +
                ", fN=" + fN +
                ", e=" + e +
                ", d=" + d +
                '}';
    }

    public BigInteger getQ() {
        return q;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getN() {
        return N;
    }

    public BigInteger getfN() {
        return fN;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getD() {
        return d;
    }
}
