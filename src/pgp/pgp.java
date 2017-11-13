/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgp;

import java.math.BigInteger;
import javax.crypto.Cipher;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author HackerStrawHat
 */
public class pgp {
//    private static Key keyPrivate;
    private static final String _AES_ALGORITHM_NAME = "AES";
    private static final String _RSA_ALGORITHM_NAME = "RSA";

  public static String encryptSessionKeyRSA(String sessionKey, PublicKey key) throws GeneralSecurityException {
    Cipher cipher = Cipher.getInstance(_RSA_ALGORITHM_NAME);
    cipher.init(Cipher.ENCRYPT_MODE, key);
    return bytesToHex(cipher.doFinal(sessionKey.getBytes()));
  }

  public static byte[] decryptSessionKeyRSA(String encryptedSessionKey, PrivateKey key) throws GeneralSecurityException {
    Cipher cipher = Cipher.getInstance(_RSA_ALGORITHM_NAME);
    cipher.init(Cipher.DECRYPT_MODE, key);
    byte[] encodedSessionKey = cipher.doFinal(hexStringToByteArray(encryptedSessionKey));
    return encodedSessionKey;
  }

//  
//    public static void main(String[] args) throws NoSuchAlgorithmException, GeneralSecurityException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, Exception {
//        Test AES
//        System.out.println(encryptMessageByAES("This is message"));
//        System.out.println("========");
//        System.out.println(decryptMessagaeByAES("xeJ4ARR6qM5/nNn4IOjXM8PKjBSlw+yRhjPv1wfyPS7tZ/iwACEhg99kKS2yfsAZ"));
//        
//        Test RSA
//        KeyPair pair = KDC.generateKeyPair();
//        
//        System.out.println(encryptSessionKeyRSA(KDC.key16byteTest, pair.getPublic()));
//        System.out.println(new String(decryptSessionKeyRSA(encryptSessionKeyRSA(KDC.key16byteTest, pair.getPublic()),
//                pair.getPrivate())));
//    }
  
  //
//  protected static PublicKey getPublicKey(byte[] encodedKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
//    KeyFactory factory = KeyFactory.getInstance("RSA");
//    X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(encodedKey);
//    return factory.generatePublic(encodedKeySpec);
//  }

    // Sinh sessionKey 128bit
//    private static Key generateSessionKey() throws NoSuchAlgorithmException {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(128, new SecureRandom());
//        return keyGenerator.generateKey();
//    }
    public static String bytesToHex(byte[] bytes) {
      char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
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
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
    
    private static Key generateKeyAES() throws Exception {
        return new SecretKeySpec(KDC.key16byteTest.getBytes(), _AES_ALGORITHM_NAME);
    }
    
    // encrypt data by AES algorithm
    public static String encryptMessageByAES(String data) throws 
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, Exception {
        Cipher cipher = Cipher.getInstance(_AES_ALGORITHM_NAME);
        cipher.init(Cipher.ENCRYPT_MODE, generateKeyAES());
        byte[] encryptValue = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptValue);
    }
    
    // decrypt data by AES algorithm
    public static String decryptMessagaeByAES(String encryptedData) throws 
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, Exception {
        Cipher c = Cipher.getInstance(_AES_ALGORITHM_NAME);
        c.init(Cipher.DECRYPT_MODE, generateKeyAES());
        byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decryptValue = c.doFinal(decordedValue);
        return new String(decryptValue);
    }
}
