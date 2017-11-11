package algorithm;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class Gronsfeld {

    public String encrypt(String string, String keyStr) {
        final Integer[] key = this.prepareKey(keyStr, string.length(), 1);
        return this.crypt(string, key);
    }

    public String decrypt(String string, String keyStr) {
        final Integer[] key = this.prepareKey(keyStr, string.length(), -1);
        return this.crypt(string, key);
    }

    private String crypt(String string, Integer[] key) {
        final Matrix matrix = new Matrix();
        final char[] chars = string.toCharArray();
        final StringBuilder builder = new StringBuilder(chars.length);
        for (int i = 0; i < chars.length; i++) {
            final Integer numB = matrix.getNumberByChar(chars[i]);
            builder.append(
                    matrix.getStringByPair(key[i], numB)
            );
        }
        return builder.toString();
    }

    //public String decrypt(String string, String keyStr) {
    //    this.prepareKey(keyStr, string.length())
    //}

    private Integer[] prepareKey(String key, Integer length, Integer coef) {
        while (key.length() < length) {
            key += key;
        }
        if (key.length() > length) {
            key = key.substring(0, length);
        }
        final char[] keyChar = key.toCharArray();
        final Integer[] keyInt = new Integer[keyChar.length];
        for (int i = 0; i < keyChar.length; i++) {
            keyInt[i] = Character.getNumericValue(keyChar[i]) * coef;
        }
        return keyInt;
    }

    private static class Matrix {

        public Integer getNumberByChar(char alpha) {
            return this.getNumberByString(String.valueOf(alpha));
        }

        public Integer getNumberByString(String alpha) {
            final Alpha value = Alpha.valueOf(alpha);
            final Alpha[] values = Alpha.values();
            for (int i = 0; i < values.length; ++i) {
                if (values[i].equals(value)) {
                    return i;
                }
            }
            System.out.println("Not found by string");
            return null;
        }

        public String getStringByPair(Integer numA, Integer numB) {
            Integer sum = numA + numB;
            final Alpha[] values = Alpha.values();
            while (sum > values.length) {
                sum -= values.length;
            }
            while (sum < 0) {
                sum += values.length;
            }
            return values[sum].toString();
        }

        private enum Alpha {
            A, B, C, D, E, F, G, H, I, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z
        }
    }


}
