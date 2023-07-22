package com.example.test3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.controll.FestivalData;
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

public class FestivalController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<FestivalData> festivalTable;
    @FXML
    private TableColumn<FestivalData, String> tenColumn;
    @FXML
    private TableColumn<FestivalData, String> ngayBatDauColumn;
    @FXML
    private TableColumn<FestivalData, String> viTriColumn;
    @FXML
    private TableColumn<FestivalData, String> nhanVatLienQuanColumn;
    @FXML
    private TableColumn<FestivalData, String> lanDauToChucColumn;
    @FXML
    private TableColumn<FestivalData, String> ghiChuColumn;
    @FXML
    private TextField filterFieldFestival;
    private ObservableList<FestivalData> festivalDataList;

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
            File file = new File("Gui/src/main/data/festival.json");
            FestivalData[] festivalDataArray = objectMapper.readValue(file, FestivalData[].class);
            List<FestivalData> festivalDataList = Arrays.asList(festivalDataArray);
            this.festivalDataList = FXCollections.observableArrayList(festivalDataList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tenColumn.setCellValueFactory(new PropertyValueFactory<FestivalData, String>("ten"));
        ngayBatDauColumn.setCellValueFactory(new PropertyValueFactory<FestivalData, String>("ngayBatDau"));
        viTriColumn.setCellValueFactory(new PropertyValueFactory<FestivalData, String>("viTri"));
        nhanVatLienQuanColumn.setCellValueFactory(new PropertyValueFactory<FestivalData, String>("nhanVatLienQuan"));
        lanDauToChucColumn.setCellValueFactory(new PropertyValueFactory<FestivalData, String>("lanDauToChuc"));
        ghiChuColumn.setCellValueFactory(new PropertyValueFactory<FestivalData, String>("ghiChu"));
        festivalTable.setItems(festivalDataList);
        FilteredList<FestivalData> filteredDataFestival  = new FilteredList<>(festivalDataList, b -> true);
        filterFieldFestival.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataFestival.setPredicate(FestivalData ->{
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (FestivalData.getTen().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true; // Mean we found a match in TrieuDai
                } else if (FestivalData.getNgayBatDau().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (FestivalData.getViTri().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (FestivalData.getNhanVatLienQuan().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (FestivalData.getLanDauToChuc().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (FestivalData.getGhiChu().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else
                    return false;
            });

        });
        SortedList<FestivalData> sortedData = new SortedList<>(filteredDataFestival);
        sortedData.comparatorProperty().bind(festivalTable.comparatorProperty());
        festivalTable.setItems(sortedData);
    }
}
