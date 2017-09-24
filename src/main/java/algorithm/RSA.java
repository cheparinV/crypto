package algorithm;

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
        System.out.println(info);
        byte[] bytes = info.getBytes();

        char[] chars = info.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (char aChar : chars) {
            System.out.println(((int) aChar));
            builder.append((int) aChar);
        }
        BigInteger bigInteger = new BigInteger(builder.toString());
        System.out.println("Info :" + bigInteger.toString());
        BigInteger res = this.powMod.powMod(bigInteger, this.e, this.N);
        System.out.println(res.toString());
        return res.toString();
    }

    public String decrypt(String info) {
        BigInteger data = new BigInteger(info);
        BigInteger bigInteger = this.powMod.powMod(data, this.d, this.N);
        System.out.println("Info :" + bigInteger.toString());
        return bigInteger.toString();
    }

    public static void main(String[] args) {
        RSA rsa = new RSA();
        rsa.generateAll(309);
        BigInteger mod = rsa.getE().multiply(rsa.getD()).mod(rsa.getfN());
        if (!mod.equals(BigInteger.ONE)) {
            System.out.println("Problem-----------------------");
        }
        System.out.println(mod);
        System.out.println(rsa);
        String hello = rsa.encrypt("год");
        rsa.decrypt(hello);
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
