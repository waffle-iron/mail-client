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
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
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
    private static final byte[] key16byteTest =
            new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};
    private static Key keyPrivate;

  public static byte[] encryptSessionKeyRSA(byte[] sessionKey, PublicKey key) throws GeneralSecurityException {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, key);
//    keyPrivate = pair.getPrivate();
    return cipher.doFinal(sessionKey);
  }

  public static byte[] decryptSessionKeyRSA(byte[] encryptedSessionKey, PrivateKey key) throws GeneralSecurityException {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, key);

    byte[] encodedPublicKey = cipher.doFinal(encryptedSessionKey);
    return encodedPublicKey;
  }

  protected static PublicKey getPublicKey(byte[] encodedKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
    KeyFactory factory = KeyFactory.getInstance("RSA");
    X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(encodedKey);
    return factory.generatePublic(encodedKeySpec);
  }

  protected static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(2048, SecureRandom.getInstance("SHA1PRNG"));
    return keyPairGenerator.generateKeyPair();
  }
  
    public static void main(String[] args) throws NoSuchAlgorithmException, GeneralSecurityException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, Exception {
//        System.out.println(encryptMessageByAES("This is message"));
//        System.out.println("========");
//        System.out.println(decryptMessagaeByAES("xeJ4ARR6qM5/nNn4IOjXM8PKjBSlw+yRhjPv1wfyPS7tZ/iwACEhg99kKS2yfsAZ"));
//        System.out.println(encryptPublicKeyRSA(keyValue));
//        System.out.println("key private = "+ keyPrivate.toString().getBytes());
//        System.out.println(decryptPublicKeyRSA("[B@224aed64".getBytes()));
        KeyPair pair = generateKeyPair();

        keyPrivate = pair.getPrivate();
        System.out.println("encode" + encryptSessionKeyRSA(key16byteTest, pair.getPublic()));
        System.out.println("decode" +decryptSessionKeyRSA(encryptSessionKeyRSA(key16byteTest, pair.getPublic()), pair.getPrivate()));
    }

    // Sinh sessionKey 128bit
//    private static Key generateSessionKey() throws NoSuchAlgorithmException {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(128, new SecureRandom());
//        return keyGenerator.generateKey();
//    }
    public static Key generateKeyAES() throws Exception {
        return new SecretKeySpec(key16byteTest, "AES");
    }
    
    // encrypt data by AES algorithm
    public static String encryptMessageByAES(String data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, Exception {
        Cipher cipher = Cipher.getInstance("AES");
        System.out.println(generateKeyAES().toString().getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, generateKeyAES());
        byte[] encryptValue = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptValue);
    }
    
    // decrypt data by AES algorithm
    public static String decryptMessagaeByAES(String encryptedData) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, Exception {
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, generateKeyAES());
        byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decryptValue = c.doFinal(decordedValue);
        return new String(decryptValue);
    }
}
