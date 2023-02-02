import it.unisa.dia.gas.jpbc.Element;

import java.util.EventListener;
import java.util.Random;
public class MABEEnc {
    private MABEPP pp;
    //private Random random;
    //private HashUtils utils;
    private MABEMPK mpk;
    private EKGENPUBEK ek;
    private EKGENPUBS S;
    private EKGENPRIEK_ ek_;
    private MABEMSK msk;
    private EKGENPUBU u;
    private VerifyPairing p;

    public MABEEnc(MABEPP pp,MABEMPK mpk,EKGENPUBEK ek,EKGENPUBS S,EKGENPRIEK_ ek_,MABEMSK msk,EKGENPUBU u){
        this.pp = pp;
        //this.random = random;
        //this.utils = utils;
        this.mpk = mpk;
        this.ek = ek;
        this.S = S;
        this.ek_ = ek_;
        this.msk = msk;
        this.u = u;
    }
    public Cipher enc(Element m,LSSSMatrix policy,int n){
        HashUtils utils = new HashUtils(pp);
        Element s1 = this.pp.getPairing().getZr().newRandomElement().getImmutable();
        Element s2 = this.pp.getPairing().getZr().newRandomElement().getImmutable();
        Element h1 = this.mpk.getH1();
        Element h2 = this.mpk.getH2();
        Element h = this.pp.geth();
        Element t1 = this.mpk.getT1();
        Element t2 = this.mpk.getT2();
        // encoding the policy matrix
        int n1 = policy.getRows();
        int n2 = policy.getCols();
        int z = 3;
        Element[][] cts = new Element[n1+1][z];
        cts[0][0] = h1.powZn(s1).getImmutable();
        cts[0][1] = h2.powZn(s2).getImmutable();
        cts[0][2] = h.powZn(s1.duplicate().add(s2)).getImmutable();
        Element tmp1;
        Element[][][] tmp_hash3es1 = new Element[n1][z][2];
        for (int i = 0; i < n1; i++) {
            for (int l = 0; l < z; l++) {
                for (int t = 0; t < 2; t++) {
                    tmp_hash3es1[i][l][t] = utils.hash(
                            policy.getMap()[i].getBytes(), l+1, t+1
                    );
                }
            }
        }
        Element[][][] tmp_hash3es2 = new Element[n2][z][2];
        for (int j = 0; j < n2; j++) {
            for (int l = 0; l < z; l++) {
                for (int t = 0; t < 2; t++) {
                    tmp_hash3es2[j][l][t] = utils.hash(
                            0, j+1, l+1, t+1
                    );
                }
            }
        }
        for (int i = 0; i < n1; i++) {
            for (int l = 0; l < z; l++) {
                cts[i+1][l] = tmp_hash3es1[i][l][0].duplicate().powZn(s1).mul(
                        tmp_hash3es1[i][l][1].duplicate().powZn(s2)
                );
                for (int j = 0; j < n2; j++) {
                    tmp1 = tmp_hash3es2[j][l][0].duplicate().powZn(s1).mul(
                            tmp_hash3es2[j][l][1].duplicate().powZn(s2)
                    ).powZn(policy.getMatrix().getValue(i, j));
                    cts[i+1][l] = cts[i+1][l].mul(tmp1);
                }
                cts[i+1][l] = cts[i+1][l].getImmutable();
            }
        }

        // finally step
        Element ctp = t1.powZn(s1).mul(t2.powZn(s2)).mul(m).getImmutable();
        Element r = this.pp.getPairing().getZr().newRandomElement().getImmutable();
        Element r_ = this.pp.getPairing().getZr().newRandomElement().getImmutable();
        Element ek1 = this.ek.getEk1().powZn(r_).getImmutable();
        Element ek2 = this.ek.getEk2().powZn(r_).getImmutable();
        Element ek__1 = this.ek_.getEk_1().powZn(r_).getImmutable();
        Element ek__2 = this.ek_.getEk_2().powZn(r_).getImmutable();
        Element tempk = this.pp.getH().newOneElement();

        for (int i = 1; i <= n; i++){
            tempk = tempk.mul(this.mpk.getPk3()[i].powZn(this.S.getS()[i]));
        }
        Element tempk_ = this.pp.getH().newOneElement();
        for (int i = 1; i <= n; i++){
            tempk_  = tempk_.mul(this.mpk.getPk3()[i].powZn(this.S.getS()[i]));
        }
        Element k = this.mpk.getPk2().mul(tempk).mul(this.pp.geth().powZn(r)).getImmutable();
        Element k_ = this.mpk.getPk2().mul(tempk_).mul(this.pp.geth().powZn(r)).getImmutable();
        Element t = ek1.powZn(r);
        Element kmul = this.pp.getH().newOneElement();
        for (int i = 1; i <= n; i++){
            kmul = kmul.mul(this.pp.geth().powZn(this.msk.getBeta()[i].mul(this.S.getS()[i]).add(r)));
        }
        Element ktemp = this.pp.geth().powZn(this.msk.getAlpha()).mul(kmul).getImmutable();
        Element k_mul = this.pp.getH().newOneElement();
        for (int i = 1; i <= n; i++){
            k_mul = k_mul.mul(this.pp.geth().powZn(this.msk.getBeta()[i].mul(this.S.getS()[i]).add(r)));
        }
        Element k_temp = this.pp.geth().powZn(this.msk.getAlpha()).mul(k_mul).getImmutable();
        Element ttemp = this.pp.geth().powZn(this.u.getU().mul(r)).getImmutable();
        this.p = new VerifyPairing(ek1,ek2,k,k_,t,ek__1,ek__2);
        return new Cipher(policy, cts, ctp);
    }

    public VerifyPairing getP() {
        return p;
    }
}
