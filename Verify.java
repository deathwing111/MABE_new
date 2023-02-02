import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.plaf.jpbc.pbc.curve.PBCTypeDCurveGenerator;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
public class Verify {
    public void verify(VerifyPairing p,MABEPP pp){
        Element v1 = pp.getPairing().pairing(p.getEk1(),p.getK()).getImmutable();
        Element v2 = pp.getPairing().pairing(p.getEk2().mul(p.getT()),pp.geth()).getImmutable();
        Element v3 = pp.getPairing().pairing(p.getEk__1(),p.getK_()).getImmutable();
        Element v4 = pp.getPairing().pairing(p.getEk__2().mul(p.getT()),pp.geth()).getImmutable();

    }
}
