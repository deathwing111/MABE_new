import it.unisa.dia.gas.jpbc.Element;

public class EKGENPRICS {
    private Element c_s;
    private Element d;
    public EKGENPRICS(Element c_s,Element d) {
        this.c_s = c_s;
        this.d = d;
    }

    public Element getC_s() {
        return c_s;
    }

    public Element getD() {
        return d;
    }
}
