
import it.unisa.dia.gas.jpbc.Element;

public class EKGenPri {
    private EKGENPRICS c_s;
    private EKGENPRISIGMA sigma;
    private EKGENPRIEK_ ek_;
    public void Prepare(MABEPP pp, MABEMPK mpk, int n, EKGENPUBS S, MABEMSK msk){//return zk
        Element d = pp.getPairing().getZr().newRandomElement().getImmutable();
        Element temp1 = pp.getG().newOneElement();//初值为群上的单位元
        for (int i = 1; i<=n;i++)
        temp1 = temp1.mul(mpk.getPk1()[i].powZn(S.getS()[i])).getImmutable();
        Element c_s = pp.getg().powZn(d).mul(temp1).getImmutable();
        Element temp2 = pp.getG().newOneElement();
        for (int i = 1; i <= n; i++){
            temp2 = temp2.mul(pp.getg().powZn(msk.getBeta()[i].mul(S.getS()[i]))).getImmutable();
        }
        Element t = pp.getg().powZn(d).mul(temp2).getImmutable();
        this.c_s = new EKGENPRICS(c_s,d);
    }

    public EKGENPRICS getC_s() {
        return c_s;
    }


    public void BlindSign(EKGENPUBU u, MABEPP pp,EKGENPRICS c_s,MABEMSK msk){//return ek_
        Element sigma1 = pp.getg().powZn(u.getU()).getImmutable();
        Element sigma2 = pp.getg().powZn(msk.getAlpha()).mul(c_s.getC_s()).powZn(u.getU()).getImmutable();
        this.sigma = new EKGENPRISIGMA(sigma1,sigma2);
    }

    public EKGENPRISIGMA getSigma() {
        return sigma;
    }

    public void Unblind(EKGENPRISIGMA sigma,EKGENPRICS d){
        Element ek_1 = sigma.getSigma1().getImmutable();
        Element ek_2 = sigma.getSigma2().mul(sigma.getSigma1().powZn(d.getD().invert())).getImmutable();
        this.ek_ = new EKGENPRIEK_(ek_1,ek_2);
    }

    public EKGENPRIEK_ getEk_() {
        return ek_;
    }
}
