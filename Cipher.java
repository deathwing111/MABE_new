import it.unisa.dia.gas.jpbc.Element;


import java.math.BigInteger;
public class Cipher {
    private LSSSMatrix policy;
    private Element[][] cts;
    private Element ctp;

    public Cipher(LSSSMatrix policy, Element[][] cts, Element ctp) {
        this.policy = policy;
        this.cts = cts;
        this.ctp = ctp;
    }

    public LSSSMatrix getPolicy() {
        return policy;
    }

    public Element[][] getCts() {
        return cts;
    }

    public Element getCtp() {
        return ctp;
    }
}
