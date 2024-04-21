package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.function.ToDoubleBiFunction;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.LocalDate;

public class SiparisvermeController {

	 public SiparisvermeController() {
	        baglanti = VeritabaniUtili.Baglanti();
	    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_geri;

    
    @FXML
    private Button btn_siparis;

    @FXML
    private Button btn_siparislerim;

    
    @FXML
    private TableColumn<Kayitlar_urunler, Integer> col_adeti;

    @FXML
    private TableColumn<Kayitlar_urunler, Double> col_fiyati;

    @FXML
    private TableColumn<Kayitlar_urunler, String> col_kod;

    @FXML
    private TableColumn<Kayitlar_urunler, String> col_marka;

    @FXML
    private TableColumn<Kayitlar_urunler, String> col_ozelikleri;

    @FXML
    private TableColumn<Kayitlar_urunler, String> col_turu;

    @FXML
    private Label lbl_fiyat;

    @FXML
    private Label lbl_mesaj;
    
    @FXML
    private TableView<Kayitlar_urunler> tbl_urunler;

    @FXML
    private TextField txt_ad;

    @FXML
    private TextField txt_adet;

    @FXML
    private TextField txt_adres;

    @FXML
    private TextField txt_arama;
    
    @FXML
    private Button txt_sifreyenile;

    @FXML
    private TextField txt_kod;

    @FXML
    private TextField txt_soyad;

    @FXML
    private TextField txt_telno;
    
    Connection baglanti = null;
    PreparedStatement sorguifadesi = null;
    ResultSet getirilen = null;
    String sql;


    public void DegerleriGetir(TableView<?> tablo) {
        String aramaKelimesi = txt_arama.getText().trim();
        sql = "SELECT * FROM ürünler WHERE ÜrünTürü LIKE ? OR ÜrünMarkası LIKE ? OR ÜrünÖzelikleri LIKE ? OR ÜrünKodu LIKE ?";
        ObservableList<Kayitlar_urunler> kayitlarlist = FXCollections.observableArrayList();
        try {
            sorguifadesi = baglanti.prepareStatement(sql);
            sorguifadesi.setString(1, "%" + aramaKelimesi + "%");
            sorguifadesi.setString(2, "%" + aramaKelimesi + "%");
            sorguifadesi.setString(3, "%" + aramaKelimesi + "%");
            sorguifadesi.setString(4, "%" + aramaKelimesi + "%");
            ResultSet getirilen = sorguifadesi.executeQuery();
            while (getirilen.next()) {
                kayitlarlist.add(new Kayitlar_urunler(getirilen.getInt("ÜrünID"),
                        getirilen.getString("ÜrünTürü"),
                        getirilen.getString("ÜrünMarkası"),
                        getirilen.getString("ÜrünÖzelikleri"),
                        getirilen.getDouble("ÜrünFiyatı"),
                        getirilen.getInt("ÜrünAdeti"),
                        getirilen.getString("ÜrünKodu")));
            }

            col_turu.setCellValueFactory(new PropertyValueFactory<>("turu"));
            col_marka.setCellValueFactory(new PropertyValueFactory<>("marka"));
            col_ozelikleri.setCellValueFactory(new PropertyValueFactory<>("ozelik"));
            col_fiyati.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
            col_adeti.setCellValueFactory(new PropertyValueFactory<>("adet"));
            col_kod.setCellValueFactory(new PropertyValueFactory<>("kod"));
            tbl_urunler.setItems(kayitlarlist);
            
           
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
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
    void btn_siparis_click(ActionEvent event) {
        sql = "insert into sparisler(MüsteriAdı, MüsteriSoyadı, MüsteriNo, MüsteriAdresi, SiparisAdeti, SiparisTutarı, SiparisTarihi, SiparisKodu) values(?,?,?,?,?,?,?,?)";
        try {
            sorguifadesi = baglanti.prepareStatement(sql);
            sorguifadesi.setString(1, txt_ad.getText().trim());
            sorguifadesi.setString(2, txt_soyad.getText().trim());
            sorguifadesi.setString(3, txt_telno.getText().trim());
            sorguifadesi.setString(4, txt_adres.getText().trim());
            sorguifadesi.setInt(5, Integer.parseInt(txt_adet.getText().trim())); 
            sorguifadesi.setDouble(6, Double.parseDouble(lbl_fiyat.getText().trim())); 
            
            sorguifadesi.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
            sorguifadesi.setString(8, txt_kod.getText().trim());
            sorguifadesi.executeUpdate();
            DegerleriGetir(tbl_urunler);
            lbl_mesaj.setText("Siparişiniz Alındı");
        } catch (Exception e) {
            e.printStackTrace();
            lbl_mesaj.setText("HATA: " + e.getMessage().toString());
        }
    }

    @FXML
    void btn_siparislerim_click(ActionEvent event) {

    	try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("Siparislerim.fxml"));
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
    void table_mause_click(MouseEvent event) {

    	 Kayitlar_urunler secilenUrun = tbl_urunler.getSelectionModel().getSelectedItem();
    	    
    	    if (secilenUrun != null) {
    	        
    	        txt_kod.setText(String.valueOf(secilenUrun.getKod()));
    	    }
    }
    
    @FXML
	void goToSifreYenileme(ActionEvent event) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sifreyenileme.fxml"));
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
    void txt_sifreyenile_click(ActionEvent event) {

    	goToSifreYenileme(event);
    }
    
    	@FXML
    	void initialize() {
    		DegerleriGetir(tbl_urunler);
    	txt_kod.setEditable(false);
    	ObservableList<Kayitlar_urunler> aramaSonucuListesi = FXCollections.observableArrayList();
    	
    	   
    	txt_adet.textProperty().addListener((observable, oldValue, newValue) -> {
    	    try {
    	        int adet = Integer.parseInt(newValue.trim());
    	        Kayitlar_urunler secilenUrun = tbl_urunler.getSelectionModel().getSelectedItem();
    	        if (secilenUrun != null) {
    	            double birimFiyat = secilenUrun.getFiyat();
    	            double toplamFiyat = adet * birimFiyat;
    	            lbl_fiyat.setText(String.valueOf(toplamFiyat));
    	        }
    	    } catch (NumberFormatException e) {
    	        lbl_fiyat.setText("");
    	    }
    	});
    	    
    	   
    	    txt_arama.textProperty().addListener((observable, oldValue, newValue) -> {
    	       
    	        String aramaKelimesi = newValue.trim();
    	        aramaSonucuListesi.clear(); 
    	        
    	        try {
    	            sorguifadesi = baglanti.prepareStatement(sql);
    	            sorguifadesi.setString(1, "%" + aramaKelimesi + "%");
    	            sorguifadesi.setString(2, "%" + aramaKelimesi + "%");
    	            sorguifadesi.setString(3, "%" + aramaKelimesi + "%");
    	            sorguifadesi.setString(4, "%" + aramaKelimesi + "%");
    	            ResultSet getirilen = sorguifadesi.executeQuery();
    	            while (getirilen.next()) {
    	                aramaSonucuListesi.add(new Kayitlar_urunler(getirilen.getInt("ÜrünID"),
    	                        getirilen.getString("ÜrünTürü"),
    	                        getirilen.getString("ÜrünMarkası"),
    	                        getirilen.getString("ÜrünÖzelikleri"),
    	                        getirilen.getDouble("ÜrünFiyatı"),
    	                        getirilen.getInt("ÜrünAdeti"),
    	                        getirilen.getString("ÜrünKodu")));
    	            }

    	           
    	            tbl_urunler.setItems(aramaSonucuListesi);
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	    });
    	}
}