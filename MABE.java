import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.plaf.jpbc.pbc.curve.PBCTypeDCurveGenerator;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;


import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class MABE {
     public MABEPP pp;
     private MABEMSK msk;
     private MABEMPK mpk;

     public void setup( int n,String m,Pairing bp) {//G、H加法群，Gt乘法群
//     int discriminant = 9563;
//     PBCTypeDCurveGenerator parametersGenerator = new PBCTypeDCurveGenerator(discriminant);
//     PairingParameters pp = parametersGenerator.generate();

     //Pairing bp = PairingFactory.getPairing("d224.properties");
     Field Gt = bp.getGT();
     Element gt = Gt.newRandomElement().getImmutable();
     //System.out.println(gt);
     Field G = bp.getG1();
     Element g = G.newRandomElement().getImmutable();
     Field H = bp.getG2();
     Element h = H.newRandomElement().getImmutable();
     Element egh = bp.pairing(g,h).getImmutable();
     byte[]  byteArray_G = m.getBytes();
     Element Hash_G = ABEUtils.HashE(byteArray_G,G);
     Element alpha = bp.getZr().newRandomElement().getImmutable();
     Element beta[] = new Element[n+1];
     for (int i = 0; i <= n; i++) {
          beta[i] = bp.getZr().newRandomElement().getImmutable();
     }

     Element a1 = bp.getZr().newRandomElement().getImmutable();
     Element a2 = bp.getZr().newRandomElement().getImmutable();
     Element b1 = bp.getZr().newRandomElement().getImmutable();
     Element b2 = bp.getZr().newRandomElement().getImmutable();
     Element d1 = bp.getZr().newRandomElement().getImmutable();
     Element d2 = bp.getZr().newRandomElement().getImmutable();
     Element d3 = bp.getZr().newRandomElement().getImmutable();


     Element g_d1 = g.powZn(d1).getImmutable();
     Element g_d2 = g.powZn(d2).getImmutable();
     Element g_d3 = g.powZn(d3).getImmutable();

     Element H1 = h.powZn(a1).getImmutable();
     Element H2 = h.powZn(a2).getImmutable();

     Element T1 = egh.powZn(d1.mul(a1).add(d3)).getImmutable();
     Element T2 = egh.powZn(d2.mul(a2).add(d3)).getImmutable();

     Element pk1[] = new Element[n+1];
     for (int i = 1; i<= n; i++){
         pk1[i] = g.powZn(beta[i]);
     }
     Element pk2 = h.powZn(alpha);
     Element pk3[] = new Element[n+1];
     for (int i = 1; i <= n; i++){
         pk3[i] = h.powZn(beta[i]);
     }
     this.pp = new MABEPP(
             bp,G,H,Gt,egh,h,g,Hash_G
     );
     this.msk = new MABEMSK(
             alpha,beta,a1,a2,b1,b2,g_d1,g_d2,g_d3
     );
     this.mpk = new MABEMPK(
             pk1,pk2,pk3,H1,H2,T1,T2
     );
     }

     public MABEPP getPp() {
          return pp;
     }

     public MABEMSK getMsk() {
          return msk;
     }

     public MABEMPK getMpk() {
          return mpk;
     }


     public static String[] getAttrs(int attr_len) {
          String[] attrs = new String[attr_len];
          for (int i = 0; i < attr_len; i++) {
               attrs[i] = "Attr-" + i;
          }
          return attrs;
     }

     public static String getPolicy(String[] attrs, int len) {
          int plen = len;
          if (attrs.length < len)
               plen = attrs.length;
          String res = "";
          for (int i = 0; i < plen - 1; i++) {
               res += "Attr-" + i + " & ";
          }
          res += "Attr-" + (plen - 1);
          return res;
     }


     public static void main(String[] args) {
               int loop = 10;
               int n = 45;
               String m = "plaintext";
               //Element m ;
               Random rand = new SecureRandom();
          MABE setup = new MABE();
          Pairing pairing = PairingFactory.getPairing("d224.properties");
          long startTime,endTime;
          startTime = System.currentTimeMillis();
          //for (int i = 1; i < loop; i++) {
              setup.setup(n, m,pairing);
          //}
          endTime = System.currentTimeMillis();
          System.out.println("setup " + (endTime - startTime)/loop + "ms");


          MABEPP pp = setup.getPp();
          MABEMSK msk = setup.getMsk();
          EKGenPub ekGenPub = new EKGenPub();

          startTime = System.currentTimeMillis();
          //for (int i = 1; i < loop; i++) {
               ekGenPub.EKGenPub1(msk,pp,n);
          //}
          endTime = System.currentTimeMillis();
          System.out.println("EKGenPub " + (endTime - startTime)/loop + "ms");

          MABEMPK mpk = setup.getMpk();
          EKGENPUBS S = ekGenPub.getS();
          EKGenPri ekGenPri = new EKGenPri();
          EKGENPUBU u = ekGenPub.getU();


          startTime = System.currentTimeMillis();
          //for (int i = 1; i < loop; i++) {
               ekGenPri.Prepare(pp,mpk,n,S,msk);
               EKGENPRICS c_s = ekGenPri.getC_s();
               ekGenPri.BlindSign(u,pp,c_s,msk);
               EKGENPRISIGMA sigma = ekGenPri.getSigma();
               ekGenPri.Unblind(sigma,c_s);
          //}
          endTime = System.currentTimeMillis();
          System.out.println("EKGenPri " + (endTime - startTime)/loop + "ms");

          DKGen dkGen = new DKGen();
          String[] attrs = { "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9",
                  "a10", "a11", "a12", "a13", "a14" , "a15", "a16", "a17", "a18", "a19",
                  "a20", "a21", "a22", "a23", "a24", "a25", "a26", "a27", "a28", "a29",
                  "a30", "a31", "a32", "a33", "a34", "a35", "a36", "a37", "a38", "a39",
                  "a40", "a41", "a42", "a43", "a44"};//, "a45", "a46", "a47", "a48", "a49"};

          startTime = System.currentTimeMillis();
          //for (int i = 0; i < loop ; i++) {
               dkGen.dkgen(pp,msk,attrs);
          //}
          endTime = System.currentTimeMillis();
          System.out.println("DKGen " + (endTime - startTime)/loop + "ms");


          Element menc = pp.getGt().newRandomElement().getImmutable();

          MABEEnc enc = new MABEEnc(pp,mpk,ekGenPub.getEk(),S,ekGenPri.getEk_(),msk,u);
          //int attrlen = 50;
          String[] attrs1 = getAttrs(n);
          String policy = getPolicy(attrs, attrs.length);
          LSSSEngine lsss = new LSSSEngine();
          LSSSMatrix M = lsss.genMatrix(policy, pp.getPairing().getZr());

          Cipher c = enc.enc(menc,M,n);;
          startTime = System.currentTimeMillis();
          //for (int i = 0; i < loop; i++) {
               c = enc.enc(menc,M,n);
//
         // }
          endTime = System.currentTimeMillis();
          System.out.println("Enc " + (endTime - startTime)/loop + "ms");

          Verify v = new Verify();
          VerifyPairing p = enc.getP();
          startTime = System.currentTimeMillis();
          //for (int i = 0; i < loop; i++) {
               v.verify(p,pp);
         //}
          endTime = System.currentTimeMillis();
          System.out.println("Verify " + (endTime - startTime)/loop + "ms");

          MABEDec dec = new MABEDec();
          //dkGen.getDk();//参数
          //dkGen.getDky();
          startTime = System.currentTimeMillis();
          for (int i = 0; i < loop ; i++) {
               dec.dec(dkGen.getDk(),c,pp,attrs1,dkGen.getDky());
          }
          endTime = System.currentTimeMillis();
          System.out.println("Dec " + (endTime - startTime)/loop + "ms");


     }

}
