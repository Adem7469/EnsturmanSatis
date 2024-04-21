package application;

import java.net.URL;
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

import java.sql.*;
import java.time.LocalDate;



public class YenikayitController {
	public YenikayitController() {
		baglanti=VeritabaniUtili.Baglanti();
		
		
			
		
	}
	
	@FXML
	private Button btn_geri;

	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_kaydet;

    @FXML
    private Label lbl_mesaj;

    @FXML
    private TextField txt_kulanici;

    @FXML
    private TextField txt_sifre;

    @FXML
    private TextField txt_sifretekrar;

    Connection baglanti=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null;
    String sql;
    
    @FXML
    void btn_geri_click(ActionEvent event) {

    	 try {
 	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
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
    void btn_kaydet_click(ActionEvent event) {
        String kullaniciAdi = txt_kulanici.getText().trim();
        String sifre = txt_sifre.getText();
        String sifreTekrar = txt_sifretekrar.getText();

        // Kullanıcı adına sahip kayıtlı kullanıcıları kontrol et
        if (kullaniciVarMi(kullaniciAdi)) {
            lbl_mesaj.setText("Bu kullanıcı adı zaten kullanılıyor.");
            return;
        }

        if (sifre.equals(sifreTekrar)) {
            sql = "insert into login(KulaniciAdı, Sifre, KayıtTarihi, Yetki) values(?,?,?,?)";
            try {
                sorguifadesi = baglanti.prepareStatement(sql);
                sorguifadesi.setString(1, kullaniciAdi);
                sorguifadesi.setString(2, sifre);
                sorguifadesi.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                sorguifadesi.setString(4, "0");
                sorguifadesi.executeUpdate();

                lbl_mesaj.setText("Kayıt Başarılı.");
            } catch (Exception e) {
                e.printStackTrace();
                lbl_mesaj.setText("Kayıt Başarısız. Hata: " + e.getMessage());
            }
        } else {
            lbl_mesaj.setText("Şifreler eşleşmiyor.");
        }
    }

    private boolean kullaniciVarMi(String kullaniciAdi) {
        String sql = "SELECT * FROM login WHERE KulaniciAdı = ?";
        try {
            sorguifadesi = baglanti.prepareStatement(sql);
            sorguifadesi.setString(1, kullaniciAdi);
            ResultSet getirilen = sorguifadesi.executeQuery();
            return getirilen.next(); // Eğer sonuç kümesi boş değilse, kullanıcı var demektir.
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    void initialize() {
        
    	
    }

}