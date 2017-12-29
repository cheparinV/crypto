package com.univer.algorithm;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class RC4Test {

    @Test
    public void testCryptMessage() {
        RC4 rc4 = new RC4();
        String message = "Hello, World!";
        String key = "This is key for RC4";
        String crypt = rc4.encryptMessage(message, key);
        String msg = rc4.decryptMessage(crypt, key);
        assertEquals(message, msg);
    }

    @Test
    public void testCryptWithNonEnglishCharacters() {
        String message = "Привет, Мир!";
        String key = "Это ключ для RC4";
        RC4 rc4 = new RC4(key);
        byte[] crypt = rc4.crypt(message.getBytes());
        byte[] msg = rc4.crypt(crypt);
        assertEquals(message, new String(msg));
    }

}
