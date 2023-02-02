import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.plaf.jpbc.pbc.curve.PBCTypeDCurveGenerator;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
public class EKGenPub {
    private EKGENPUBEK ek;
    private EKGENPUBS S;
    private EKGENPUBU u;


//    public EKGenPub(MABEMSK msk,MABEPP pp,int n){
//        Element S[] = new Element[n+1];
//        for (int i = 1; i <= n; i++) {
//            S[i] = pp.getPairing().getZr().newRandomElement().getImmutable();
//        }
//        Element u = pp.getPairing().getZr().newRandomElement().getImmutable();
//        Element ek1 = pp.getg().powZn(u).getImmutable();
//        Element temp  = pp.getg().setToOne();
//        for (int i = 1; i<=n;i++){
//            temp = temp.mul(pp.getg().powZn(msk.getBeta()[i].mul(S[i]).mul(u))).getImmutable();
//        }
//
//
//        Element ek2 = pp.getg().powZn(msk.getAlpha().mul(u)).mul(temp).getImmutable();
//
//        this.ek = new EKGENPUBEK(ek1,ek2);
//        this.S = new EKGENPUBS(S);
//        this.u = new EKGENPUBU(u);
//    }
    public void EKGenPub1(MABEMSK msk,MABEPP pp,int n){
        Element S[] = new Element[n+1];
        for (int i = 1; i <= n; i++) {
            S[i] = pp.getPairing().getZr().newRandomElement().getImmutable();
        }
        Element u = pp.getPairing().getZr().newRandomElement().getImmutable();
        Element ek1 = pp.getg().powZn(u).getImmutable();
        //Element temp  = pp.getg().setToOne();
        Element temp = pp.getG().newOneElement();

        for (int i = 1; i<=n;i++){
            temp = temp.mul(pp.getg().powZn(msk.getBeta()[i].mul(S[i]).mul(u)));
        }


        Element ek2 = pp.getg().powZn(msk.getAlpha().mul(u)).mul(temp).getImmutable();

        this.ek = new EKGENPUBEK(ek1,ek2);
        this.S = new EKGENPUBS(S);
        this.u = new EKGENPUBU(u);
    }

    public EKGENPUBEK getEk() {
        return ek;
    }


    public EKGENPUBS getS() {
        return S;
    }

    public EKGENPUBU getU() {
        return u;
    }
}
