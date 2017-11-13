/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgp;

import java.security.*;

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
  
  public static void main(String[] args) throws NoSuchAlgorithmException {
    KeyPair keyPair = generateKeyPair();
    publicKey = keyPair.getPublic();
    privateKey = keyPair.getPrivate();
  }
}
