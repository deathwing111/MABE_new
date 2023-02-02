import it.unisa.dia.gas.jpbc.Element;

public class MABEMPK {
    private Element[] pk1;
    private Element pk2;
    private Element[] pk3;
    private Element H1;
    private Element H2;
    private Element T1;
    private Element T2;

    public MABEMPK(Element[] pk1, Element pk2, Element[] pk3, Element H1, Element H2, Element T1, Element T2) {
        this.pk1 = pk1;
        this.pk2 = pk2;
        this.pk3 = pk3;
        this.H1 = H1;
        this.H2 = H2;
        this.T1 = T1;
        this.T2 = T2;
    }

    public Element getH1() {
        return H1;
    }

    public Element getH2() {
        return H2;
    }

    public Element[] getPk1() {
        return pk1;
    }

    public Element getPk2() {
        return pk2;
    }

    public Element[] getPk3() {
        return pk3;
    }

    public Element getT1() {
        return T1;
    }

    public Element getT2() {
        return T2;
    }
}
