package crypto.algorithm;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class Cesar {

    private Integer shift;

    private final static Integer START = 97;
    private final static Integer END = 122;

    public String encrypt(String string, Integer shift) {
        if (shift == null || string == null) {
            System.err.println("Null!!!");
            return "";
        }

        final char[] chars = string.toCharArray();

        final StringBuilder builder = new StringBuilder();
        for (char aChar : chars) {
            int aInt = aChar;
            aInt += shift;
            if (aInt < START) {
                aInt += END - START + 1;
            }
            if (aInt > END) {
                aInt -= END - START + 1;
            }
            builder.append((char) aInt);
        }
        return builder.toString();
    }

    public String decrypt(String string, Integer shift) {
        return this.encrypt(string, -shift);
    }
}
