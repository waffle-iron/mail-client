/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgp;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author HackerStrawHat
 */
public class KDC {

  public static final String key16byteTest = "TheBestSecretKey";
  public static PublicKey publicKey;
  public static PrivateKey privateKey;
  
  public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(2048, SecureRandom.getInstance("SHA1PRNG"));
    return keyPairGenerator.generateKeyPair();
  }
  
  public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
    KeyPair keyPair = generateKeyPair();
    publicKey = keyPair.getPublic();
    privateKey = keyPair.getPrivate();
    byte[] privateKeyBytes = privateKey.getEncoded();
    byte[] publicKeyBytes = publicKey.getEncoded();
    KeyFileIO.KeyFileIO.writeKeyToFile(Base64.encode(publicKeyBytes), "public_key.txt");
    KeyFileIO.KeyFileIO.writeKeyToFile(Base64.encode(privateKeyBytes), "private_key.txt");
  }
  
    public static PrivateKey stringToPrivateKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.decode(key));
        return keyFactory.generatePrivate(privateKeySpec);
    }
    
    public static PublicKey stringToPublicKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.decode(key));
        return keyFactory.generatePublic(publicKeySpec);
    }
    
//        Sinh sessionKey 128bit
//        private static Key generateSessionKey() throws NoSuchAlgorithmException {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(128, new SecureRandom());
//        return keyGenerator.generateKey();
//    }
}
