/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeyFileIO;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tran.xuan.diep
 */
public final class KeyFileIO {
    public final static void writeKeyToFile(String key, String fileName) {
        FileOutputStream fos = null;
        File file;
        try {
            file = new File(fileName);
            if(!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            fos.write(key.getBytes());
            fos.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public final static String readKeyFromFile(String fileName) {
        
        File file = new File(fileName); 
        String keyResult = "";
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()) {
                keyResult = scanner.next();
            }
            scanner.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return keyResult;
    }
}
