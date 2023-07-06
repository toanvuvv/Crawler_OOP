package com.example.App;

import com.example.controll.MonarchData;
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

public class MonarchController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<MonarchData> monarchTable;
    @FXML
    private TableColumn<MonarchData, String> tenColumn;
    @FXML
    private TableColumn<MonarchData, String> sinhColumn;
    @FXML
    private TableColumn<MonarchData, String> matColumn;
    @FXML
    private TableColumn<MonarchData, String> noiSinhColumn;
    @FXML
    private TableColumn<MonarchData, String> noiMatColumn;
    @FXML
    private TableColumn<MonarchData, String> triViBatDauColumn;
    @FXML
    private TableColumn<MonarchData, String> triViKetThucColumn;
    @FXML
    private TableColumn<MonarchData, String> trieudaiColumn;
    @FXML
    private TableColumn<MonarchData, String> tienNhiemColumn;
    @FXML
    private TableColumn<MonarchData, String> keNhiemColumn;
    @FXML
    private TableColumn<MonarchData, String> mieuHieuColumn;
    @FXML
    private TableColumn<MonarchData, String> theThuColumn;
    @FXML
    private TableColumn<MonarchData, String> nienHieuColumn;
    @FXML
    private TableColumn<MonarchData, String> tenHuyColumn;
    @FXML
    private TextField filterFieldMonarch;
    private ObservableList<MonarchData> monarchDataList;

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
            File file = new File("Crawler/src/main/resources/output/monarch.json");
            MonarchData[] monarchDataArray = objectMapper.readValue(file, MonarchData[].class);
            List<MonarchData> monarchDataList = Arrays.asList(monarchDataArray);
            this.monarchDataList = FXCollections.observableArrayList(monarchDataList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tenColumn.setCellValueFactory(new PropertyValueFactory<MonarchData, String>("ten"));
        sinhColumn.setCellValueFactory(new PropertyValueFactory<MonarchData, String>("sinh"));
        matColumn.setCellValueFactory(new PropertyValueFactory<MonarchData, String>("mat"));
        noiSinhColumn.setCellValueFactory(new PropertyValueFactory<MonarchData, String>("noiSinh"));
        noiMatColumn.setCellValueFactory(new PropertyValueFactory<MonarchData, String>("noiMat"));
        triViBatDauColumn.setCellValueFactory(new PropertyValueFactory<MonarchData, String>("triViBatDau"));
        triViKetThucColumn.setCellValueFactory(new PropertyValueFactory<MonarchData, String>("triViKetThuc"));
        trieudaiColumn.setCellValueFactory(new PropertyValueFactory<MonarchData, String>("trieuDai"));
        tienNhiemColumn.setCellValueFactory(new PropertyValueFactory<MonarchData, String>("tienNhiem"));
        keNhiemColumn.setCellValueFactory(new PropertyValueFactory<MonarchData, String>("keNhiem"));
        mieuHieuColumn.setCellValueFactory(new PropertyValueFactory<MonarchData, String>("mieuHieu"));
        theThuColumn.setCellValueFactory(new PropertyValueFactory<MonarchData, String>("theThu"));
        nienHieuColumn.setCellValueFactory(new PropertyValueFactory<MonarchData, String>("nienHieu"));
        tenHuyColumn.setCellValueFactory(new PropertyValueFactory<MonarchData, String>("tenHuy"));
        monarchTable.setItems(monarchDataList);
        FilteredList<MonarchData> filteredDataMonarch = new FilteredList<>(monarchDataList, b -> true);
        filterFieldMonarch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataMonarch.setPredicate(MonarchData ->{
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (MonarchData.getMat().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true; // Mean we found a match in TrieuDai
                } else if (MonarchData.getSinh().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (MonarchData.getTrieuDai().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (MonarchData.getTriViBatDau().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (MonarchData.getMieuHieu().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (MonarchData.getTheThu().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (MonarchData.getNienHieu().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (MonarchData.getKeNhiem().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (MonarchData.getTrieuDai().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (MonarchData.getNoiMat().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (MonarchData.getTriViKetThuc().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (MonarchData.getTenHuy().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (MonarchData.getTen().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (MonarchData.getTienNhiem().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (MonarchData.getNoiSinh().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else
                    return false;
            });

        });
        SortedList<MonarchData> sortedData = new SortedList<>(filteredDataMonarch);
        sortedData.comparatorProperty().bind(monarchTable.comparatorProperty());
        monarchTable.setItems(sortedData);
    }
}
