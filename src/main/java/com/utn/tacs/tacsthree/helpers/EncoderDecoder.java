package com.utn.tacs.tacsthree.helpers;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncoderDecoder {

    public byte[] encode(String sinCifrar) throws Exception {
            final byte[] bytes = sinCifrar.getBytes("UTF-8");
            final Cipher aes = getCipher(true);
            final byte[] encodedText = aes.doFinal(bytes);
            return encodedText;
    }

    public String decode(byte[] cifrado) throws Exception {
            final Cipher aes = getCipher(false);
            final byte[] bytes = aes.doFinal(cifrado);
            final String decodedText = new String(bytes, "UTF-8");
            return decodedText;
    }

    private Cipher getCipher(boolean paraCifrar) throws Exception {
            final String phrase = "EnCt26c1d52b24b9dcca2583e1e166b471d017e8b774f6c1d52b24b9dcca2583e1e16N+0r/HYaxAFvUmNsDFfvlFPNWoifYGkiZHEekimPfaBtBicaykkDbn1xkWa+gykU6bn/s7FK7BGTiy3L3w07L91y/SoiHgIXOOGLzVXvXSMlzo67MagII6BcAvsKYrH1YoyXeJnpeXN1HahP4b5GspU9dVTJSkdRqRr71acC7zEOL5dQhpzRxYzPLvRo49suh/";
            final MessageDigest digest = MessageDigest.getInstance("SHA");
            digest.update(phrase.getBytes("UTF-8"));
            final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

            final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
            if (paraCifrar) {
                    aes.init(Cipher.ENCRYPT_MODE, key);
            } else {
                    aes.init(Cipher.DECRYPT_MODE, key);
            }

            return aes;
    }
    
}
