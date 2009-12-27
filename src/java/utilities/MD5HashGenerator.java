/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * generates the md5 hash of a string
 * @author Ahmed Kotb
 */
public class MD5HashGenerator {

    public static String generateHash(String word) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(word.getBytes(), 0, word.length());
            String signature = new BigInteger(1, md5.digest()).toString(16);
            return signature;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5HashGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";

    }
}
