package org.green.test.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
    
    // 비밀번호를 넣으면 암호화된 64글자 문자열을 돌려주는 메서드
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("암호화 알고리즘을 찾을 수 없습니다.", e);
        }
    }
}