package com.univer.algorithm;

import java.util.Arrays;


public class RC4 {
    private static final int BYTE_LENGTH = 256;
    private byte[] key = new byte[BYTE_LENGTH - 1];
    private int[] newKey = new int[BYTE_LENGTH];

    public RC4() {
    }

    public RC4(String key) {
        this.key = key.getBytes();
    }



    public String encryptMessage(String message, String key) {
        this.key = key.getBytes();
        return Arrays.toString(crypt(message.getBytes()));
    }

    public String decryptMessage(String message, String key) {
        final String[] split = message.replaceAll("\\[", "")
                .replaceAll("]", "")
                .replaceAll(" ", "")
                .split(",");
        final byte[] bytes = new byte[split.length];
        for (int i = 0; i < split.length; ++i) {
            bytes[i] = Byte.valueOf(split[i]);
        }
        return this.decryptMessage(bytes, key);
    }


    public String decryptMessage(byte[] message, String key) {
        this.key = key.getBytes();
        byte[] msg = crypt(message);
        return new String(msg);
    }

    public byte[] crypt(final byte[] msg) {
        newKey = initKey(key);
        byte[] code = new byte[msg.length];
        int i = 0;
        int j = 0;
        for (int n = 0; n < msg.length; n++) {
            i = (i + 1) % BYTE_LENGTH;
            j = (j + newKey[i]) % BYTE_LENGTH;
            swap(i, j, newKey);
            int rand = newKey[(newKey[i] + newKey[j]) % BYTE_LENGTH];
            code[n] = (byte) (rand ^ msg[n]);
        }
        return code;
    }

    private int[] initKey(byte[] key) {
        int[] newKey = new int[BYTE_LENGTH];
        int j = 0;

        for (int i = 0; i < BYTE_LENGTH; i++) {
            newKey[i] = i;
        }

        for (int i = 0; i < BYTE_LENGTH; i++) {
            j = (j + newKey[i] + (key[i % key.length]) & 0xFF) % BYTE_LENGTH;
            swap(i, j, newKey);
        }
        return newKey;
    }

    private void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
