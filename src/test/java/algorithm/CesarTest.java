package algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class CesarTest {
    /**
     * ${CLASS} can work.
     *
     * @throws Exception If fails
     */
    @Test
    public void testDecrypt() throws Exception {
        String test = "abcdxyz";

        final Cesar cesar = new Cesar();
        final String encrypt = cesar.encrypt(test, 5);
        final String decrypt = cesar.decrypt(encrypt, 5);

        Assert.assertEquals(test, decrypt);
    }

    /**
     * ${CLASS} can work.
     *
     * @throws Exception If fails
     */
    @Test
    public void testEncrypt() throws Exception {
        String test = "abcxyz";

        final Cesar cesar = new Cesar();
        final String encrypt = cesar.encrypt(test, 5);

        Assert.assertNotSame(test, encrypt);
    }

}
