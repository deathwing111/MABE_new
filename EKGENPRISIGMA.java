import it.unisa.dia.gas.jpbc.Element;

public class EKGENPRISIGMA {
    private Element sigma1;
    private Element sigma2;
    public EKGENPRISIGMA(Element sigma1, Element sigma2) {
        this.sigma1 = sigma1;
        this.sigma2 = sigma2;

    }

    public Element getSigma1() {
        return sigma1;
    }

    public Element getSigma2() {
        return sigma2;
    }
}
