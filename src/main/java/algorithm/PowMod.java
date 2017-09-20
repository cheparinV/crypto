package algorithm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ASUS-PC on 29.03.2017.
 */
public class PowMod {
    public BigInteger powMod(BigInteger base, BigInteger degreeOfBase, BigInteger mod) {
        ArrayList<BigInteger> modList = new ArrayList<>();
        ArrayList<BigInteger> baseList = new ArrayList<>();
        BigInteger div = new BigInteger(degreeOfBase.toString());
        while (!div.equals(BigInteger.ZERO)) {
            modList.add(div.mod(BigInteger.valueOf(2l)));
            div = div.divide(BigInteger.valueOf(2l));
        }
        Collections.reverse(modList);
        baseList.add(base);
        for (int i = 1; i < modList.size(); i++) {
            BigInteger c = BigInteger.ONE;
            if (modList.get(i).equals(BigInteger.ONE)) {
                c = base;
            }
            baseList.add(c.multiply(baseList.get(i - 1).pow(2)).mod(mod));
        }
        BigInteger result = baseList.get(baseList.size() - 1);
        return result;
    }
}
