package com.example.App;

import com.example.controll.SiteData;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class SiteController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<SiteData> siteTable;
    @FXML
    private TableColumn<SiteData, String> viTriColumn;
    @FXML
    private TableColumn<SiteData, String> diTichColumn;
    @FXML
    private TableColumn<SiteData, String> ghiChuColumn;
    @FXML
    private TableColumn<SiteData, String> nhanVatLienQuanColumn;
    @FXML
    private TableColumn<SiteData, String> namCNColumn;
    @FXML
    private TextField filterFieldSite;
    private ObservableList<SiteData> siteDataList;

    public void switchToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Load data from JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File("Crawler/src/main/resources/output/site.json");
            SiteData[] siteDataArray = objectMapper.readValue(file, SiteData[].class);
            List<SiteData> siteDataList = Arrays.asList(siteDataArray);
            this.siteDataList = FXCollections.observableArrayList(siteDataList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        viTriColumn.setCellValueFactory(new PropertyValueFactory<SiteData, String>("viTri"));
        diTichColumn.setCellValueFactory(new PropertyValueFactory<SiteData, String>("diTich"));
        ghiChuColumn.setCellValueFactory(new PropertyValueFactory<SiteData, String>("ghiChu"));
        nhanVatLienQuanColumn.setCellValueFactory(new PropertyValueFactory<SiteData, String>("nhanVatLienQuan"));
        namCNColumn.setCellValueFactory(new PropertyValueFactory<SiteData, String>("namCN"));
        siteTable.setItems(siteDataList);
        FilteredList<SiteData> filteredDataSite = new FilteredList<>(siteDataList, b -> true);
        filterFieldSite.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataSite.setPredicate(SiteData ->{
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (SiteData.getViTri().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (SiteData.getDiTich().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (SiteData.getGhiChu().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (SiteData.getNhanVatLienQuan().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (SiteData.getNamCN().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else
                    return false;
            });

        });
        SortedList<SiteData> sortedData = new SortedList<>(filteredDataSite);
        sortedData.comparatorProperty().bind(siteTable.comparatorProperty());
        siteTable.setItems(sortedData);
    }
}
