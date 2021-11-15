package nytro.other;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    // Source: https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
    // function to generate a random string of length n
    public static String getAlphaNumericString(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public static String generatePwd(String passwordToHash) {
        String generatedPassword = null;
        String salt = "nytro";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static boolean checkImg(String string) {
        if (string == null) {
            return false;
        }
        System.out.println("File name: " + string);
        String[] types = string.split("\\.");
        String type = null;
        for (String t : types) {
            System.out.println("Tipo: " + t);
            type = t;
        }
        System.out.println("type : " + type);
        if (type.equals("png") || type.equals("jpg") || type.equals("jpeg")) {
            System.out.println("It's an image");
            return true;
        } else {
            System.out.println("It's NOT an image");
            return false;
        }
    }
}
