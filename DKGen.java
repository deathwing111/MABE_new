import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.plaf.jpbc.pbc.curve.PBCTypeDCurveGenerator;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class DKGen {
    private DKGENDK0 dk0;
    private DKGENDKY dky;
    private DKGENDK_ dk_;
    private DKGENDK dk;
//    public DKGen(MABEPP pp,MABEMSK msk,String R){//R是属性
//        Element r1 = pp.getPairing().getZr().newRandomElement().getImmutable();
//        Element r2 = pp.getPairing().getZr().newRandomElement().getImmutable();
//        Element dk1 = pp.geth().powZn(msk.getB1().mul(r1)).getImmutable();
//        Element dk2 = pp.geth().powZn(msk.getB2().mul(r2)).getImmutable();
//        Element dk3 = pp.geth().powZn(r1.add(r2));
//        this.dk0 = new DKGENDK0(dk1,dk2,dk3);
//        Element muy = pp.getPairing().getZr().newRandomElement().getImmutable();
//        Element mu_ = pp.getPairing().getZr().newRandomElement().getImmutable();//mu撇
//        byte[] y = R.getBytes();
//        Element[] dk_yt = new Element[3];//循环计算
//        for (int t = 1;t <= 2; t++){
//            dk_yt[t] = HashUtils.hash(y,1,t).powZn(msk.getB1().mul(r1).div(
//                    (t==1)?msk.getA1():msk.getA2()
//            )).mul(HashUtils.hash(y,2,t).powZn(msk.getB2().mul(r2).div(
//                    (t==1)?msk.getA1():msk.getA2()
//            )).mul(HashUtils.hash(y,3,t).powZn(r1.add(r2).div(
//                    (t==1)?msk.getA1():msk.getA2()
//            )).mul(pp.getg().powZn(muy.div((t==1)?msk.getA1():msk.getA2()))))).getImmutable();
//        }
//        Element g_muy = pp.getg().powZn(muy.invert());
//        this.dky = new DKGENDKY(dk_yt[1],dk_yt[2],g_muy);
//        Element[] dk_t = new Element[3];
//        for (int t = 1; t <= 2; t++){
//            dk_t[t] = ((t==1)?msk.getG_d1():msk.getG_d2()).mul(
//                    HashUtils.hash(0,1,1,t).powZn(msk.getB1().mul(r1).div(
//                            (t==1)?msk.getA1():msk.getA2()
//                    )).mul(HashUtils.hash(0,1,2,t).powZn(msk.getB2().mul(r2).div(
//                            (t==1)?msk.getA1():msk.getA2()
//                    )).mul(HashUtils.hash(0,1,3,t).powZn(r1.add(r2).div(
//                            (t==1)?msk.getA1():msk.getA2()
//                    )).mul(pp.getg().powZn(mu_.div(
//                            (t==1)?msk.getA1():msk.getA2()
//                    )))))).getImmutable();//mul()?
//        }
//        Element t = msk.getG_d3().mul(pp.getg().powZn(mu_.invert()));
//        this.dk_ = new DKGENDK_(dk_t[1],dk_t[2],t);//SETdk_
//        this.dk = new DKGENDK(dk0,dk_yt,dk_);
//
//    }
    public void dkgen(MABEPP pp,MABEMSK msk,String[] R){//R是属性
        //HashUtils hash = new HashUtils(pp);

        Element r1 = pp.getPairing().getZr().newRandomElement().getImmutable();
        Element r2 = pp.getPairing().getZr().newRandomElement().getImmutable();
        Element dk1 = pp.geth().powZn(msk.getB1().mul(r1)).getImmutable();
        Element dk2 = pp.geth().powZn(msk.getB2().mul(r2)).getImmutable();
        Element dk3 = pp.geth().powZn(r1.add(r2)).getImmutable();
        this.dk0 = new DKGENDK0(dk1,dk2,dk3);
        //Element muy = pp.getPairing().getZr().newRandomElement().getImmutable();
        Element mu_ = pp.getPairing().getZr().newRandomElement().getImmutable();//mu撇

        int n = R.length;
        Element[] muy = new Element[n + 1];
        byte[] y = new byte[n + 1];
        Element[][] dk_yt = new Element[n+1][4];//下标123列不为空，0列为空
        HashUtils hashutils = new HashUtils(pp);
        Element[]  g_muy = new Element[n + 1];
        Element[][] dky = new Element[n+1][4];
        for (int i = 0; i < n; i++){
            muy[i] = pp.getPairing().getZr().newRandomElement().getImmutable();
            y = R[i].getBytes();
            for (int t = 1; t <= 2; t++){
                dk_yt[i][t] = hashutils.hash(y,1,t).powZn(msk.getB1().mul(r1).div(
                        (t==1)?msk.getA1():msk.getA2()
                )).mul(hashutils.hash(y,2,t).powZn(msk.getB2().mul(r2).div(
                        (t==1)?msk.getA1():msk.getA2()
                )).mul(hashutils.hash(y,3,t).powZn(r1.add(r2).div(
                        (t==1)?msk.getA1():msk.getA2()
                )).mul(pp.getg().powZn(muy[i].div((t==1)?msk.getA1():msk.getA2()))))).getImmutable();
            }
             g_muy[i] = pp.getg().powZn(muy[i].invert());
//            dky[i][1] = dk_yt[i][1];
//            dky[i][2] = d
            dk_yt[i][3] = g_muy[i];
        }






        //this.dky = new DKGENDKY(dk_yt[][1],dk_yt[][2],g_muy);
        Element[] dk_t = new Element[3];
        for (int t = 1; t <= 2; t++){
            dk_t[t] = ((t==1)?msk.getG_d1():msk.getG_d2()).mul(
                    hashutils.hash(0,1,1,t).powZn(msk.getB1().mul(r1).div(
                            (t==1)?msk.getA1():msk.getA2()
                    )).mul(hashutils.hash(0,1,2,t).powZn(msk.getB2().mul(r2).div(
                            (t==1)?msk.getA1():msk.getA2()
                    )).mul(hashutils.hash(0,1,3,t).powZn(r1.add(r2).div(
                            (t==1)?msk.getA1():msk.getA2()
                    )).mul(pp.getg().powZn(mu_.div(
                            (t==1)?msk.getA1():msk.getA2()
                    )))))).getImmutable();//mul()?
        }
        Element t = msk.getG_d3().mul(pp.getg().powZn(mu_.invert()));
        this.dk_ = new DKGENDK_(dk_t[1],dk_t[2],t);//SETdk_
        this.dk = new DKGENDK(dk0,dk_yt,dk_);

    }

    public DKGENDKY getDky() {
        return dky;
    }

    public DKGENDK0 getDk0() {
        return dk0;
    }

    public DKGENDK_ getDk_() {
        return dk_;
    }

    public DKGENDK getDk() {
        return dk;
    }
}
