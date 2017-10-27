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
    private static final byte[] keyValue =
            new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};
    private static Key keyPrivate;
  /**
   * The message is created like so: - Generates a random KeyPair - Encrypt the
   * message with the private key from the generated key pair - Encrypt the
   * generated public key with given public key
   *
   * @param message The message to encrypt
   * @param key The key to encrypt with
   * @return The encrypted message
   * @throws GeneralSecurityException
   */
  public static byte[] encryptPublicKeyRSA(byte[] key) throws GeneralSecurityException {
    KeyPair pair = generateKeyPair();
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
    keyPrivate = pair.getPrivate();

//    byte[] encryptedMessage = cipher.doFinal(message.getBytes()); // Message encrypt with privateKey RSA

//    cipher.init(Cipher.ENCRYPT_MODE, key);

//    byte[] encryptedPublicKey =  // PublicKey encrypt

//    ByteBuffer buffer = ByteBuffer.allocate((encryptedPublicKey.length + encryptedMessage.length) + 4);
//    buffer.putInt(encryptedPublicKey.length);
//    buffer.put(encryptedPublicKey);
//    buffer.put(encryptedMessage);
    return cipher.doFinal(key);
  }

  /**
   * The message is decrypted like so: - Read the encrypted public key - Decrypt
   * the public key with the private key - Read the encrypted message - Use the
   * decrypted public key to decrypt the encrypted message
   *
   * @param message The encrypted message
   * @param key The private key
   * @return The decrypted message
   * @throws GeneralSecurityException
   */
  public static byte[] decryptPublicKeyRSA(byte[] key) throws GeneralSecurityException {
//    ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
//    int keyLength = buffer.getInt();
//    byte[] encyptedPublicKey = new byte[keyLength];
//    buffer.get(encyptedPublicKey);

    Cipher cipher = Cipher.getInstance("RSA");
//    Key key = new SecretKeySpec("[B@224aed64".getBytes(), 0, "[B@224aed64".getBytes().length, "RSA");
//    cipher.init(Cipher.DECRYPT_MODE, );

    byte[] encodedPublicKey = cipher.doFinal(key);
    return encodedPublicKey;
  }

  protected static PublicKey getPublicKey(byte[] encodedKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
    KeyFactory factory = KeyFactory.getInstance("RSA");
    X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(encodedKey);
    return factory.generatePublic(encodedKeySpec);
  }

  protected static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(1024, SecureRandom.getInstance("SHA1PRNG"));
    return keyPairGenerator.generateKeyPair();
  }
  
    public static void main(String[] args) throws NoSuchAlgorithmException, GeneralSecurityException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, Exception {
//        System.out.println(encryptByAES("This is message, hi Diep, I like AES and PGP"));
//        System.out.println("========");
//        System.out.println(decryptByAES("xeJ4ARR6qM5/nNn4IOjXM8PKjBSlw+yRhjPv1wfyPS7tZ/iwACEhg99kKS2yfsAZ"));
        System.out.println(encryptPublicKeyRSA(keyValue));
        System.out.println("key value = "+keyValue);
//        System.out.println(decryptPublicKeyRSA("[B@387c703b".getBytes()));
    }

    // Sinh sessionKey 128bit
//    private static Key generateSessionKey() throws NoSuchAlgorithmException {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(128, new SecureRandom());
//        return keyGenerator.generateKey();
//    }
    public static Key generateSessionKey() throws Exception {
        return new SecretKeySpec(keyValue, "AES");
    }
    
    // encrypt data by AES algorithm
    public static String encryptByAES(String data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, generateSessionKey());
        byte[] encryptValue = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptValue);
    }
    
    // decrypt data by AES algorithm
    public static String decryptByAES(String encryptedData) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, Exception {
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, generateSessionKey());
        byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decryptValue = c.doFinal(decordedValue);
        return new String(decryptValue);
    }
}
