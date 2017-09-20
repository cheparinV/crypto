import algorithm.Euclid;
import org.junit.Test;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ASUS-PC on 16.05.2017.
 */
public class EuclidTest {

    Euclid euclid;

    @Test
    public void countEuclid() throws Exception {
        BigInteger b1 = BigInteger.valueOf(180l);
        BigInteger b2 = BigInteger.valueOf(150l);
        euclid = new Euclid();
        euclid.countEuclid(b1, b2);
        List<BigInteger> aList = euclid.getaList();
        List<BigInteger> bList = euclid.getbList();
        List<BigInteger> aModBList = euclid.getaModBList();
        List<BigInteger> aDivBList = euclid.getaDivBList();
        LinkedList<BigInteger> xList = euclid.getxList();
        LinkedList<BigInteger> yList = euclid.getyList();

        System.out.println(euclid.getNOD());
        System.out.println(xList.getFirst());
        System.out.println(yList.getFirst());
    }

}