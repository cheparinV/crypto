package com.univer.algorithm;

import junit.framework.Assert;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class GronsfeldTest {

    private final String TEST = "GRONSFELD";
    private final String KEY = "201520152";

    /**
     * ${CLASS} can work.
     *
     * @throws Exception If fails
     */
    @org.junit.Test
    public void testEncrypt() throws Exception {
        final Gronsfeld gronsfeld = new Gronsfeld();
        final String encrypt = gronsfeld.encrypt(TEST, KEY);
        System.out.println(encrypt);
        final String decrypt = gronsfeld.decrypt(encrypt, KEY);
        System.out.println(decrypt);
        Assert.assertEquals(TEST, decrypt);
    }

}
