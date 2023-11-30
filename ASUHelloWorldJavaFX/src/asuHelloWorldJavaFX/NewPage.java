package asuHelloWorldJavaFX;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ASUHelloWorldJavaFX extends Application {
    private final ObservableList<EffortData> rowData = FXCollections.observableArrayList();
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        loadDataFromFile("testData.txt"); // Load data from the text file - Remove once database is implemented
   
        TableView<EffortData> table = new TableView<>(); // Create Table
       
        //Set values into table.
        table.setItems(rowData);

        TableColumn<EffortData, StringProperty> numberCol = new TableColumn<>("#");
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        
        TableColumn<EffortData, StringProperty> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<EffortData, String> lifeCycleStepCol = new TableColumn<>("Life Cycle Step");
        lifeCycleStepCol.setCellValueFactory(new PropertyValueFactory<>("lifeCycleStep"));

        TableColumn<EffortData, String> effortCategoryCol = new TableColumn<>("Effort Category");
        effortCategoryCol.setCellValueFactory(new PropertyValueFactory<>("effortCategory"));

        TableColumn<EffortData, String> deliverableCol = new TableColumn<>("Deliverable");
        deliverableCol.setCellValueFactory(new PropertyValueFactory<>("deliverable"));
        
        	//These are for if we decide to display all data in the table. 
        
        //TableColumn<EffortData, String> startTimeCol = new TableColumn<>("Start Time");
        //startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        //TableColumn<EffortData, String> stopTimeCol = new TableColumn<>("Stop Time");
        //stopTimeCol.setCellValueFactory(new PropertyValueFactory<>("stopTime"));

        //TableColumn<EffortData, String> deltaTimeCol = new TableColumn<>("Delta Time");
        //deltaTimeCol.setCellValueFactory(new PropertyValueFactory<>("deltaTime"));
        
        //I dont know why it flags this, but it works fine. 
        table.getColumns().addAll(numberCol, dateCol, lifeCycleStepCol, effortCategoryCol, deliverableCol);             
        		//, startTimeCol, stopTimeCol, deltaTimeCol); //add to above if we decide to display all data

        // Handle row selection and detailed view
        Button openDetailedViewButton = new Button("Open Detailed View");
        openDetailedViewButton.setOnAction(e -> {
            EffortData selectedRow = table.getSelectionModel().getSelectedItem();
            if (selectedRow != null) {
                openDetailedView(selectedRow);
            }
        });
        
        // Setup Window
        VBox buttonVBox = new VBox(openDetailedViewButton);
        buttonVBox.setSpacing(10);

        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setRight(buttonVBox); 
        root.setPadding(new Insets(10));
        
        Scene scene = new Scene(root, 800, 600);
        
        primaryStage.setTitle("RowData Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }

    private void openDetailedView(EffortData rowData) {
        // Create a new stage for the detailed view 
    		//Might be a good idea to have it pull directly from the database insted of table in final ver.
    		//If we have time to implement the effort data editor, use this as a base.
    	
        Stage detailedStage = new Stage();
        BorderPane detailedRoot = new BorderPane();

        Label numberLabel = new Label("Data Number:");
        TextField numberTextField = new TextField();
        numberTextField.textProperty().bind(rowData.numberProperty());
        
        Label dateLabel = new Label("Date:");
        TextField dateTextField = new TextField();
        dateTextField.textProperty().bind(rowData.dateProperty());
        
        Label startTimeLabel = new Label("Start Time:");
        TextField startTimeTextField = new TextField();
        startTimeTextField.textProperty().bind(rowData.startTimeProperty());

        Label stopTimeLabel = new Label("Stop Time:");
        TextField stopTimeTextField = new TextField();
        stopTimeTextField.textProperty().bind(rowData.stopTimeProperty());

        Label deltaTimeLabel = new Label("Delta Time:");
        TextField deltaTimeTextField = new TextField();
        deltaTimeTextField.textProperty().bind(rowData.deltaTimeProperty());
        
        Label lifeCycleLabel = new Label("Life Cycle Step:");
        TextField lifeCycleTextField = new TextField();
        lifeCycleTextField.textProperty().bind(rowData.lifeCycleStepProperty());
        
        Label effortLabel = new Label("Effort Category:");
        TextField effortTextField = new TextField();
        effortTextField.textProperty().bind(rowData.effortCategoryProperty());
        
        Label deliverableLabel = new Label("Deliverable:");
        TextField deliverableTextField = new TextField();
        deliverableTextField.textProperty().bind(rowData.deliverableProperty());
        
        //Setup display 
        VBox detailsVBox = new VBox(
        		numberLabel, numberTextField,
        		dateLabel, dateTextField,
                startTimeLabel, startTimeTextField,
                stopTimeLabel, stopTimeTextField,
                deltaTimeLabel, deltaTimeTextField,
                lifeCycleLabel, lifeCycleTextField,
                effortLabel, effortTextField,
                deliverableLabel, deliverableTextField
        );
        detailsVBox.setSpacing(10);
        detailedRoot.setCenter(detailsVBox);

        detailedRoot.setPadding(new Insets(10));
        Scene detailedScene = new Scene(detailedRoot, 200, 600);

        detailedStage.setTitle("Detailed View");
        detailedStage.setScene(detailedScene);
        detailedStage.show();
    }

    
    //loads data from table - This should be replaced by database functions in final version
    private void loadDataFromFile(String filePath) {
    	
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Loop for each line of file
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) { 
                	// Ensure that the line has the expected format of 8 values
                    rowData.add(new EffortData(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]));   
                } else {
                	// Displays in row if data is incomplete. As the input component requiers all fields, this shows an error in the database. 
                	rowData.add(new EffortData("Incomplete Data Error", "", "", "", "", "", "", ""));
                }
            }            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
