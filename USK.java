import it.unisa.dia.gas.jpbc.Element;

public class USK {
    private String[] attrs;
    private Element[][] sks;
    private Element[] skp;

    public USK(String[] attrs, Element[][] sks, Element[] skp) {
        this.attrs = attrs;
        this.sks = sks;
        this.skp = skp;
    }

    public String[] getAttrs() {
        return attrs;
    }

    public Element[][] getSks() {
        return sks;
    }

    public Element[] getSkp() {
        return skp;
    }
}
