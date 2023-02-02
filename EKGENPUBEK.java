import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.plaf.jpbc.pbc.curve.PBCTypeDCurveGenerator;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
public class EKGENPUBEK {
    private Element ek1;
    private Element ek2;

    public EKGENPUBEK(Element ek1, Element ek2) {
        this.ek1 = ek1;
        this.ek2 = ek2;
    }

    public Element getEk1() {
        return ek1;
    }

    public Element getEk2() {
        return ek2;
    }
}
