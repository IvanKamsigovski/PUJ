package kontakti.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import kontakti.model.LogiraniKorisnikModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController implements Initializable {

    @FXML
    Label statusLbl;
    
    @FXML
    TextField kimeTxt;
    
    @FXML
    PasswordField lozinkaTxt;
    
    public void prijavise (ActionEvent e) {
        String kime = kimeTxt.getText();
        String lozinka = lozinkaTxt.getText();

        if (kime.equals("") || lozinka.equals("")) {
            statusLbl.setTextFill(Color.RED);
            statusLbl.setText("Morate unijeti sve vrijednosti!");
        } else {
            if (LogiraniKorisnikModel.logiraj(kime, lozinka)) {
                try {
                    statusLbl.setTextFill(Color.GREEN);
                    statusLbl.setText("Uspješno ste se prijavili");
                    Parent root;
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("kontakti/view/Kontakti.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Prikaz svih kontakata u Bazi podataka");
                    stage.setScene(new Scene(root, 900, 500));
                    stage.show();
                    statusLbl.getScene().getWindow().hide();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                statusLbl.setTextFill(Color.RED);
                statusLbl.setText("Korisnicki podatci nisu ispravni!");
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
