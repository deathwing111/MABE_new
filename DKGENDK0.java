import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.plaf.jpbc.pbc.curve.PBCTypeDCurveGenerator;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
public class DKGENDK0 {//这里合成一个值
    private Element dk1;
    private Element dk2;
    private Element dk3;
    public DKGENDK0(Element dk1, Element dk2, Element dk3) {
        this.dk1 = dk1;
        this.dk2 = dk2;
        this.dk3 = dk3;
    }

    public Element getDk1() {
        return dk1;
    }

    public Element getDk2() {
        return dk2;
    }

    public Element getDk3() {
        return dk3;
    }
}
