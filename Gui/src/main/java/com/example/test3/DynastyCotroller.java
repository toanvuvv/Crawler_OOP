package com.example.test3;

import com.example.controll.DynastyData;
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

public class DynastyCotroller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<DynastyData> dynastyTable;
    @FXML
    private TableColumn<DynastyData, String> trieuDaiColumn;
    @FXML
    private TableColumn<DynastyData, String> viTheColumn;
    @FXML
    private TableColumn<DynastyData, String> ketThucColumn;
    @FXML
    private TableColumn<DynastyData, String> suKienLienQuanColumn;
    @FXML
    private TableColumn<DynastyData, String> donViTienTeColumn;
    @FXML
    private TableColumn<DynastyData, String> batDauColumn;
    @FXML
    private TableColumn<DynastyData, String> ngonNguColumn;
    @FXML
    private TableColumn<DynastyData, String> quocGiaColumn;
    @FXML
    private TableColumn<DynastyData, String> nhanVatColumn;
    @FXML
    private TableColumn<DynastyData, String> thuDoColumn;
    @FXML
    private TableColumn<DynastyData, String> tonGiaoColumn;
    @FXML
    private TextField filterFieldDysnaty;


    private ObservableList<DynastyData> dynastyDataList;

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
            File file = new File("Gui/src/main/data/dynasty.json");
            DynastyData[] dynastyDataArray = objectMapper.readValue(file, DynastyData[].class);
            List<DynastyData> dynastyDataList = Arrays.asList(dynastyDataArray);
            this.dynastyDataList = FXCollections.observableArrayList(dynastyDataList);
        } catch (IOException e) {

            e.printStackTrace();
        }

        trieuDaiColumn.setCellValueFactory(new PropertyValueFactory<DynastyData, String>("trieuDai"));
        viTheColumn.setCellValueFactory(new PropertyValueFactory<DynastyData, String>("viThe"));
        ketThucColumn.setCellValueFactory(new PropertyValueFactory<DynastyData, String>("ketThuc"));
        suKienLienQuanColumn.setCellValueFactory(new PropertyValueFactory<DynastyData, String>("suKienLienQuan"));
        donViTienTeColumn.setCellValueFactory(new PropertyValueFactory<DynastyData, String>("donViTienTe"));
        batDauColumn.setCellValueFactory(new PropertyValueFactory<DynastyData, String>("batDau"));
        ngonNguColumn.setCellValueFactory(new PropertyValueFactory<DynastyData, String>("ngonNgu"));
        quocGiaColumn.setCellValueFactory(new PropertyValueFactory<DynastyData, String>("quocGia"));
        nhanVatColumn.setCellValueFactory(new PropertyValueFactory<DynastyData, String>("nhanVat"));
        thuDoColumn.setCellValueFactory(new PropertyValueFactory<DynastyData, String>("thuDo"));
        tonGiaoColumn.setCellValueFactory(new PropertyValueFactory<DynastyData, String>("tonGiao"));
        dynastyTable.setItems(dynastyDataList);
        FilteredList<DynastyData> filteredDataDynasty = new FilteredList<>(dynastyDataList, b -> true);
        filterFieldDysnaty.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataDynasty.setPredicate(DynastyData ->{
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (DynastyData.getTrieuDai().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true; // Mean we found a match in TrieuDai
                } else if (DynastyData.getViThe().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (DynastyData.getTrieuDai().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (DynastyData.getKetThuc().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (DynastyData.getDonViTienTe().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (DynastyData.getSuKienLienQuan().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (DynastyData.getBatDau().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (DynastyData.getNgonNgu().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (DynastyData.getQuocGia().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (DynastyData.getNhanVat().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (DynastyData.getThuDo().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (DynastyData.getTonGiao().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else
                    return false;
            });

        });
        SortedList<DynastyData> sortedData = new SortedList<>(filteredDataDynasty);
        sortedData.comparatorProperty().bind(dynastyTable.comparatorProperty());
        dynastyTable.setItems(sortedData);
    }
}
