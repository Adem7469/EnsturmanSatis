package application;

import java.net.URL;

import java.util.ResourceBundle;

import com.ademMYsql.utili.VeritabaniUtili;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.lang.Exception;
import javafx.scene.Node;


import java.sql.*;

public class SampleController {
 
	public SampleController() {
		baglanti=VeritabaniUtili.Baglanti();
		
		
	}
	
	
	
	@FXML
	void goToYenikayit(ActionEvent event) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Yenikayit.fxml"));
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
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_giris;

    @FXML
    private Button btn_kayit;
    
    @FXML
    private Label lbl_uyarı;

    @FXML
    private TextField txt_kulsnici;
 
    @FXML
    private TextField txt_sifre;
     
    Connection baglanti=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null;
    String sql;
    
   
    
    @FXML
    void btn_giris_click(ActionEvent event) {
        sql = "select * from login where KulaniciAdı=? and Sifre=?";
        try {
            sorguifadesi = baglanti.prepareStatement(sql);
            sorguifadesi.setString(1, txt_kulsnici.getText().trim());
            sorguifadesi.setString(2, txt_sifre.getText().trim());

            ResultSet getirilen = sorguifadesi.executeQuery();
            if (getirilen.next()) {
                String yetki = getirilen.getString("Yetki");
               
                if (yetki.equals("1")) {
                    // Yetki = 1 ise Urunler formuna git
                    try {
                    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Urunler.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (yetki.equals("0")) {
                    // Yetki = 0 ise Siparisverme formuna git
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
            } else {
                lbl_uyarı.setText("Kulanici Adı veya Şifre Hatalı");
            }
        } catch (Exception e) {
            lbl_uyarı.setText(e.getMessage().toString());
            e.printStackTrace();
        }
    }
    @FXML
    void btn_kayit_click(ActionEvent event) {
    	
    	goToYenikayit(event);
    	
    }


    @FXML
    void initialize() {
        
    }

}