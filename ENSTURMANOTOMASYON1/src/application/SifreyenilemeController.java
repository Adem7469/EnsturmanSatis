package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ResourceBundle;

import com.ademMYsql.utili.VeritabaniUtili;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SifreyenilemeController {

	public SifreyenilemeController() {
		baglanti=VeritabaniUtili.Baglanti();
		
		
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_geri;

    @FXML
    private Button btn_kaydet;

    @FXML
    private Label lbl_mesaj;

    @FXML
    private TextField txt_kulanici;

    @FXML
    private TextField txt_sifre;

    @FXML
    private TextField txt_yenisifre;

    @FXML
    private TextField txt_yenisifretekrari;
    
    Connection baglanti=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null;
    String sql;
    
    @FXML
	void goToSiparisVerme(ActionEvent event) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Siparisverme.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

    @FXML
    void btn_geri_click(ActionEvent event) {

    	goToSiparisVerme(event);
    }
    
    @FXML
    void btn_kaydet_click(ActionEvent event) {
        String kullaniciAdi = txt_kulanici.getText().trim();
        String eskiSifre = txt_sifre.getText().trim();
        String yeniSifre = txt_yenisifre.getText().trim();
        String yeniSifreTekrar = txt_yenisifretekrari.getText().trim();
        
        // Kullanıcının eski şifresini kontrol et
        sql = "SELECT * FROM login WHERE KulaniciAdı=? AND Sifre=?";
        try {
            sorguifadesi = baglanti.prepareStatement(sql);
            sorguifadesi.setString(1, kullaniciAdi);
            sorguifadesi.setString(2, eskiSifre);

            ResultSet getirilen = sorguifadesi.executeQuery();
            if (getirilen.next()) {
                if (yeniSifre.equals(yeniSifreTekrar)) {
                    // Yeni şifreyi güncelle
                    sql = "UPDATE login SET Sifre=? WHERE KulaniciAdı=?";
                    try {
                        sorguifadesi = baglanti.prepareStatement(sql);
                        sorguifadesi.setString(1, yeniSifre);
                        sorguifadesi.setString(2, kullaniciAdi);
                        sorguifadesi.executeUpdate();
                        lbl_mesaj.setText("Şifre Değiştirildi.");
                    } catch (Exception e) {
                        e.printStackTrace();
                        lbl_mesaj.setText("Kayıt Başarısız. Hata: " + e.getMessage());
                    }
                } else {
                    lbl_mesaj.setText("Yeni şifreler eşleşmiyor.");
                }
            } else {
                lbl_mesaj.setText("Kullanıcı Adı veya Şifre Hatalı");
            }

        } catch (Exception e) {
            e.printStackTrace();
            lbl_mesaj.setText("Hata: " + e.getMessage());
        }
    }


    @FXML
    void initialize() {
       
    }

}