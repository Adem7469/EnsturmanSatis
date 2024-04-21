package application;


import java.awt.MenuBar;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UrunlerController {

    public UrunlerController() {
        baglanti = VeritabaniUtili.Baglanti();
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
    private Button btn_guncele;

    @FXML
    private Button btn_kulanici;

    
    @FXML
    private Button btn_sil;

    @FXML
    private Button btn_siparisler;
    
    @FXML
    private TableColumn<Kayitlar_urunler, Integer> col_adeti;

    @FXML
    private TableColumn<Kayitlar_urunler, Double> col_fiyati;

    @FXML
    private TableColumn<Kayitlar_urunler, Integer> col_id;
    
    @FXML
    private TableColumn<Kayitlar_urunler, String> col_kod;

    @FXML
    private TableColumn<Kayitlar_urunler, String> col_marka;

    @FXML
    private TableColumn<Kayitlar_urunler, String> col_ozelikleri;

    @FXML
    private TableColumn<Kayitlar_urunler, String> col_turu;
   


    @FXML
    private TableView<Kayitlar_urunler> tbl_urunler;

    @FXML
    private TextField txt_adeti;

    @FXML
    private TextField txt_fiyati;
    
    @FXML
    private TextField txt_kod;

    @FXML
    private TextField txt_markasi;

    @FXML
    private TextField txt_ozelikleri;

    @FXML
    private TextField txt_turu;

    Connection baglanti = null;
    PreparedStatement sorguifadesi = null;
    ResultSet getirilen = null;
    String sql;

    @FXML
    void btn_ekle_click(ActionEvent event) {
        sql = "insert into ürünler(ÜrünTürü, ÜrünMarkası, ÜrünÖzelikleri, ÜrünFiyatı,ÜrünAdeti,ÜrünKodu) values(?,?,?,?,?,?)";
        try {
            sorguifadesi = baglanti.prepareStatement(sql);
            sorguifadesi.setString(1, txt_turu.getText().trim());
            sorguifadesi.setString(2, txt_markasi.getText().trim());
            sorguifadesi.setString(3, txt_ozelikleri.getText().trim());
            sorguifadesi.setString(4, txt_fiyati.getText().trim());
            sorguifadesi.setString(5, txt_adeti.getText().trim());
            sorguifadesi.setString(6, txt_kod.getText().trim());
            sorguifadesi.executeUpdate();
            DegerleriGetir(tbl_urunler);
           
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
    
    public void DegerleriGetir(TableView<?> tablo) {
        sql = "select * from ürünler";
        ObservableList<Kayitlar_urunler> kayitlarlist = FXCollections.observableArrayList();
        try {
            sorguifadesi = baglanti.prepareStatement(sql);
            ResultSet getirilen = sorguifadesi.executeQuery();
            while (getirilen.next()) {
                kayitlarlist.add(new Kayitlar_urunler(getirilen.getInt("ÜrünID"),
                        getirilen.getString("ÜrünTürü"),
                        getirilen.getString("ÜrünMarkası"),
                        getirilen.getString("ÜrünÖzelikleri"),
                        getirilen.getDouble("ÜrünFiyatı"),
                        getirilen.getInt("ÜrünAdeti"),getirilen.getString("ÜrünKodu")));
                
            }
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
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
    void btn_guncele_click(ActionEvent event) {
        
        Kayitlar_urunler secilenUrun = tbl_urunler.getSelectionModel().getSelectedItem();
        
        if (secilenUrun != null) {
            int urunID = secilenUrun.getId();
            
            
            sql = "UPDATE ürünler SET ÜrünFiyatı = ?, ÜrünAdeti = ? WHERE ÜrünID = ?";
            
            try {
                sorguifadesi = baglanti.prepareStatement(sql);
                sorguifadesi.setString(1, txt_fiyati.getText().trim());
                sorguifadesi.setString(2, txt_adeti.getText().trim());
                sorguifadesi.setInt(3, urunID);
                sorguifadesi.executeUpdate();
                
                
                DegerleriGetir(tbl_urunler);
                
                
                txt_turu.clear();
                txt_markasi.clear();
                txt_ozelikleri.clear();
                txt_fiyati.clear();
                txt_adeti.clear();
                txt_kod.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btn_kulanici_click(ActionEvent event) {

    	 try {
         	FXMLLoader loader = new FXMLLoader(getClass().getResource("Kulanicikayit.fxml"));
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
void btn_sil_click(ActionEvent event) {
    
    Kayitlar_urunler secilenUrun = tbl_urunler.getSelectionModel().getSelectedItem();
    
    if (secilenUrun != null) {
        int urunID = secilenUrun.getId();
        
        
        sql = "DELETE FROM ürünler WHERE ÜrünID = ?";
        
        try {
            sorguifadesi = baglanti.prepareStatement(sql);
            sorguifadesi.setInt(1, urunID);
            sorguifadesi.executeUpdate();
            
            
            DegerleriGetir(tbl_urunler);
            
            
            txt_turu.clear();
            txt_markasi.clear();
            txt_ozelikleri.clear();
            txt_fiyati.clear();
            txt_adeti.clear();
            txt_kod.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

@FXML
void btn_siparisler_click(ActionEvent event) {
	try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Siparisler.fxml"));
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
void table_mauseClick(MouseEvent event) {
  
    Kayitlar_urunler secilenUrun = tbl_urunler.getSelectionModel().getSelectedItem();
    
    if (secilenUrun != null) {
       
        txt_turu.setText(secilenUrun.getTuru());
        txt_markasi.setText(secilenUrun.getMarka());
        txt_ozelikleri.setText(secilenUrun.getOzelik());
        txt_fiyati.setText(String.valueOf(secilenUrun.getFiyat()));
        txt_adeti.setText(String.valueOf(secilenUrun.getAdet()));
        txt_kod.setText(String.valueOf(secilenUrun.getKod()));
    }
}

    @FXML
    void initialize() {
        DegerleriGetir(tbl_urunler);
    }
}