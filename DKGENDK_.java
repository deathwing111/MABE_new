import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.plaf.jpbc.pbc.curve.PBCTypeDCurveGenerator;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
public class DKGENDK_ {
    private Element dk_t1;
    private Element dk_t2;
    private Element t;

    public DKGENDK_(Element dk_t1, Element dk_t2, Element t) {
        this.dk_t1 = dk_t1;
        this.dk_t2 = dk_t2;
        this.t = t;
    }

    public Element getDk_t1() {
        return dk_t1;
    }

    public Element getDk_t2() {
        return dk_t2;
    }

    public Element getT() {
        return t;
    }
}
