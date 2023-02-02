import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.plaf.jpbc.pbc.curve.PBCTypeDCurveGenerator;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
public class MABEPP {
    private  Pairing bp;
    private Element h;
    private Element g;
    private Element egh;
    private Field G;
    private Field H;
    private Field Gt;
    private Element Hash_G;

    public MABEPP(Pairing bp, Field G, Field H, Field Gt, Element egh, Element h,Element g) {

    }



    public MABEPP(Pairing bp, Field G, Field H, Field Gt, Element egh, Element h, Element g, Element Hash_G) {
        this.bp = bp;
        this.G = G;
        this.H = H;
        this.Gt = Gt;
        this.egh = egh;
        this.h = h;
        this.g = g;
        this.Hash_G = Hash_G;
    }

    public Pairing getPairing() {
        return bp;
    }

    public Field getH() {
        return H;
    }
    public Field getG() {
        return G;
    }
    public Field getGt() {
        return Gt;
    }
    public Element getEgh() {
        return egh;
    }
    public Element geth(){
        return h;
    }
    public Element getg(){
        return g;
    }

    public Element getHash_G() {
        return Hash_G;
    }
}
