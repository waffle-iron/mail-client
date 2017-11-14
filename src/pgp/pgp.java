/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgp;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author HackerStrawHat
 */
public class pgp {
//    private static Key keyPrivate;

    private static final String _AES_ALGORITHM_NAME = "AES";
    private static final String _RSA_ALGORITHM_NAME = "RSA";
    
    public static String encryptSessionKeyRSA(String sessionKey, PublicKey key)
            throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(_RSA_ALGORITHM_NAME);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return bytesToHex(cipher.doFinal(sessionKey.getBytes()));
    }
    
    public static byte[] decryptSessionKeyRSA(String encryptedSessionKey, PrivateKey key)
            throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(_RSA_ALGORITHM_NAME);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] encodedSessionKey = cipher.doFinal(hexStringToByteArray(encryptedSessionKey));
        return encodedSessionKey;
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
    
    private static Key generateKeyAES(String keyString) throws Exception {
        return new SecretKeySpec(keyString.getBytes(), _AES_ALGORITHM_NAME);
    }

    // encrypt data by AES algorithm
    public static String encryptMessageByAES(String data) throws
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, Exception {
        Cipher cipher = Cipher.getInstance(_AES_ALGORITHM_NAME);
        cipher.init(Cipher.ENCRYPT_MODE, generateKeyAES(KDC.key16byteTest));
        byte[] encryptValue = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptValue);
    }

    // decrypt data by AES algorithm
    public static String decryptMessagaeByAES(String encryptedData, String key) throws
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, Exception {
        Cipher c = Cipher.getInstance(_AES_ALGORITHM_NAME);
        c.init(Cipher.DECRYPT_MODE, generateKeyAES(key));
        byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decryptValue = c.doFinal(decordedValue);
        return new String(decryptValue);
    }
}
