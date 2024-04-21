package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_guncelle;

    @FXML
    private Button btn_sil;

    @FXML
    private TableColumn<?, ?> clm_Ders;

    @FXML
    private TableColumn<?, ?> clm_ID;

    @FXML
    private TableColumn<?, ?> clm_Kontenjan;

    @FXML
    private TableColumn<?, ?> clm_Kredi;

    @FXML
    private TableColumn<?, ?> clm_Universite;

    @FXML
    private TableColumn<?, ?> clm_ders;

    @FXML
    private TableColumn<?, ?> clm_kontenjan;

    @FXML
    private TableColumn<?, ?> clm_not;

    @FXML
    private TableColumn<?, ?> clm_ogrencino;

    @FXML
    private TableColumn<?, ?> clm_universite;

    @FXML
    private TableColumn<?, ?> clm_yazokuluID;

    @FXML
    private ComboBox<?> comboBox_dersler;

    @FXML
    private Spinner<?> comboBox_ogrencino;

    @FXML
    private ComboBox<?> comboBox_universite;

    @FXML
    private ComboBox<?> comboBox_urunler;

    @FXML
    private Label lbl_captcha;

    @FXML
    private Label lbl_dersler;

    @FXML
    private TableView<?> tbl_dersler;

    @FXML
    private TableView<?> tbl_urunler;

    @FXML
    private TextField txt_Kontenjan;

    @FXML
    private TextField txt_Notdurum;

    @FXML
    private TextField txt_captcha;

    @FXML
    private TextField txt_dersadi;

    @FXML
    private TextField txt_ogrencino;

    @FXML
    void btn_guncelle_Click(ActionEvent event) {

    }

    @FXML
    void btn_sil_Click(ActionEvent event) {

    }

    @FXML
    void btn_siparis_Click(ActionEvent event) {

    }

    @FXML
    void initialize() {
       

    }

}
