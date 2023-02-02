

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ABEUtils {

    public static Element HashE(byte[] m, Field group){

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("null");
            return null;
        }
        digest.update(m);
        byte[] hash = digest.digest();
        return group.newElementFromHash(hash, 0, hash.length);

    }

    public static Element HashE(byte[][] ms, Field group){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        for (int i = 0; i < ms.length; i++) {
            digest.update(ms[i]);
        }
        byte[] hash = digest.digest();
        return group.newElementFromHash(hash, 0, hash.length);

    }
}
