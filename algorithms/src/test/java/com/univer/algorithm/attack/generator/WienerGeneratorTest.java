package com.univer.algorithm.attack.generator;

import com.univer.algorithm.attack.Dujel;
import com.univer.algorithm.attack.Wiener;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Map;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 * @version $Id$
 * @since 1.0
 */
public class WienerGeneratorTest {
    /**
     * ${CLASS} can work.
     *
     * @throws Exception If fails
     */
    @Test
    public void testGenerateAll() throws Exception {
        final Map<String, BigInteger> map = new WienerGenerator().generateAll(512);
        for (String s : map.keySet()) {
            System.out.println(s + " : " + map.get(s));
        }

        final BigInteger attack = new Wiener().attack(map.get("N"), map.get("e"), "2142352");
        System.out.println("Hacked d  wiener : ");
        System.out.println(attack);
        Assert.assertEquals(map.get("d"), attack);

        final BigInteger range = map.get("D");
        final BigInteger dujel = new Dujel().attack(map.get("N"), map.get("e"), 10000);

        System.out.println("Hacked d dujel : ");
        System.out.println(dujel);
        Assert.assertEquals(map.get("d"), dujel);

    }

    /**
     * ${CLASS} can work.
     *
     * @throws Exception If fails
     */
    @Test
    public void testGenerateD() throws Exception {
    }

}
