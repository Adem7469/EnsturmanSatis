package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SiparislerController {

	 public SiparislerController() {
	        baglanti = VeritabaniUtili.Baglanti();
	    }

	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_geri;

    @FXML
    private Button btn_iptal;

    @FXML
    private TableColumn<Kayitlar_siparisler, String> col_ad;

    @FXML
    private TableColumn<Kayitlar_siparisler, Integer> col_adet;

    @FXML
    private TableColumn<Kayitlar_siparisler, String> col_adres;

    @FXML
    private TableColumn<Kayitlar_siparisler, String> col_kod;

    @FXML
    private TableColumn<Kayitlar_siparisler, String> col_marka;

    @FXML
    private TableColumn<Kayitlar_siparisler, String> col_no;

    @FXML
    private TableColumn<Kayitlar_siparisler, String> col_soyad;

    @FXML
    private TableColumn<Kayitlar_siparisler, String> col_turu;

    @FXML
    private TableColumn<Kayitlar_siparisler, Double> col_tutar;

    @FXML
    private TableView<Kayitlar_siparisler> tbl_siparisler;

    @FXML
    private TextField txt_arama;

    Connection baglanti = null;
    PreparedStatement sorguifadesi = null;
    ResultSet getirilen = null;
    String sql;

    
    public void DegerleriGetir(TableView<?> tablo) {
        sql = "select sparisler.MüsteriAdı, sparisler.MüsteriSoyadı, sparisler.MüsteriNO, sparisler.MüsteriAdresi, sparisler.SiparisAdeti, sparisler.SiparisTutarı, ürünler.ÜrünTürü, ürünler.ÜrünMarkası, ürünler.ÜrünKodu from sparisler  join ürünler on ürünler.ÜrünKodu=sparisler.SiparisKodu";
       
        ObservableList<Kayitlar_siparisler> kayitlarlist = FXCollections.observableArrayList();
        try {
            sorguifadesi = baglanti.prepareStatement(sql);
            ResultSet getirilen = sorguifadesi.executeQuery();
            while (getirilen.next()) {
                kayitlarlist.add(new Kayitlar_siparisler(getirilen.getString("MüsteriAdı"),
                        getirilen.getString("MüsteriSoyadı"),
                        getirilen.getString("MüsteriNo"),
                        getirilen.getString("MüsteriAdresi"),
                        getirilen.getInt("SiparisAdeti"),
                        getirilen.getDouble("SiparisTutarı"),getirilen.getString("ÜrünTürü"),getirilen.getString("ÜrünMarkası"),getirilen.getString("ÜrünKodu")));
                
            }
            col_ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
            col_soyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
            col_no.setCellValueFactory(new PropertyValueFactory<>("no"));
            col_adres.setCellValueFactory(new PropertyValueFactory<>("adres"));
            col_adet.setCellValueFactory(new PropertyValueFactory<>("adet"));
            col_tutar.setCellValueFactory(new PropertyValueFactory<>("tutar"));
            col_turu.setCellValueFactory(new PropertyValueFactory<>("tur"));
            col_marka.setCellValueFactory(new PropertyValueFactory<>("marka"));
            col_kod.setCellValueFactory(new PropertyValueFactory<>("kod"));
            tbl_siparisler.setItems(kayitlarlist);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void goTokayit(ActionEvent event) {
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
    void btn_geri_click(ActionEvent event) {

    	goTokayit(event);
    	
    }

    @FXML
    void btn_iptal_click(ActionEvent event) {
        Kayitlar_siparisler seciliSiparis = tbl_siparisler.getSelectionModel().getSelectedItem();
        if (seciliSiparis != null) {
            String müsteriNO = seciliSiparis.getNo();
            String siparisKodu = seciliSiparis.getKod();
            
            try {
                String sql = "DELETE FROM sparisler WHERE MüsteriNO = ? AND SiparisKodu = ?";
                sorguifadesi = baglanti.prepareStatement(sql);
                sorguifadesi.setString(1, müsteriNO);
                sorguifadesi.setString(2, siparisKodu);
                sorguifadesi.executeUpdate();
                
                // Sipariş tablosunu güncelle
                DegerleriGetir(tbl_siparisler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void aramaYap(String aramaKelimesi) {
        String aramaSorgusu = "SELECT sparisler.MüsteriAdı, sparisler.MüsteriSoyadı, sparisler.MüsteriNO, sparisler.MüsteriAdresi, sparisler.SiparisAdeti, sparisler.SiparisTutarı, ürünler.ÜrünTürü, ürünler.ÜrünMarkası, ürünler.ÜrünKodu " +
                "FROM sparisler " +
                "JOIN ürünler ON ürünler.ÜrünKodu = sparisler.SiparisKodu " +
                "WHERE sparisler.MüsteriAdı LIKE ? OR sparisler.MüsteriSoyadı LIKE ?";

        ObservableList<Kayitlar_siparisler> kayitlarlist = FXCollections.observableArrayList();
        try {
            sorguifadesi = baglanti.prepareStatement(aramaSorgusu);
            sorguifadesi.setString(1, "%" + aramaKelimesi + "%");
            sorguifadesi.setString(2, "%" + aramaKelimesi + "%");
            ResultSet getirilen = sorguifadesi.executeQuery();
            while (getirilen.next()) {
                kayitlarlist.add(new Kayitlar_siparisler(getirilen.getString("MüsteriAdı"),
                        getirilen.getString("MüsteriSoyadı"),
                        getirilen.getString("MüsteriNo"),
                        getirilen.getString("MüsteriAdresi"),
                        getirilen.getInt("SiparisAdeti"),
                        getirilen.getDouble("SiparisTutarı"),
                        getirilen.getString("ÜrünTürü"),
                        getirilen.getString("ÜrünMarkası"),
                        getirilen.getString("ÜrünKodu")));
            }
            tbl_siparisler.setItems(kayitlarlist);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void initialize() {
       DegerleriGetir(tbl_siparisler);
       txt_arama.textProperty().addListener((observable, oldValue, newValue) -> {
    	    aramaYap(newValue);
    	});
    }

}