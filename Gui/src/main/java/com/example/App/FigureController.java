package com.example.App;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.controll.FigureData;
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

public class FigureController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<FigureData> figureTable;
    @FXML
    private TableColumn<FigureData, String> tenColumn;
    @FXML
    private TableColumn<FigureData, String> trieuDaiColumn;
    @FXML
    private TableColumn<FigureData, String> matColumn;
    @FXML
    private TableColumn<FigureData, String> sinhColumn;
    @FXML
    private TableColumn<FigureData, String> noiMatColumn;
    @FXML
    private TableColumn<FigureData, String> noiSinhColumn;
    @FXML
    private TableColumn<FigureData, String> urlColumn;
    @FXML
    private TextField filterFieldFigure;
    private ObservableList<FigureData> figureDataList;


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
            File file = new File("Crawler/src/main/resources/output/figure.json");
            FigureData[] figureDataArray = objectMapper.readValue(file, FigureData[].class);
            List<FigureData> figureDataList = Arrays.asList(figureDataArray);
            this.figureDataList = FXCollections.observableArrayList(figureDataList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tenColumn.setCellValueFactory(new PropertyValueFactory<FigureData, String>("ten"));
        trieuDaiColumn.setCellValueFactory(new PropertyValueFactory<FigureData, String>("trieuDai"));
        matColumn.setCellValueFactory(new PropertyValueFactory<FigureData, String>("mat"));
        sinhColumn.setCellValueFactory(new PropertyValueFactory<FigureData, String>("sinh"));
        noiMatColumn.setCellValueFactory(new PropertyValueFactory<FigureData, String>("noiMat"));
        noiSinhColumn.setCellValueFactory(new PropertyValueFactory<FigureData, String>("noiSinh"));
        urlColumn.setCellValueFactory(new PropertyValueFactory<FigureData, String>("url"));
        figureTable.setItems(figureDataList);
        FilteredList<FigureData> filteredDataFigure = new FilteredList<>(figureDataList, b -> true);
        filterFieldFigure.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataFigure.setPredicate(FigureData ->{
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (FigureData.getTrieuDai().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true; // Mean we found a match in TrieuDai
                } else if (FigureData.getTen().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (FigureData.getTrieuDai().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (FigureData.getMat().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (FigureData.getSinh().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (FigureData.getNoiMat().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (FigureData.getNoiSinh().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (FigureData.getUrl().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else
                    return false;
            });

        });
        SortedList<FigureData> sortedData = new SortedList<>(filteredDataFigure);
        sortedData.comparatorProperty().bind(figureTable.comparatorProperty());
        figureTable.setItems(sortedData);
    }
}
