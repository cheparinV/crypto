package controller;

import algorithm.Euclid;
import algorithm.Pollard;
import algorithm.PowMod;
import algorithm.RSA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.math.BigInteger;


/**
 * Created by ASUS-PC on 16.05.2017.
 */
public class MainController {
    @FXML
    private TextField tittelA;
    @FXML
    private TextField tittelB;
    @FXML
    private TextField tittelNOD;
    @FXML
    private TextField tittelX;
    @FXML
    private TextField tittelY;

    @FXML
    private TextField powNum;
    @FXML
    private TextField powE;
    @FXML
    private TextField powMod;
    @FXML
    private TextField powRes;

    @FXML
    private TextField rsaQ;
    @FXML
    private TextField rsaP;
    @FXML
    private TextField rsaN;
    @FXML
    private TextField rsaF;
    @FXML
    private TextField rsaSize;
    @FXML
    private TextField rsaE;
    @FXML
    private TextField rsaD;
    @FXML
    private TextField rsaText;
    @FXML
    private TextField rsaCrypt;
    @FXML
    private TextField rsaDecrypt;

    @FXML
    private TextField polN;
    @FXML
    private TextField polE;
    @FXML
    private TextField polSW;
    @FXML
    private TextField polText;


    private RSA rsa;


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

    @FXML
    protected void handleGenerateKeys(ActionEvent event) {
        this.rsa = new RSA();
        int size = Integer.parseInt(
                rsaSize.getText()
        );
        this.rsa.generateAll(size);
        rsaP.setText(this.rsa.getP().toString());
        rsaQ.setText(this.rsa.getQ().toString());
        rsaN.setText(this.rsa.getN().toString());
        rsaF.setText(this.rsa.getfN().toString());
        rsaD.setText(this.rsa.getD().toString());
        rsaE.setText(this.rsa.getE().toString());
    }

    @FXML
    protected void handleCrypt(ActionEvent event) {
        final String encrypt = this.rsa.encrypt(
                rsaText.getText()
        );
        rsaCrypt.setText(
                encrypt
        );
    }

    @FXML
    protected void handleDecrypt(ActionEvent event) {
        final String decrypt = this.rsa.decrypt(
                rsaCrypt.getText()
        );
        rsaDecrypt.setText(
                decrypt
        );
    }

    @FXML
    protected void handlePol(ActionEvent event) {
        final Pollard pollard = new Pollard();
        final String decrypt = pollard.decrypt(
                polN.getText(),
                polE.getText(),
                polSW.getText()
        );
        polText.setText(
                decrypt
        );
    }


}
