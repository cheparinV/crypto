package controller;

import algorithm.Euclid;
import algorithm.PowMod;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.math.BigInteger;


/**
 * Created by ASUS-PC on 16.05.2017.
 */
public class MainController {
    @FXML private TextField tittelA;
    @FXML private TextField tittelB;
    @FXML private TextField tittelNOD;
    @FXML private TextField tittelX;
    @FXML private TextField tittelY;

    @FXML private TextField powNum;
    @FXML private TextField powE;
    @FXML private TextField powMod;
    @FXML private TextField powRes;


    @FXML
    protected void handleEuclid(ActionEvent event) {
        BigInteger b1 = new BigInteger(tittelA.getText());
        BigInteger b2 = new BigInteger(tittelB.getText());
        Euclid euclid = new Euclid();
        euclid.countEuclid(b1, b2);
        tittelNOD.setText(euclid.getNOD());
        tittelX.setText(euclid.getX());
        tittelY.setText(euclid.getY());

    }

    @FXML
    protected void handlePowMod(ActionEvent event) {
        BigInteger base = new BigInteger(powNum.getText());
        BigInteger degreeOfBase = new BigInteger(powE.getText());
        BigInteger mod = new BigInteger(powMod.getText());
        BigInteger result = new PowMod().powMod(base, degreeOfBase, mod);
        powRes.setText(result.toString());
    }
}
