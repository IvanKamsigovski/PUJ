package kontakti.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import kontakti.model.KontaktModel;

import java.net.URL;
import java.util.ResourceBundle;

public class KontaktiController implements Initializable {

    @FXML
    Button dodajBtn;
    @FXML
    Button urediBtn;
    @FXML
    Button brisiBtn;
    @FXML
    TextField imeTxtFld;
    @FXML
    TextField prezimeTxtFld;
    @FXML
    TextField knjigaTxtFld;

    @FXML
    TableView kontaktiTbl;
    @FXML
    TableColumn imeTblCol;
    @FXML
    TableColumn prezimeTblCol;
    @FXML
    TableColumn knjigaTblCol;

    KontaktModel odabraniKontakt;
    @FXML
    public void dodajKontakt(ActionEvent e) {
        KontaktModel odabraniKontakt;
        String ime = this.imeTxtFld.getText();
        String prezime = this.prezimeTxtFld.getText();
        String Knjiga = this.knjigaTxtFld.getText();
        KontaktModel novi = new KontaktModel (0, ime, prezime, Knjiga);
        novi.spasi();
    }
    @FXML
    public void odaberiKorisnika(Event e) {
        this.odabraniKontakt = (KontaktModel) this.kontaktiTbl.getSelectionModel().getSelectedItem();
        this.imeTxtFld.setText(this.odabraniKontakt.getIme());
        this.prezimeTxtFld.setText(this.odabraniKontakt.getPrezime());
        this.knjigaTxtFld.setText(this.odabraniKontakt.getKnjiga());
    }
    @FXML
    public void urediKontakt(Event e) {
        this.odabraniKontakt.setIme(this.imeTxtFld.getText());
        this.odabraniKontakt.setPrezime(this.prezimeTxtFld.getText());
        this.odabraniKontakt.setKnjiga(this.knjigaTxtFld.getText());
        this.odabraniKontakt.uredi();
        ObservableList<KontaktModel> data = KontaktModel.listaKontakata();
        this.kontaktiTbl.setItems(data);
    }
    @FXML
    public void brisiKontakt(Event e) {
        if(this.odabraniKontakt!= null) {
            this.odabraniKontakt.brisi();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<KontaktModel> data = KontaktModel.listaKontakata();
        this.kontaktiTbl.setItems(data);
        imeTblCol.setCellValueFactory(new PropertyValueFactory<KontaktModel, String>("Ime"));
        prezimeTblCol.setCellValueFactory(new PropertyValueFactory<KontaktModel, String>("Prezime"));
        knjigaTblCol.setCellValueFactory(new PropertyValueFactory<KontaktModel, String>("Knjiga"));
        kontaktiTbl.setItems(data);
    }


}
