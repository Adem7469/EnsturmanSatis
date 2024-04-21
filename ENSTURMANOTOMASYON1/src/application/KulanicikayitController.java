package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.ademMYsql.utili.VeritabaniUtili;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class KulanicikayitController {

	public KulanicikayitController() {
		baglanti=VeritabaniUtili.Baglanti();
		
		
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    

    @FXML
    private Button btn_ekle;
    
    @FXML
    private Button btn_geri;


    @FXML
    private Button btn_güncele;

    @FXML
    private Button btn_sil;

    @FXML
    private TableColumn<Kayitlar_kulanici, Integer> ccol_id;

    @FXML
    private TableColumn<Kayitlar_kulanici, String> col_kulanici;

    @FXML
    private TableColumn<Kayitlar_kulanici, String> col_sifre;

    @FXML
    private TableColumn<Kayitlar_kulanici, Date> col_tarih;

    @FXML
    private TableColumn<Kayitlar_kulanici, Integer> col_yetki;

    @FXML
    private ComboBox<Integer> combo_yetki;

    @FXML
    private Label lbl_id;

    @FXML
    private Label lbl_mesaj;

   
    @FXML
    private TableView<Kayitlar_kulanici> tablo_kulanici;

    @FXML
    private TextField txt_kulanici;

    @FXML
    private TextField txt_sifre;
    
    Connection baglanti=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null;
    String sql;
    
    public void DegerleriGetir(TableView<?> tablo) {
        sql = "select * from login";
        ObservableList<Kayitlar_kulanici> kayitlarlist = FXCollections.observableArrayList();
        try {
            sorguifadesi = baglanti.prepareStatement(sql);
            ResultSet getirilen = sorguifadesi.executeQuery();
            while (getirilen.next()) {
                kayitlarlist.add(new Kayitlar_kulanici(getirilen.getInt("KID"),
                        getirilen.getString("KulaniciAdı"),
                        getirilen.getString("Sifre"),
                        getirilen.getDate("KayıtTarihi"),
                        getirilen.getInt("Yetki")));
                        
                
            }
            ccol_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_kulanici.setCellValueFactory(new PropertyValueFactory<>("kulanici"));
            col_sifre.setCellValueFactory(new PropertyValueFactory<>("sifre"));
            col_tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
            col_yetki.setCellValueFactory(new PropertyValueFactory<>("yetki"));
            tablo_kulanici.setItems(kayitlarlist);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_ekle_click(ActionEvent event) {

    	 sql = "insert into login(KulaniciAdı, Sifre, KayıtTarihi, Yetki) values(?,?,?,?)";
    	    try {
    	        sorguifadesi = baglanti.prepareStatement(sql);
    	        sorguifadesi.setString(1, txt_kulanici.getText().trim());
    	        sorguifadesi.setString(2, txt_sifre.getText().trim());
    	        sorguifadesi.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
    	        int yetki = combo_yetki.getValue();
    	        sorguifadesi.setInt(4, yetki);
    	        
    	        sorguifadesi.executeUpdate();
    	        DegerleriGetir(tablo_kulanici);
    	        
    	        txt_kulanici.clear();
    	        txt_sifre.clear();
    	        
    	        lbl_mesaj.setText("Kullanıcı Eklendi");
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        lbl_mesaj.setText("Hata: " + e.getMessage());
    	    }
    }

    @FXML
    void btn_geri_click(ActionEvent event) {

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
    }
    
    @FXML
    void btn_güncele_click(ActionEvent event) {

    	   Kayitlar_kulanici secilenUrun = tablo_kulanici.getSelectionModel().getSelectedItem();
    	    
    	    if (secilenUrun != null) {
    	        int KID = secilenUrun.getId();
    	        
    	        sql = "UPDATE login SET KulaniciAdı = ?, Sifre = ?, Yetki = ? WHERE KID = ?";
    	        
    	        try {
    	            sorguifadesi = baglanti.prepareStatement(sql);
    	            sorguifadesi.setString(1, txt_kulanici.getText().trim());
    	            sorguifadesi.setString(2, txt_sifre.getText().trim());
    	            int yetki = combo_yetki.getValue();
    	            sorguifadesi.setInt(3, yetki);
    	            sorguifadesi.setInt(4, KID);
    	            
    	            sorguifadesi.executeUpdate();
    	            
    	            DegerleriGetir(tablo_kulanici);
    	            
    	            txt_kulanici.clear();
    	            txt_sifre.clear();
    	            
    	            lbl_mesaj.setText("Kullanıcı Güncellendi");
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	    }
    }

    @FXML
    void btn_sil_click(ActionEvent event) {
        Kayitlar_kulanici secilenUrun = tablo_kulanici.getSelectionModel().getSelectedItem();
        
        if (secilenUrun != null) {
            int KID = secilenUrun.getId();
            
            sql = "DELETE FROM login WHERE KID = ?";
            
            try {
                sorguifadesi = baglanti.prepareStatement(sql);
                sorguifadesi.setInt(1, KID);
                sorguifadesi.executeUpdate();
                
                DegerleriGetir(tablo_kulanici);
                
                lbl_mesaj.setText("Kullanıcı Silindi");
               
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
   

    
    

    @FXML
    void tablo_kulanici_cilick(MouseEvent event) {
    	   Kayitlar_kulanici secilenUrun = tablo_kulanici.getSelectionModel().getSelectedItem();
    	    
    	    if (secilenUrun != null) {
    	        lbl_id.setText(String.valueOf(secilenUrun.getId()));
    	        txt_kulanici.setText(secilenUrun.getKulanici());
    	        txt_sifre.setText(secilenUrun.getSifre());
    	        combo_yetki.setValue(secilenUrun.getYetki());
    	        
    	        int secilenID = secilenUrun.getId();
    	        if (secilenID > 0) {
    	            btn_sil.setDisable(false); 
    	            btn_güncele.setDisable(false); 
    	        } else {
    	            btn_sil.setDisable(true); 
    	            btn_güncele.setDisable(true); 
    	        }
    	        
    	        int secilenYetki = secilenUrun.getYetki();
    	        if (secilenYetki == 1) {
    	            lbl_mesaj.setText("Yönetici Kaydı");
    	        } else if (secilenYetki == 0) {
    	            lbl_mesaj.setText("Kullanıcı Kaydı");
    	        } else {
    	            lbl_mesaj.setText("hata");
    	        }
    	    }
    }
   

    @FXML
    void initialize() {
    	combo_yetki.getItems().addAll(1, 0);
       DegerleriGetir(tablo_kulanici);
    }

}
