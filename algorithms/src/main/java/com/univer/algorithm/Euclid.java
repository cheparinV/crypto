package com.univer.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ASUS-PC on 29.03.2017.
 */
public class Euclid {

    private List<BigInteger> aList;
    private List<BigInteger> bList;
    private List<BigInteger> aModBList;
    private List<BigInteger> aDivBList;
    private LinkedList<BigInteger> xList;
    private LinkedList<BigInteger> yList;
    private BigInteger A;
    private BigInteger B;
    private BigInteger X;
    private BigInteger Y;
    private BigInteger NOD;

    public Euclid() {
    }

    public Euclid countEuclid(BigInteger number1, BigInteger number2) {
        aList = new ArrayList<>();
        bList = new ArrayList<>();
        aModBList = new ArrayList<>();
        aDivBList = new ArrayList<>();
        xList = new LinkedList<>();
        yList = new LinkedList<>();

        aList.add(number1);
        bList.add(number2);
        BigInteger aModB = number1.mod(number2);
        //System.out.println(aModB);
        BigInteger aDivB = number1.divide(number2);
        //System.out.println(aDivB);
        aModBList.add(aModB);
        aDivBList.add(aDivB);

        while (!aModB.equals(BigInteger.ZERO)) {
            number1 = number2;
            number2 = aModB;
            aList.add(number1);
            bList.add(number2);
            aModB = number1.mod(number2);
            aDivB = number1.divide(number2);
         //   System.out.println("mod = " + aModB + "; div = " + aDivB);
            aModBList.add(aModB);
            aDivBList.add(aDivB);
        }

        xList.add(0, BigInteger.ZERO);
        yList.add(0, BigInteger.ONE);

        for (int i = aDivBList.size() - 2; i >= 0; i--) {
            BigInteger xFirst = xList.getFirst();
            xList.add(0, yList.getFirst());
            BigInteger yValue = xFirst.subtract(yList.getFirst().multiply(aDivBList.get(i)));
            yList.add(0, yValue);
        }
        this.X = xList.get(0);
        this.Y = yList.get(0);
       // bList.forEach(System.out::println);
        this.NOD = bList.get(bList.size()-1);
        return this;
    }

    public List<BigInteger> getaList() {
        return aList;
    }

    public List<BigInteger> getbList() {
        return bList;
    }

    public List<BigInteger> getaModBList() {
        return aModBList;
    }

    public List<BigInteger> getaDivBList() {
        return aDivBList;
    }

    public LinkedList<BigInteger> getxList() {
        return xList;
    }

    public LinkedList<BigInteger> getyList() {
        return yList;
    }

    public String getX() {
        return X.toString();
    }

    public String getY() {
        return Y.toString();
    }

    public String getNOD() {
        return NOD.toString();
    }


    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s1 = reader.readLine();
            String s2 = reader.readLine();
            BigInteger number1 = new BigInteger(s1);
            BigInteger number2 = new BigInteger(s2);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
