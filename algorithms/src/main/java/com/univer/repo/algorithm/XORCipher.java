package crypto.algorithm;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class XORCipher {

    public String crypt(String string, Integer key) {
        final char[] chars = string.toCharArray();
        final StringBuilder builder = new StringBuilder();
        for (char aChar : chars) {
            int k = (int) aChar;
            k ^= key;
            builder.append((char) k);
        }
        return builder.toString();
    }


}
