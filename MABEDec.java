import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;

import java.util.Random;
public class MABEDec {
    public Element dec(DKGENDK dk,Cipher cipher,MABEPP pp,String[] attr,DKGENDKY dky){//需要enc写完，返回cipher
        Pairing pairing = pp.getPairing();

        // step 1
        LSSSMatrix policy = cipher.getPolicy();
        int[][] indexes = Utils.search(policy.getMap(), attr);
        // where policy.getMap()[mi[i]] = usk.getAttrs[vi[i]]
        int[] mi = indexes[0];
        int[] ui = indexes[1];

        // compute lambda
        Vector lambda = policy.extract(mi).genLambda();

        int i, j;
        Element[] tmps1 = new Element[3];
        Element[] tmps2 = new Element[3];
        Element lambda_i;//gama
        for (int x = 0; x < 3; x++) {
            tmps1[x] = pairing.getG1().newOneElement();
            //tmps2[x] = dk.getDk0().getDk1();//dk1
        }
        tmps2[0] = dk.getDk0().getDk1();
        tmps2[1] = dk.getDk0().getDk2();
        tmps2[2] = dk.getDk0().getDk3();
        Element[] dknew = new Element[4];
        for (int x = 0; x < mi.length; x++) {
            i = mi[x];
            j = ui[x];//rho下标
            Element[] temp = new Element[j+2];
            temp[x] = pairing.getG1().newOneElement();//
            lambda_i = lambda.getValue(x);
            Element C = pairing.getG1().newRandomElement();
            Element D = pairing.getZr().newRandomElement();
            for (int y = 0; y < 3; y++) {
                tmps1[y] = tmps1[y].mul(
                        cipher.getCts()[i+1][y].powZn(lambda_i)
                );
                Element E = C.powZn(D);
                //for (int k = 0; k < attr.length; k++) {
                    tmps2[y] = tmps2[y].mul(
                            //temp[x].powZn(lambda_i)
                            //dk.getDk_yt()[k][y + 1].powZn(lambda_i)
                            dk.getDk_yt()[1][y + 1].powZn(temp[x])
                    );
                //}
            }
        }

        Element A = cipher.getCtp();
        Element B = pairing.getGT().newOneElement();

        for (int y = 0; y < 3; y++) {
            A = A.mul(
                    pairing.pairing(tmps1[y], dk.getDk0().getDk1())
            );
            //for (int k = 0; k < attr.length; k++) {
                B = B.mul(
                        pairing.pairing(dk.getDk_yt()[1][y + 1], cipher.getCts()[0][y])
                        //pairing.pairing(tmps2[y], cipher.getCts()[0][y])

                );
            //}
        }

        Element m = A.mul(B.invert());

        return m;
    }
}
