package kontakti.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KontaktModel {
    SimpleIntegerProperty sifra = new SimpleIntegerProperty();
    SimpleStringProperty ime = new SimpleStringProperty();
    SimpleStringProperty prezime = new SimpleStringProperty();
    SimpleStringProperty Knjiga = new SimpleStringProperty();
    
    public KontaktModel (Integer sifra, String ime, String prezime, String knjiga) {
        this.sifra = new SimpleIntegerProperty (sifra);
        this.ime = new SimpleStringProperty(ime);
        this.prezime = new SimpleStringProperty(prezime);
        this.Knjiga = new SimpleStringProperty(knjiga);
    }
    
    public Integer getSifra () {
        return sifra.get();
    }
    
    public String getIme () {
        return ime.get();
    }
    
    public String getPrezime () {
        return prezime.get();
    }
    
    public String getKnjiga () {
        return Knjiga.get();
    }

    public void setIme(String ime) {
        this.ime = new SimpleStringProperty(ime);
    }

    public void setPrezime(String prezime) {
        this.prezime = new SimpleStringProperty(prezime);
    }

    public void setKnjiga(String Knjiga) {
        this.Knjiga = new SimpleStringProperty(Knjiga);
    }

    public void spasi() {
        try{
            PreparedStatement upit = Baza.DB.exec("INSERT INTO kontakt VALUES (null,?,?,?)");
            upit.setString(1, this.getIme());
            upit.setString(2, this.getPrezime());
            upit.setString(3, this.getKnjiga());
            upit.executeUpdate();
        }
        catch(SQLException ex) {System.out.println("Greška prilikom spasavanja korisnika u bazu: "+ ex.getMessage());
        }
    }

    public void uredi() {
        try{
            PreparedStatement upit = Baza.DB.exec("UPDATE kontakt SET ime=?, prezime=?, Knjiga=? WHERE id=?");
            upit.setString(1, this.getIme());
            upit.setString(2, this.getPrezime());
            upit.setString(3, this.getKnjiga());
            upit.setInt(4, this.getSifra());
            upit.executeUpdate();
        }
        catch(SQLException ex) {System.out.println("Greška prilikom spasavanja korisnika u bazu: "+ ex.getMessage());
        }
    }
    public void brisi() {
        try{
            PreparedStatement upit = Baza.DB.exec("DELETE FROM kontakt WHERE id=?");
            upit.setInt(1, this.getSifra());
            upit.executeUpdate();
        }
        catch(SQLException ex) {
            System.out.println("Greška prilikom spasavanja korisnika u bazu: "+ ex.getMessage());
        }
    }
    
    public static ObservableList<KontaktModel> listaKontakata () {
        ObservableList<KontaktModel> lista = FXCollections.observableArrayList();
        
        ResultSet rs = Baza.DB.select("SELECT * FROM kontakt");
        
        try {
            while (rs.next()) {
                lista.add(new KontaktModel(rs.getInt("id"), rs.getString("ime"), rs.getString("prezime"), rs.getString("Knjiga")));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
        return lista;
    }
}

