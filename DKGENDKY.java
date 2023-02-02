import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.plaf.jpbc.pbc.curve.PBCTypeDCurveGenerator;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
public class DKGENDKY {
    private Element[][] dk_yt1;
    private Element[][] dk_yt2;
    private Element[] g_muy;

    public DKGENDKY(Element[][] dk_yt1, Element[][] dk_yt2, Element[] g_muy) {
        this.dk_yt1 = dk_yt1;
        this.dk_yt2 = dk_yt2;
        this.g_muy = g_muy;
    }

    public Element[][] getDk_yt1() {
        return dk_yt1;
    }

    public Element[][] getDk_yt2() {
        return dk_yt2;
    }

    public Element[] getG_muy() {
        return g_muy;
    }
}
