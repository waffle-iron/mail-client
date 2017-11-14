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
    
    
    
//    String formatPrivate = privateKey.getFormat(); // PKCS#8
//    String formatPublic = publicKey.getFormat(); // X.509
//    
//    System.out.println("Private Key : " + Base64.encode(privateKeyBytes));
//    System.out.println("Public Key : " + Base64.encode(publicKeyBytes));
//
//               // The bytes can be converted back to public and private key objects
//    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//    PrivateKey privateKey2 = stringToPrivateKey(Base64.encode(privateKeyBytes));
//      System.out.println(privateKey2);
//    PublicKey publicKey2 = stringToPublicKey(Base64.encode(publicKeyBytes));
//
//    // The original and new keys are the same
//    System.out.println("  Are both private keys equal? " + privateKey.equals(privateKey2));
//    System.out.println("  Are both public keys equal? " + publicKey.equals(publicKey2));
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
}
