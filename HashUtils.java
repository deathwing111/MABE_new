
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class HashUtils {
    public static MABEPP pp;

    public HashUtils(MABEPP pp){
        this.pp = pp;
    }

    public static Element hash(byte[] m, int i, int j) {

        byte[] message = new byte[m.length + 2];
        System.arraycopy(m, 0, message, 0, m.length);
        message[m.length] = (byte) i;
        message[m.length + 1] = (byte) j;

        return ABEUtils.HashE(message, pp.getG());
    }

    public static Element hash(int i, int j, int k, int l) {
        byte[] message = new byte[] { (byte) i, (byte) j, (byte) k, (byte) l };
        return ABEUtils.HashE(message, pp.getG());
    }
}
