package com.example.test3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.controll.EventData;
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

public class EventController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<EventData> eventTable;
    @FXML
    private TableColumn<EventData, String> nhanVatColumn;
    @FXML
    private TableColumn<EventData, String> batDauColumn;
    @FXML
    private TableColumn<EventData, String> ketThucColumn;
    @FXML
    private TableColumn<EventData, String> suKienColumn;
    private ObservableList<EventData> eventDataList;
    @FXML
    private TextField filterFieldEvent;

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
            File file = new File("Crawler/src/main/resources/output/event.json");
            EventData[] eventDataArray = objectMapper.readValue(file, EventData[].class);
            List<EventData> eventDataList = Arrays.asList(eventDataArray);
            this.eventDataList = FXCollections.observableArrayList(eventDataList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        nhanVatColumn.setCellValueFactory(new PropertyValueFactory<EventData, String>("nhanVat"));
        batDauColumn.setCellValueFactory(new PropertyValueFactory<EventData, String>("batDau"));
        ketThucColumn.setCellValueFactory(new PropertyValueFactory<EventData, String>("ketThuc"));
        suKienColumn.setCellValueFactory(new PropertyValueFactory<EventData, String>("suKien"));
        eventTable.setItems(eventDataList);
        FilteredList<EventData> filteredDataEvent = new FilteredList<>(eventDataList, b -> true);
        filterFieldEvent.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataEvent.setPredicate(EventData ->{
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (EventData.getNhanVat().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true; // Mean we found a match in TrieuDai
                } else if (EventData.getBatDau().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (EventData.getKetThuc().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (EventData.getSuKien().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else
                    return false;
            });

        });
        SortedList<EventData> sortedData = new SortedList<>(filteredDataEvent);
        sortedData.comparatorProperty().bind(eventTable.comparatorProperty());
        eventTable.setItems(sortedData);
    }
}
