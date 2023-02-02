import it.unisa.dia.gas.jpbc.Element;

public class VerifyPairing {
    private Element ek1;
    private Element ek2;
    private Element k;
    private Element k_;
    private Element t;
    private Element ek__1;
    private Element ek__2;
    public VerifyPairing(Element ek1, Element ek2, Element k, Element k_, Element t, Element ek__1, Element ek__2) {
        this.ek1 = ek1;
        this.ek2 = ek2;
        this.k = k;
        this.k_ = k_;
        this.t = t;
        this.ek__1 = ek__1;
        this.ek__2 = ek__2;
    }

    public Element getT() {
        return t;
    }

    public Element getEk2() {
        return ek2;
    }

    public Element getEk1() {
        return ek1;
    }

    public Element getEk__1() {
        return ek__1;
    }

    public Element getEk__2() {
        return ek__2;
    }

    public Element getK() {
        return k;
    }

    public Element getK_() {
        return k_;
    }
}
