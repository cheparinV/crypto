package com.univer.algorithm;

import org.junit.Test;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class XORCipherTest {

    /**
     * ${CLASS} can work.
     *
     * @throws Exception If fails
     */
    @Test
    public void testCrypt() throws Exception {
        final XORCipher xorCipher = new XORCipher();
        final String abcdefg = xorCipher.crypt("abcdefg", 12);
        System.out.println(abcdefg);
        System.out.println(xorCipher.crypt(abcdefg, 12));
    }
}
