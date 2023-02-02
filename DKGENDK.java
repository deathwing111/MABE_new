import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.plaf.jpbc.pbc.curve.PBCTypeDCurveGenerator;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
public class DKGENDK {
    private DKGENDK0 dk0;
    private Element[][] dk_yt;
    private DKGENDK_ dk_;

    public DKGENDK(DKGENDK0 dk0, Element[][] dk_yt, DKGENDK_ dk_) {
        this.dk0 = dk0;
        this.dk_yt = dk_yt;
        this.dk_ = dk_;
    }

    public DKGENDK0 getDk0() {
        return dk0;
    }

    public Element[][] getDk_yt() {
        return dk_yt;
    }

    public DKGENDK_ getDk_() {
        return dk_;
    }
}
