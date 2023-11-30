package asuHelloWorldJavaFX;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.io.*;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;




class DropdownObject {
    private String project;
    private ComboBox<CheckBox> lifeCycleStep;
    private ComboBox<CheckBox> effortCategory;
    private ComboBox<CheckBox> deliverable;

    public DropdownObject(String project, ComboBox<CheckBox> lifeCycleStep, ComboBox<CheckBox> effortCategory, ComboBox<CheckBox> deliverable) {
        this.project = project;
        this.lifeCycleStep = lifeCycleStep;
        this.effortCategory = effortCategory;
        this.deliverable = deliverable;
    }

    public String getProject() {
        return project;
    }

    public ComboBox<CheckBox> getLifeCycleStep() {
        return lifeCycleStep;
    }

    public ComboBox<CheckBox> getEffortCategory() {
        return effortCategory;
    }

    public ComboBox<CheckBox> getDeliverable() {
        return deliverable;
    }
}
class YourDataClass {
    private String category;
    private String date;
    private String deliverable;
    private String dtime;
    private String lifecycle;
    private String start;
    private String stop;
    private String user;
    private String id;
    // Constructors, getters, and setters go here...

    // Example constructor
    public YourDataClass(String category, String date, String deliverable, String dtime,
            String lifecycle, String start, String stop, String user,String id) {
this.category = category;
this.date = date;
this.deliverable = deliverable;
this.dtime = dtime;
this.lifecycle = lifecycle;
this.start = start;
this.stop = stop;
this.user = user;
this.id = id;
}

// Getters
public String getCategory() {
return category;
}

public String getDate() {
return date;
}

public String getDeliverable() {
return deliverable;
}

public String getDtime() {
return dtime;
}

public String getLifecycle() {
return lifecycle;
}

public String getStart() {
return start;
}

public String getStop() {
return stop;
}
public String getUser() {
return user;
}
public String getId() {
return id;
}
}
class AccountData {
    private String data;
    

    // Constructors, getters, and setters go here...

    // Example constructor
    public AccountData(String data) {
this.data = data;

}

// Getters
public String getData() {
return data;
}

}
public class ASUHelloWorldJavaFX extends Application {
	private String category;
    private String date;
    private String deliverable;
    private String dtime;
    private String lifecycle;
    private String start;
    private String stop;
    private String data;
    private StringProperty timerStatus = new SimpleStringProperty("Not Started");
    private long startTime = 0;
    private final ObservableList<EffortData> rowData = FXCollections.observableArrayList();
    String Name = "";
    private Label dateLabel = new Label("Date:");
    private Label startTimeLabel = new Label("Starting Time:");
    private Label endTimeLabel = new Label("Ending Time:");
    private Label totalTimeLabel = new Label("Total Time:");
    private Label submitLabel = new Label("Submit Status: ");
    private Timeline clockTimeline;
    //date, starting, ending, and total time
    private String dateVal;
    private String sTime;
    private String eTime;
    private String tTime;
    private BooleanProperty passwordVisible = new SimpleBooleanProperty(false);
    Boolean acess = false;
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
    private Stage primaryStage;
    private Scene scene1;
    private Scene loginApp;
    private Scene createAcc;
    private Scene logOut;
	private Label failedLogin = new Label("Username or Password inncorrect. \n" + "Attempts remain");
    private boolean isSup = false;
    private boolean empsupSelect = false;
    private int loginAttempts = 0;
    List<Account> accountList = new ArrayList<>();
    ArrayList<String> LifeCycleStep = new ArrayList<>();
    ArrayList<String> EffortCategory = new ArrayList<>();
    ArrayList<String> Deliverable = new ArrayList<>();
    boolean accountExist = false;

    public static void main(String[] args) {
        launch(args);
    }

    //@Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        loadAccountsFromFile();
        this.primaryStage = primaryStage;
        scene1 = createOpen();
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    
    
    
    
    
    
    private Scene effortLogger(Stage previousStage) {
    	Label averageLabel = new Label("Avg Time: ");
    	Label maxLabel = new Label("Max Time: ");
    	Label minLabel = new Label("Min Time: ");

    	// Set styles for labels
    	averageLabel.setStyle("-fx-text-fill: #d4eceb;");
    	maxLabel.setStyle("-fx-text-fill: #d4eceb;");
    	minLabel.setStyle("-fx-text-fill: #d4eceb;");

    	
    	String line = "";

        // Read data from consoleOutput5.txt and save it to the 'line' variable
        try (BufferedReader br = new BufferedReader(new FileReader("consoleOutput5.txt"))) {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    	
    	
    	String[] parts = line.split(",");
		if (parts.length == 4) {
			Name = unscrambler(parts[1]);
			acess = Boolean.parseBoolean(unscrambler(parts[3]));
		}
		System.out.println(Name);
		System.out.println(acess);
        loadDataFromFile("testData.txt"); // Load data from the text file - Remove once database is implemented
        
        // Create the top bar
        HBox topBar = new HBox();
        topBar.setStyle("-fx-background-color: #06c8a1;"); // Set bar background color
        topBar.setPrefHeight(50); // Set the height to 50

        // Add a label to the top bar
        Label titleLabel = new Label("effortlogger");
        titleLabel.setStyle("-fx-text-fill: #d4eceb;"); // Set label text color
        titleLabel.setFont(Font.font("PT Sans Caption", FontWeight.BOLD, 35));
        titleLabel.setAlignment(Pos.CENTER); // Center the text vertically
        titleLabel.setPadding(new Insets(12, 0, 5, 12)); // Adjust padding as needed

        ImageView logoImageView = new ImageView();
        Image logoImage = new Image(getClass().getResourceAsStream("effortlogger.png"));

        logoImageView.setImage(logoImage);

        HBox.setHgrow(logoImageView, Priority.ALWAYS); // Allow the image to take available space
        HBox.setMargin(logoImageView, new Insets(10, 20, 10, 10)); // Adjust margins to add more space around the image
        
        TableView<EffortData> table = new TableView<>();
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

        table.getColumns().addAll(numberCol, dateCol, lifeCycleStepCol, effortCategoryCol, deliverableCol);

        table.setRowFactory(tv -> {
            TableRow<EffortData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    EffortData selectedRow = row.getItem();
                    openDetailedView(selectedRow);
                }
            });
            return row;
        });

        // Setup Window
        

        CustomButtonmain logoutButton = new CustomButtonmain("Logout");
        logoutButton.setOnAction(e -> switchToLogin1());

        CustomButtonmain goToThirdPageButton = new CustomButtonmain("Refresh");
        goToThirdPageButton.setOnAction(e -> {
            rowData.clear();
            // Reload data from the file or database
            loadDataFromFile("testData.txt"); // You may need to adjust this based on your implementation

            // Update the table with the new data
            table.setItems(rowData);
            
        });

        CustomButtonmain goToFourthPageButton = new CustomButtonmain("New Entry");
        goToFourthPageButton.setOnAction(e -> switchToFourthPage(previousStage));
        CustomButtonmain goToFivePageButton = new CustomButtonmain("Planning");
        goToFivePageButton.setOnAction(e -> switchToFithPage(previousStage));
        VBox vboxm = new VBox(10); // 10 is the spacing between buttons
        vboxm.getChildren().addAll(titleLabel); // Use vboxm instead of hbox
        vboxm.setAlignment(Pos.CENTER_LEFT);
        Label greetingLabel = new Label(Name);
        greetingLabel.setStyle("-fx-text-fill: #43528f;"); // Set label text color
        greetingLabel.setFont(Font.font("PT Sans Caption", FontWeight.BOLD, 30));
        greetingLabel.setAlignment(Pos.TOP_RIGHT); // Center the text vertically
        greetingLabel.setPadding(new Insets(10, 0, 0, 70));
        
        HBox hbox = new HBox(10); // 10 is the spacing between buttons

 // 10 is the spacing between buttons
//        hbox.getChildren().addAll();
//        hbox.setAlignment(Pos.BOTTOM_LEFT);
//
//        // Adjust spacing between titleLabel and buttons
//        hbox.setPadding(new Insets(15, 0, 0, 0));

//        vboxm.getChildren().add(hbox); // Add the hbox to the vboxm

        topBar.getChildren().addAll(titleLabel, new Region(), logoImageView);
        HBox.setHgrow(topBar.getChildren().get(1), Priority.ALWAYS); // Make the Region take available space

        // Load the image
        Image image = new Image("file:effortlogger.png"); // Assuming the image is in the same directory as your code

        // Create an ImageView and set the image
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        // Add the image view to the layout
        hbox.getChildren().add(imageView);

        BorderPane root = new BorderPane();
        
        


        
        
        
        
        
        
        
        
        
        
   

	    // Set up the grid
	    GridPane grid = new GridPane();
	    grid.setHgap(12);
	    grid.setVgap(12);
	    grid.setPadding(new Insets(10, 10, 10, 10));


 // Timer button
	    CustomButtonlog2 timerButton = new CustomButtonlog2("Start");
	    //timerButton.setStyle("-fx-background-color: red;");
	    timerButton.setOnAction(e -> handleTimerButton(timerButton));

	    // Clock label next to the button
	    Label clockLabel = new Label("00:00");
	    clockLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
	    //grid.add(timerButton, 0, 0);
	   // grid.add(clockLabel, 1, 0);
	    KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	            updateClock(clockLabel);
	        }
	    });
	    clockTimeline = new Timeline(keyFrame);
	    clockTimeline.setCycleCount(Timeline.INDEFINITE);
	    
 // Drop down 1 - Project
// Drop down 1 - Project
// Drop down 1 - Project
// Drop down 1 - Project
	 // Drop down 1 - Project
	    Label label1 = new Label("Project:");
	    label1.setStyle("-fx-text-fill: #d4eceb;");
	    label1.setFont(Font.font("PT Sans Caption", FontWeight.BOLD, 15));
	    ComboBox<String> dropdown1 = new ComboBox<>();
	    dropdown1.getItems().addAll("Business Project", "Development Project");
	    dropdown1.setMinWidth(150); // Set a standard width for the ComboBox
	    grid.add(label1, 0, 1);
	    grid.add(dropdown1, 0, 2);

	    // Drop Down 2 - Life Cycle Step
	    Label label2 = new Label("Life Cycle Step:");
	    ComboBox<CheckBox> dropdown2 = new ComboBox<>();
	    dropdown2.disableProperty();
	    dropdown2.setMinWidth(150); // Set a standard width for the ComboBox
	    grid.add(label2, 1, 1);
	    grid.add(dropdown2, 1, 2);
	    label2.setStyle("-fx-text-fill: #d4eceb;");
	    label2.setFont(Font.font("PT Sans Caption", FontWeight.BOLD, 15));
	    dropdown2.disableProperty();

	    // Drop Down 3 - Effort Categories
	    Label label3 = new Label("Effort Category:");
	    ComboBox<CheckBox> dropdown3 = new ComboBox<>();
	    dropdown3.disableProperty();
	    dropdown3.getItems().addAll(
	            createCheckBox("Plans"),
	            createCheckBox("Deliverables"),
	            createCheckBox("Interuptions"),
	            createCheckBox("Defects"),
	            createCheckBox("Others")
	    );
	    dropdown3.setMinWidth(150); // Set a standard width for the ComboBox
	    dropdown3.disableProperty();
	    grid.add(label3, 2, 1);
	    grid.add(dropdown3, 2, 2);
	    label3.setStyle("-fx-text-fill: #d4eceb;");
	    label3.setFont(Font.font("PT Sans Caption", FontWeight.BOLD, 15));
	    // Drop Down 4 - Label will change Based on prior boxes
	    Label label4 = new Label("Deliverable:");
	    ComboBox<CheckBox> dropdown4 = new ComboBox<>();
	    dropdown4.disableProperty();
	    dropdown4.getItems().addAll(
	            createCheckBox("Project Plan"),
	            createCheckBox("Risk Management Plan"),
	            createCheckBox("Conceptual Design Plan"),
	            createCheckBox("Detailed Design Plan"),
	            createCheckBox("Implimentation Plan")
	    );
	    dropdown4.setMinWidth(150); // Set a standard width for the ComboBox
	    dropdown4.disableProperty();
	    grid.add(label4, 3, 1);
	    grid.add(dropdown4, 3, 2);
	    label4.setStyle("-fx-text-fill: #d4eceb;");
	    label4.setFont(Font.font("PT Sans Caption", FontWeight.BOLD, 15));
//Submit button - Will Add data to database
	    
 CustomButtonmain filterButton = new CustomButtonmain("Filter");
 CustomButtonmain reset = new CustomButtonmain("reset");
 filterButton.setOnAction(e -> {
 	DropdownObject dropdownObject = new DropdownObject(
             dropdown1.getValue(),
             dropdown2,
             dropdown3,
             dropdown4
     );

     // Access dropdown values using the DropdownObject methods
     System.out.println("Selected Project: " + dropdownObject.getProject());
     printSelectedItems1(dropdownObject.getLifeCycleStep());
     printSelectedItems2(dropdownObject.getEffortCategory());
     printSelectedItems3(dropdownObject.getDeliverable());
//     for (EffortData effortData : rowData2) {
//         if (dropdownObject.getLifeCycleStep().getItems().stream()
//                 .anyMatch(checkBox -> checkBox.getText().equals(effortData.getLifeCycleStep()))) {
//             System.out.println("Found match in rowData2: " + effortData.getLifeCycleStep());
//         }
//     }
     List<EffortData> filteredData = rowData.stream()
             .filter(data -> LifeCycleStep.contains(data.lifeCycleStepProperty().get()))
             .filter(data -> EffortCategory.contains(data.effortCategoryProperty().get()))
             .filter(data -> Deliverable.contains(data.deliverableProperty().get()))
             .collect(Collectors.toList());

     // Update the table with the filtered data
     DoubleSummaryStatistics stats = FXCollections.observableArrayList(filteredData).stream()
             .mapToDouble(data -> {
                 // Parse the time string and convert to seconds
                 String[] partsp = data.deltaTimeProperty().get().split(":");
                 int minutes = Integer.parseInt(partsp[0]);
                 int seconds = Integer.parseInt(partsp[1]);
                 return minutes * 60 + seconds;
             })
             .summaryStatistics();

     // Convert the total seconds back to minutes and seconds for display
     int averageMinutes = (int) (stats.getAverage() / 60);
     int averageSeconds = (int) (stats.getAverage() % 60);

     // Display the average, maximum, and minimum delta time
     averageLabel.setText("Avg Time: " + String.format("%02d:%02d", averageMinutes, averageSeconds));
     maxLabel.setText("Max Time: " + String.format("%02d:%02d", (int) (stats.getMax() / 60), (int) (stats.getMax() % 60)));
     minLabel.setText("Min Time: " + String.format("%02d:%02d", (int) (stats.getMin() / 60), (int) (stats.getMin() % 60)));

     table.setItems(FXCollections.observableArrayList(filteredData));
     FXCollections.observableArrayList(filteredData).clear();
     filteredData.clear();
     LifeCycleStep.clear();
     EffortCategory.clear();
     Deliverable.clear();
     reset.setVisible(true);
 });

 reset.setVisible(false);
 reset.setOnAction(e -> {
	 	dropdown1.setValue(null); 
	    dropdown2.setValue(null); 
	    dropdown3.setValue(null);
	    dropdown4.setValue(null); 
	    reset.setVisible(false);
	    rowData.clear();
	    loadDataFromFile("testData.txt");
	    table.setItems(rowData);
	    averageLabel.setText("Avg Time: ");
	    maxLabel.setText("Max Time: ");
	    minLabel.setText("Min Time: ");

	    // Reset combo boxes to their default values
	    
	});
 dropdown1.setOnAction(event -> getItemsForLifeCycleStep2(dropdown1.getValue(), dropdown2));
 
        
        
        
        
        
        
        

        // Set background color or image for the page
        HBox bottomBar = new HBox();
        
        bottomBar.setStyle("-fx-background-color: #161b2f;"); // Set bar background color
        bottomBar.setPrefHeight(70); // Set the height to 50

        // Add a label to the bottom bar (optional)
        Label bottomLabel = new Label("");
        bottomLabel.setStyle("-fx-text-fill: #d4eceb;"); // Set label text color
        bottomLabel.setFont(Font.font("PT Sans Caption", FontWeight.BOLD, 18));
        bottomLabel.setAlignment(Pos.CENTER); // Center the text vertically
        bottomLabel.setPadding(new Insets(5));
        bottomBar.getChildren().addAll(bottomLabel,grid);
        VBox buttonVBox = new VBox(greetingLabel,logoutButton,goToThirdPageButton, goToFourthPageButton,filterButton,reset,averageLabel,maxLabel,minLabel);
        buttonVBox.setSpacing(10);
        buttonVBox.setPadding(new Insets(10)); // Add padding here
        grid.setPadding(new Insets(10, 0, 30, 0)); 
        root.setStyle("-fx-background-color: #161b2f;"); // Set background color
        root.setTop(topBar); // Add the top bar to the top of the BorderPane
        root.setCenter(table);
        root.setRight(buttonVBox);
        root.setPadding(new Insets(0));
        // Add the bottom bar to the bottom of the BorderPane
        root.setBottom(bottomBar);

        // Adjust spacing between the center content and the bottom bar
        root.setMargin(table, new Insets(0, 0, 0, 0)); // Add margin at the bottom

        // Set background color or image for the page
        root.setStyle("-fx-background-color: #161b2f;"); // Set background color
        
        logOut = new Scene(root, 800, 800);
        logOut.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        return logOut;
    }

    private void printSelectedItems(ComboBox<CheckBox> comboBox) {
        System.out.println("Selected Items:");

        for (CheckBox checkBox : comboBox.getItems()) {
            if (checkBox.isSelected()) {
                System.out.println(checkBox.getText());
            }
        }
    }
    private void printSelectedItems1(ComboBox<CheckBox> comboBox) {
        System.out.println("Selected Items:");

        for (CheckBox checkBox : comboBox.getItems()) {
            if (checkBox.isSelected()) {
                System.out.println(checkBox.getText());
                LifeCycleStep.add(checkBox.getText());
            }
        }
    }
    private void printSelectedItems2(ComboBox<CheckBox> comboBox) {
        System.out.println("Selected Items:");

        for (CheckBox checkBox : comboBox.getItems()) {
            if (checkBox.isSelected()) {
                System.out.println(checkBox.getText());
                EffortCategory.add(checkBox.getText());
            }
        }
    }
    private void printSelectedItems3(ComboBox<CheckBox> comboBox) {
        System.out.println("Selected Items:");

        for (CheckBox checkBox : comboBox.getItems()) {
            if (checkBox.isSelected()) {
                System.out.println(checkBox.getText());
                Deliverable.add(checkBox.getText());
            }
        }
    }
    private void switchToThirdPage(Stage previousStage) throws IOException, InterruptedException {
        // Run the start method of ASUHelloWorldJavaFX with a new Stage
        Stage thirdStage = new Stage();
        ASUHelloWorldJavaFX asuHelloWorldJavaFX = new ASUHelloWorldJavaFX();
        asuHelloWorldJavaFX.start(thirdStage);

        // Create a TextArea to display the output from the Node.js script
        TextArea outputTextArea = new TextArea();
        outputTextArea.setEditable(false);

        // Add the TextArea to the layout
        StackPane layout = new StackPane();
        layout.getChildren().addAll(outputTextArea);

        // Create the third page Scene
        Scene thirdPageScene = new Scene(layout, 600, 400);

        // Set the third page Scene on the thirdStage
        thirdStage.setScene(thirdPageScene);

        // Show the thirdStage
        thirdStage.show();

        // Create a button to go back to the previous stage
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            thirdStage.close(); // Close the current (third) stage
            previousStage.show(); // Show the previous stage
        });

        // Add the back button to the layout
        layout.getChildren().add(backButton);

        // Run the Node.js script and display the output in the TextArea
        try {
            String consoleOutputFilePath = "consoleOutput.txt";
            List<YourDataClass> dataList = ASUHelloWorldJavaFX.runNodeProcessAndParseFile(consoleOutputFilePath);

            // Display the data in the TextArea
            for (YourDataClass data : dataList) {
                outputTextArea.appendText("Category: " + data.getCategory() + "\n");
                outputTextArea.appendText("Date: " + data.getDate() + "\n");
                outputTextArea.appendText("Deliverable: " + data.getDeliverable() + "\n");
                outputTextArea.appendText("Dtime: " + data.getDtime() + "\n");
                outputTextArea.appendText("Lifecycle: " + data.getLifecycle() + "\n");
                outputTextArea.appendText("Start: " + data.getStart() + "\n");
                outputTextArea.appendText("Stop: " + data.getStop() + "\n\n");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void updateClock(Label clockLabel) {
        if (timerStatus.get().equals("Started")) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - startTime;

            long minutes = (elapsedTime / 1000) / 60;
            long seconds = (elapsedTime / 1000) % 60;

            String currentTimeStr = String.format("%02d:%02d", minutes, seconds);
            clockLabel.setText(currentTimeStr);
        }
    }
    private void switchToFourthPage(Stage previousStage) {
        // Set Up Window
    	 Stage fourthStage = new Stage();
    	    BorderPane root = new BorderPane();

    	    // Create the top bar
    	    HBox topBar = createTopBar("effortlogger");

    	    // Set up the grid
    	    GridPane grid = new GridPane();
    	    grid.setHgap(12);
    	    grid.setVgap(12);
    	    grid.setPadding(new Insets(10, 10, 10, 10));


        // Timer button
    	    CustomButtonlog2 timerButton = new CustomButtonlog2("Start");
    	    //timerButton.setStyle("-fx-background-color: red;");
    	    timerButton.setOnAction(e -> handleTimerButton(timerButton));

    	    // Clock label next to the button
    	    Label clockLabel = new Label("00:00");
    	    clockLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
    	    grid.add(timerButton, 0, 0);
    	    grid.add(clockLabel, 1, 0);
    	    KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
    	        @Override
    	        public void handle(ActionEvent event) {
    	            updateClock(clockLabel);
    	        }
    	    });
    	    clockTimeline = new Timeline(keyFrame);
    	    clockTimeline.setCycleCount(Timeline.INDEFINITE);
    	    
        // Drop down 1 - Project
     // Drop down 1 - Project
     // Drop down 1 - Project
     // Drop down 1 - Project
        Label label1 = new Label("Project:");
        label1.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        ComboBox<String> dropdown1 = new ComboBox<>();
        dropdown1.getItems().addAll("Business Project", "Development Project");
        grid.add(label1, 0, 1);
        grid.add(dropdown1, 1, 1);

        // Drop Down 2 - Life Cycle Step
        Label label2 = new Label("Life Cycle Step:");
        ComboBox<String> dropdown2 = new ComboBox<>();
        grid.add(label2, 0, 2);
        grid.add(dropdown2, 1, 2);
        label2.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        // Drop Down 3 - Effort Categories
        Label label3 = new Label("Effort Category:");
        ComboBox<String> dropdown3 = new ComboBox<>();
        dropdown3.getItems().addAll("Plans", "Deliverables", "Interuptions", "Defects", "Others");
        grid.add(label3, 0, 3);
        grid.add(dropdown3, 1, 3);
        label3.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");

        // Drop Down 4 - Label will change Based on prior boxes
        Label label4 = new Label("Deliverable:");
        ComboBox<String> dropdown4 = new ComboBox<>();
        dropdown4.getItems().addAll("Project Plan", "Risk Management Plan", "Conceptual Design Plan", "Detailed Design Plan", "Implimentation Plan");
        grid.add(label4, 0, 4);
        grid.add(dropdown4, 1, 4);
        label4.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");

        // Submit button - Will Add data to database
        CustomButtonlog2 submitButton = new CustomButtonlog2("Submit");
        submitButton.setOnAction(e -> {
            handleSubmitButton(dropdown1, dropdown2, dropdown3, dropdown4,Name);
            fourthStage.close(); // Close the current (fourth) stage after submitting
        });
        grid.add(submitButton, 0, 5);
        
        // Timer status label - May remove
        Label timerStatusLabel = new Label("Timer Status:");
        Label timerStatusValueLabel = new Label();
        timerStatusValueLabel.textProperty().bind(timerStatus);
        timerStatusLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        timerStatusValueLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        grid.add(timerStatusLabel, 0, 6);
        grid.add(timerStatusValueLabel, 1, 6);
        dateLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        startTimeLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        endTimeLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        totalTimeLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        submitLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        
        grid.add(dateLabel, 0, 7);
        grid.add(startTimeLabel, 0, 8);
        grid.add(endTimeLabel, 0, 9);
        grid.add(totalTimeLabel, 0, 10);
        grid.add(submitLabel, 0, 11);

        // Dropdown selection change handler
        dropdown1.setOnAction(event -> getItemsForLifeCycleStep(dropdown1.getValue(), dropdown2));

        // Add grid to the center of the BorderPane
        root.setCenter(grid);

        Scene fourthPageScene = new Scene(root, 450, 520);
        fourthPageScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        fourthStage.setScene(fourthPageScene);
        
        // Show the fourth stage
        fourthStage.show();

        // Create a button to go back to the previous stage
        CustomButtonlog2 backButton = new CustomButtonlog2("Back");
        backButton.setOnAction(e -> {
            fourthStage.close(); // Close the current (fourth) stage
            previousStage.show(); // Show the previous stage
        });

        // Add the back button to the layout
        grid.add(backButton, 0, 12);
     // Set the background color for the BorderPane
        root.setStyle("-fx-background-color: #161b2f;");
        fourthStage.setOnShown(e -> clockTimeline.play());
        // Add the top bar to the top of the BorderPane
        root.setTop(topBar);
    }
    private void switchToFithPage(Stage previousStage) {
        // Set Up Window
    	 Stage fourthStage = new Stage();
    	    BorderPane root = new BorderPane();

    	    // Create the top bar
    	    HBox topBar = createTopBar("effortlogger");

    	    // Set up the grid
    	    GridPane grid = new GridPane();
    	    grid.setHgap(12);
    	    grid.setVgap(12);
    	    grid.setPadding(new Insets(10, 10, 10, 10));


        // Timer button
    	    CustomButtonlog2 timerButton = new CustomButtonlog2("Start");
    	    //timerButton.setStyle("-fx-background-color: red;");
    	    timerButton.setOnAction(e -> handleTimerButton(timerButton));

    	    // Clock label next to the button
    	    Label clockLabel = new Label("00:00");
    	    clockLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
    	    //grid.add(timerButton, 0, 0);
    	   // grid.add(clockLabel, 1, 0);
    	    KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
    	        @Override
    	        public void handle(ActionEvent event) {
    	            updateClock(clockLabel);
    	        }
    	    });
    	    clockTimeline = new Timeline(keyFrame);
    	    clockTimeline.setCycleCount(Timeline.INDEFINITE);
    	    
        // Drop down 1 - Project
     // Drop down 1 - Project
     // Drop down 1 - Project
     // Drop down 1 - Project
        Label label1 = new Label("Project:");
        label1.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        ComboBox<String> dropdown1 = new ComboBox<>();
        dropdown1.getItems().addAll("Business Project", "Development Project");

        // Set custom cell factory to include checkboxes
      
        grid.add(label1, 0, 1);
        grid.add(dropdown1, 1, 1);

        // Drop Down 2 - Life Cycle Step
        Label label2 = new Label("Life Cycle Step:");
        ComboBox<CheckBox> dropdown2 = new ComboBox<>();
        dropdown2.disableProperty();
        grid.add(label2, 0, 2);
        grid.add(dropdown2, 1, 2);
        label2.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");

        // Drop Down 3 - Effort Categories
        Label label3 = new Label("Effort Category:");
        ComboBox<CheckBox> dropdown3 = new ComboBox<>();
       
        dropdown3.getItems().addAll(
                createCheckBox("Plans"),
                createCheckBox("Deliverables"),
                createCheckBox("Interuptions"),
                createCheckBox("Defects"),
                createCheckBox("Others")
        );
        dropdown3.disableProperty();
        grid.add(label3, 0, 3);
        grid.add(dropdown3, 1, 3);
        label3.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");

        // Drop Down 4 - Label will change Based on prior boxes
        Label label4 = new Label("Deliverable:");
        ComboBox<CheckBox> dropdown4 = new ComboBox<>();
        
        
        dropdown4.getItems().addAll(
                createCheckBox("Project Plan"),
                createCheckBox("Risk Management Plan"),
                createCheckBox("Conceptual Design Plan"),
                createCheckBox("Detailed Design Plan"),
                createCheckBox("Implimentation Plan")
        );
        dropdown4.disableProperty();
        grid.add(label4, 0, 4);
        grid.add(dropdown4, 1, 4);
        label4.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");

        // Submit button - Will Add data to database
        CustomButtonlog2 submitButton = new CustomButtonlog2("Submit");
        submitButton.setOnAction(e -> {
        	DropdownObject dropdownObject = new DropdownObject(
                    dropdown1.getValue(),
                    dropdown2,
                    dropdown3,
                    dropdown4
            );

            // Access dropdown values using the DropdownObject methods
            System.out.println("Selected Project: " + dropdownObject.getProject());
            printSelectedItems(dropdownObject.getLifeCycleStep());
            printSelectedItems(dropdownObject.getEffortCategory());
            printSelectedItems(dropdownObject.getDeliverable());
            
//            for (EffortData effortData : rowData2) {
//                if (dropdownObject.getLifeCycleStep().getItems().stream()
//                        .anyMatch(checkBox -> checkBox.getText().equals(effortData.getLifeCycleStep()))) {
//                    System.out.println("Found match in rowData2: " + effortData.getLifeCycleStep());
//                }
//            }
            
            
            //handleSubmitButton(dropdown1, dropdown2, dropdown3, dropdown4,Name);
            //fourthStage.close(); // Close the current (fourth) stage after submitting
        });
        grid.add(submitButton, 0, 5);
        
        // Timer status label - May remove
        Label timerStatusLabel = new Label("Timer Status:");
        Label timerStatusValueLabel = new Label();
        timerStatusValueLabel.textProperty().bind(timerStatus);
        timerStatusLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        timerStatusValueLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        dropdown1.setOnAction(event -> getItemsForLifeCycleStep2(dropdown1.getValue(), dropdown2));
        CustomButtonlog2 backButton = new CustomButtonlog2("Back");
        backButton.setOnAction(e -> {
            fourthStage.close(); // Close the current (fourth) stage
            previousStage.show(); // Show the previous stage
        });

        // Add the back button to the layout
        grid.add(backButton, 0, 6);
        // Add grid to the center of the BorderPane
        root.setCenter(grid);
        
        Scene fourthPageScene = new Scene(root, 450, 520);
        fourthPageScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        fourthStage.setScene(fourthPageScene);
        
        // Show the fourth stage
        fourthStage.show();

        // Create a button to go back to the previous stage
        
     // Set the background color for the BorderPane
        root.setStyle("-fx-background-color: #161b2f;");
        fourthStage.setOnShown(e -> clockTimeline.play());
        // Add the top bar to the top of the BorderPane
        root.setTop(topBar);
    }
    private CheckBox createCheckBox(String text) {
        CheckBox checkBox = new CheckBox(text);
        checkBox.setSelected(false); // Initially, none selected
        return checkBox;
    }


    private void openDetailedView(EffortData rowData) {
        // Create a new stage for the detailed view 
    		//Might be a good idea to have it pull directly from the database insted of table in final ver.
    		//If we have time to implement the effort data editor, use this as a base.
    	 
        Stage detailedStage = new Stage();
        BorderPane detailedRoot = new BorderPane();
        HBox topBar = createTopBar("effortlogger");
        Label numberLabel = new Label("Data Number:");
        numberLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        TextField numberTextField = new TextField();
        numberTextField.textProperty().set(rowData.numberProperty().get());
//        numberTextField.textProperty().bind(rowData.numberProperty());
        numberTextField.setStyle("-fx-font-family: 'PT Sans Caption'; -fx-text-fill: #272F51; -fx-background-color: #D0FFF6;");
        Label dateLabel = new Label("Date:");
        dateLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        TextField dateTextField = new TextField();
        dateTextField.textProperty().set(rowData.dateProperty().get());
        dateTextField.setStyle("-fx-font-family: 'PT Sans Caption'; -fx-text-fill: #272F51; -fx-background-color: #D0FFF6;");
        Label startTimeLabel = new Label("Start Time:");
        startTimeLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        TextField startTimeTextField = new TextField();
        startTimeTextField.textProperty().set(rowData.startTimeProperty().get());
        startTimeTextField.setStyle("-fx-font-family: 'PT Sans Caption'; -fx-text-fill: #272F51; -fx-background-color: #D0FFF6;");
        
        Label stopTimeLabel = new Label("Stop Time:");
        stopTimeLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        TextField stopTimeTextField = new TextField();
        stopTimeTextField.textProperty().set(rowData.stopTimeProperty().get());
        stopTimeTextField.setStyle("-fx-font-family: 'PT Sans Caption'; -fx-text-fill: #272F51; -fx-background-color: #D0FFF6;");
        
        Label deltaTimeLabel = new Label("Delta Time:");
        deltaTimeLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        TextField deltaTimeTextField = new TextField();
        deltaTimeTextField.textProperty().set(rowData.deltaTimeProperty().get());
        deltaTimeTextField.setStyle("-fx-font-family: 'PT Sans Caption'; -fx-text-fill: #272F51; -fx-background-color: #D0FFF6;");
        
        Label lifeCycleLabel = new Label("Life Cycle Step:");
        lifeCycleLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        TextField lifeCycleTextField = new TextField();
        lifeCycleTextField.textProperty().set(rowData.lifeCycleStepProperty().get());
        lifeCycleTextField.setStyle("-fx-font-family: 'PT Sans Caption'; -fx-text-fill: #272F51; -fx-background-color: #D0FFF6;");
        
        Label effortLabel = new Label("Effort Category:");
        effortLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        TextField effortTextField = new TextField();
        effortTextField.textProperty().set(rowData.effortCategoryProperty().get());
        effortTextField.setStyle("-fx-font-family: 'PT Sans Caption'; -fx-text-fill: #272F51; -fx-background-color: #D0FFF6;");
        
        Label deliverableLabel = new Label("Deliverable:");
        deliverableLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        TextField deliverableTextField = new TextField();
        deliverableTextField.textProperty().set(rowData.deliverableProperty().get());
        deliverableTextField.setStyle("-fx-font-family: 'PT Sans Caption'; -fx-text-fill: #272F51; -fx-background-color: #D0FFF6;");
        
        
        
        
        
        
        Label numberLabel1 = new Label("Data Number: " + rowData.numberProperty().get());
        numberLabel1.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        
        Label dateLabel1 = new Label("Date: " + rowData.dateProperty().get());
        dateLabel1.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        
        Label startTimeLabel1 = new Label("Start Time: " + rowData.startTimeProperty().get());
        startTimeLabel1.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        
        Label stopTimeLabel1 = new Label("Stop Time: " + rowData.stopTimeProperty().get());
        stopTimeLabel1.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        
        Label deltaTimeLabel1 = new Label("Delta Time: " + rowData.deltaTimeProperty().get());
        deltaTimeLabel1.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        
        Label lifeCycleLabel1 = new Label("Life Cycle Step: " + rowData.lifeCycleStepProperty().get());
        lifeCycleLabel1.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        
        Label effortLabel1 = new Label("Effort Category: " + rowData.effortCategoryProperty().get());
        effortLabel1.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        
        Label deliverableLabel1 = new Label("Deliverable: " + rowData.deliverableProperty().get());
        deliverableLabel1.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';");
        
        
        
        
        
        
        
        
        
        
        
        //Setup display 
        CustomButtonlog2 closeButton = new CustomButtonlog2("Save");
        
//        if() {
//        	
//        }else {
//        	
//        }
        
        
        
        boolean val = true;
        System.out.println(isSup);
if (Name.equals(rowData.userProperty().get().toString()) || acess == true) {
	closeButton.setOnAction(event -> {
    	System.out.println(effortTextField.getText());
        // Close the window
    	try {
			updatedata(
					effortTextField.getText(),
					  dateTextField.getText(),
					  deliverableTextField.getText(),
					  deltaTimeTextField.getText(),
					  lifeCycleTextField.getText(),
					  startTimeTextField.getText(),
					  stopTimeTextField.getText(),
					  rowData.userProperty().get(),
					  rowData.idProperty().get()
					);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	detailedStage.close();

        // Run your method here
        // Example: runMyMethod();
    });
	VBox detailsVBox = new VBox(
    		numberLabel, numberTextField,
    		dateLabel, dateTextField,
            startTimeLabel, startTimeTextField,
            stopTimeLabel, stopTimeTextField,
            deltaTimeLabel, deltaTimeTextField,
            lifeCycleLabel, lifeCycleTextField,
            effortLabel, effortTextField,
            deliverableLabel, deliverableTextField, closeButton
    );
	detailsVBox.setSpacing(10);
	detailsVBox.setPadding(new Insets(10));
    detailedRoot.setTop(topBar);
    detailedRoot.setCenter(detailsVBox);
    detailedRoot.setStyle("-fx-background-color: #161b2f;");

    detailedRoot.setPadding(new Insets(0));
    Scene detailedScene = new Scene(detailedRoot, 400, 630);

    detailedStage.setScene(detailedScene);
    detailedStage.show();
    	}else {
    		closeButton.setText("Close");
    		closeButton.setOnAction(event -> {
            	System.out.println(effortTextField.getText());
                // Close the window
            	
            	detailedStage.close();

                // Run your method here
                // Example: runMyMethod();
            });
    		VBox detailsVBox = new VBox(
    	    		numberLabel1,
    	    		dateLabel1,
    	            startTimeLabel1,
    	            stopTimeLabel1,
    	            deltaTimeLabel1,
    	            lifeCycleLabel1,
    	            effortLabel1,
    	            deliverableLabel1, closeButton
    	    );
    		detailsVBox.setSpacing(10);
    		detailsVBox.setPadding(new Insets(10));
    		detailedRoot.setStyle("-fx-background-color: #161b2f;");

    	    detailedRoot.setTop(topBar);
    	    detailedRoot.setCenter(detailsVBox);

    	    detailedRoot.setPadding(new Insets(0));
    	    Scene detailedScene = new Scene(detailedRoot, 400, 370);

    	    detailedStage.setScene(detailedScene);
    	    detailedStage.show();
                	}
        
    }
    private HBox createTopBar(String title) {
        HBox topBar = new HBox();
        topBar.setStyle("-fx-background-color: #06c8a1;");
        topBar.setPrefHeight(50);

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-text-fill: #d4eceb;");
        titleLabel.setFont(Font.font("PT Sans Caption", FontWeight.BOLD, 35));
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setPadding(new Insets(15, 0, 5, 10));

        ImageView logoImageView = new ImageView();
        Image logoImage = new Image(getClass().getResourceAsStream("effortlogger.png"));
        logoImageView.setImage(logoImage);

        HBox.setHgrow(logoImageView, Priority.ALWAYS);
        HBox.setMargin(logoImageView, new Insets(10, 20, 10, 10));

        topBar.getChildren().addAll(titleLabel, new Region(), logoImageView);
        HBox.setHgrow(topBar.getChildren().get(1), Priority.ALWAYS);

        return topBar;
    }
    
    //loads data from table - This should be replaced by database functions in final version
    private void loadDataFromFile(String filePath) {
 
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Loop for each line of file
            String consoleOutputFilePath = "consoleOutput.txt";
            List<YourDataClass> dataList = ASUHelloWorldJavaFX.runNodeProcessAndParseFile(consoleOutputFilePath);
            int a = 0;
            // Display the data in the TextArea
            for (YourDataClass data : dataList) {
            	a++;
            	String s=Integer.toString(a);
                rowData.add(new EffortData(s, data.getDate(), data.getStart(), data.getStop(), data.getDtime(), data.getLifecycle(), data.getCategory(), data.getDeliverable(),data.getUser(),data.getId()));
                
            }
                      
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private Scene createOpen() throws IOException {
    	if (hasContentInFile("consoleOutput5.txt")) {
            String contentToCheck = getContentOfFile("consoleOutput5.txt");
            
            if (isContentInFile("consoleOutput4.txt", contentToCheck)) {
                System.out.println("Content from consoleOutput5.txt is present in consoleOutput4.txt");
                return effortLogger(primaryStage);
            } else {
                System.out.println("Content from consoleOutput5.txt is not present in consoleOutput4.txt");
                clearConsoleOutputFile("consoleOutput5.txt");
            }
        }

    	    // The rest of your code remains unchanged
    	    CustomButton loginButton = new CustomButton("login");
    	    CustomButtonvert createButton = new CustomButtonvert("register");
    	    loginButton.setOnAction(e -> switchToLogin());
    	    createButton.setOnAction(e -> switchTocreateAcc());
        

        // Create the bar at the top
        HBox topBar = new HBox();
        topBar.setStyle("-fx-background-color: #06c8a1;"); // Set bar background color
        topBar.setPrefHeight(50); // Set the height to 50

        // Add a label to the top bar
        Label titleLabel = new Label("effortlogger");
        titleLabel.setStyle("-fx-text-fill: #d4eceb;"); // Set label text color
        titleLabel.setFont(Font.font("PT Sans Caption", FontWeight.BOLD, 35));
        titleLabel.setAlignment(Pos.CENTER); // Center the text vertically
        titleLabel.setPadding(new Insets(15, 0, 5, 10)); // Adjust padding as needed

        ImageView logoImageView = new ImageView();
        Image logoImage = new Image(getClass().getResourceAsStream("effortlogger.png"));

        logoImageView.setImage(logoImage);

        HBox.setHgrow(logoImageView, Priority.ALWAYS); // Allow the image to take available space
        HBox.setMargin(logoImageView, new Insets(10, 20, 10, 10)); // Adjust margins to add more space around the image

        topBar.getChildren().addAll(titleLabel, new Region(), logoImageView); // Add a Region to push the image to the right
        HBox.setHgrow(topBar.getChildren().get(1), Priority.ALWAYS); // Make the Region take available space

        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: #161b2f;"); // Set background color
        layout.setTop(topBar);

        VBox buttonBox = new VBox(30); // Set spacing between buttons (e.g., 20 pixels)
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10, 0, 0, 0)); // Add padding to the top of the VBox
        buttonBox.getChildren().addAll(loginButton, createButton);
        layout.setCenter(buttonBox);


        Scene logOut = new Scene(layout, 400, 400); // Adjust the size as needed

        return logOut;
    }

  


    
    private boolean hasContentInTheFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.readLine() != null; // Returns true if there is at least one line in the file
        }
    }

    private String getContentOfFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString().trim(); // Trim to remove trailing newline
    }

    private boolean isContentInFile(String filePath, String targetContent) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(targetContent)) {
                    return true;
                }
            }
        }
        return false;
    }
    private Scene createLogin() {
    	primaryStage.setTitle("Login");

        // Create the bar at the top
        HBox topBar = new HBox();
        topBar.setStyle("-fx-background-color: #06c8a1;"); // Set bar background color
        topBar.setPrefHeight(50); // Set the height to 50


        // Add a label to the top bar
        Label titleLabel = new Label("effortlogger");
        titleLabel.setStyle("-fx-text-fill: #d4eceb;"); // Set label text color
        titleLabel.setFont(Font.font("PT Sans Caption", FontWeight.BOLD, 35));
        titleLabel.setAlignment(Pos.CENTER); // Center the text vertically
        titleLabel.setPadding(new Insets(15, 0, 5, 10)); // Adjust padding as needed

        ImageView logoImageView = new ImageView();
        Image logoImage = new Image(getClass().getResourceAsStream("effortlogger.png"));

        logoImageView.setImage(logoImage);

        HBox.setHgrow(logoImageView, Priority.ALWAYS); // Allow the image to take available space
        HBox.setMargin(logoImageView, new Insets(10, 20, 10, 10)); // Adjust margins to add more space around the image

        topBar.getChildren().addAll(titleLabel, new Region(), logoImageView); // Add a Region to push the image to the right
        HBox.setHgrow(topBar.getChildren().get(1), Priority.ALWAYS); // Make the Region take available space

        // Create the login form
        GridPane logingrid = new GridPane();
        logingrid.setPadding(new Insets(20, 20, 20, 20));
        logingrid.setVgap(10);
        logingrid.setHgap(10);

        // Labels
        Label usernameLabel = new Label("Username:");
        usernameLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';"); // Set label text color and font
        GridPane.setConstraints(usernameLabel, 0, 0);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';"); // Set label text color and font
        GridPane.setConstraints(passwordLabel, 0, 1);

        // Input fields
        TextField usernameField = new TextField();
        usernameField.setStyle("-fx-font-family: 'PT Sans Caption'; -fx-text-fill: #272F51; -fx-background-color: #D0FFF6;");
        GridPane.setConstraints(usernameField, 1, 0);

        PasswordField passwordField = new PasswordField();
        passwordField.setStyle("-fx-font-family: 'PT Sans Caption'; -fx-text-fill: #272F51; -fx-background-color: #D0FFF6;");
        GridPane.setConstraints(passwordField, 1, 1);

        // Text element to overlay the password field
        Text overlayText = new Text();
        overlayText.visibleProperty().bind(passwordVisible);
        overlayText.textProperty().bind(passwordField.textProperty());
        overlayText.setStyle("-fx-font-family: 'PT Sans Caption'; -fx-text-fill: #272F51; -fx-background-color: transparent; -fx-border-color: #D0FFF6;");
        GridPane.setConstraints(overlayText, 1, 1);

        // Show Password button
        CustomButtonlog showPasswordButton = new CustomButtonlog("Show");
        GridPane.setConstraints(showPasswordButton, 2, 1);
        logingrid.getChildren().add(showPasswordButton);

        // Handle Show Password button action
        showPasswordButton.setOnAction(event -> {
            passwordVisible.set(!passwordVisible.get());
            showPasswordButton.setText(passwordVisible.get() ? "Hide" : "Show");

            // Set the text fill color to transparent when showing the password
            if (passwordVisible.get()) {
                passwordField.setStyle("-fx-font-family: 'PT Sans Caption'; -fx-text-fill: transparent; -fx-background-color: #D0FFF6;");
            } else {
                // Reset the style when hiding the password
                passwordField.setStyle("-fx-font-family: 'PT Sans Caption'; -fx-text-fill: #272F51; -fx-background-color: #D0FFF6;");
            }
        });

        // Login button
        CustomButtonlog loginToButton = new CustomButtonlog("login");
        GridPane.setConstraints(loginToButton, 1, 2);

        CustomButtonlog2 createButton = new CustomButtonlog2("register");
        createButton.setOnAction(e -> switchTocreateAcc());
        GridPane.setConstraints(createButton, 1, 3);

        // Label for displaying failed login attempts
        Label failedLogin = new Label();
        failedLogin.setStyle("-fx-text-fill: red; -fx-font-family: 'PT Sans Caption';");
        failedLogin.setAlignment(Pos.CENTER);

        logingrid.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, overlayText, loginToButton);

        // Login button action
        loginToButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (passwordCheck(username, password)) {
                switchToEffortLogger();
            } else {
                logingrid.getChildren().remove(createButton);
                logingrid.getChildren().add(createButton);
                loginAttempts++;
                int remainingAttempt = 3 - loginAttempts;
                failedLogin.setText("Username or Password incorrect. \n" + remainingAttempt + " Attempts remain");
                GridPane.setConstraints(failedLogin, 2, 3);
                logingrid.getChildren().removeAll(failedLogin);
                logingrid.getChildren().add(failedLogin);
            }

            if (loginAttempts == 3) {
                logingrid.getChildren().remove(loginToButton);
            }
        });

        // Combine top bar and login form
        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: #161b2f;"); // Set background color
        layout.setTop(topBar);
        layout.setCenter(logingrid);

        Scene loginScene = new Scene(layout, 500, 300); // Adjust the size as needed
        return loginScene;
    }



    
    
    private Scene createCreate() {
        primaryStage.setTitle("Register");

        // Create the bar at the top
        HBox topBar = new HBox();
        topBar.setStyle("-fx-background-color: #06c8a1;"); // Set bar background color
        topBar.setPrefHeight(50); // Set the height to 50

        // Add a label to the top bar
        Label titleLabel = new Label("effortlogger");
        titleLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';"); // Set label text color and font
        titleLabel.setFont(Font.font("PT Sans Caption", FontWeight.BOLD, 35));
        titleLabel.setAlignment(Pos.CENTER); // Center the text vertically
        titleLabel.setPadding(new Insets(15, 0, 5, 10)); // Adjust padding as needed

        // Add the logo to the top bar
        ImageView logoImageView = new ImageView();
        Image logoImage = new Image(getClass().getResourceAsStream("effortlogger.png"));
        logoImageView.setImage(logoImage);
        HBox.setHgrow(logoImageView, Priority.ALWAYS); // Allow the image to take available space
        HBox.setMargin(logoImageView, new Insets(10, 20, 10, 10)); // Adjust margins to add more space around the image

        topBar.getChildren().addAll(titleLabel, new Region(), logoImageView); // Add a Region to push the image to the right
        HBox.setHgrow(topBar.getChildren().get(1), Priority.ALWAYS); // Make the Region take available space

        // Create the grid for the registration form
        GridPane creategrid = new GridPane();
        creategrid.setPadding(new Insets(20, 20, 20, 20));
        creategrid.setVgap(10);
        creategrid.setHgap(10);

        // Set background color or image for the registration form
        creategrid.setStyle("-fx-background-color: #161b2f;"); // Set background color

        // Labels
        Label usernameCreateLabel = new Label("Username:");
        usernameCreateLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';"); // Set label text color and font
        GridPane.setConstraints(usernameCreateLabel, 0, 0);

        Label passwordCreateLabel = new Label("Password:");
        passwordCreateLabel.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';"); // Set label text color and font
        GridPane.setConstraints(passwordCreateLabel, 0, 1);

        Label passwordCreateLabel2 = new Label("Re-enter Password:");
        passwordCreateLabel2.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';"); // Set label text color and font
        GridPane.setConstraints(passwordCreateLabel2, 0, 2);

        // Input fields
        TextField usernameCreateField = new TextField();
        usernameCreateField.setStyle("-fx-font-family: 'PT Sans Caption'; -fx-text-fill: #272F51; -fx-background-color: #D0FFF6;");
        GridPane.setConstraints(usernameCreateField, 1, 0);

        PasswordField passwordCreateField = new PasswordField();
        passwordCreateField.setStyle("-fx-font-family: 'PT Sans Caption'; -fx-text-fill: #272F51; -fx-background-color: #D0FFF6;");
        GridPane.setConstraints(passwordCreateField, 1, 1);

        PasswordField passwordCreateField2 = new PasswordField();
        passwordCreateField2.setStyle("-fx-font-family: 'PT Sans Caption'; -fx-text-fill: #272F51; -fx-background-color: #D0FFF6;");
        GridPane.setConstraints(passwordCreateField2, 1, 2);

        // Show Password button
        CustomButtonlog showPasswordButton = new CustomButtonlog("Show");
        GridPane.setConstraints(showPasswordButton, 2, 1);
        creategrid.getChildren().add(showPasswordButton);

        // Handle Show Password button action for both password fields
        showPasswordButton.setOnAction(event -> {
            passwordVisible.set(!passwordVisible.get());
            showPasswordButton.setText(passwordVisible.get() ? "Hide" : "Show");

            // Set the text fill color to transparent only when showing the password
            if (passwordVisible.get()) {
                // Use a temporary TextField to display the password
                TextField tempPasswordField = new TextField(passwordCreateField.getText());
                tempPasswordField.setStyle(passwordCreateField.getStyle());
                tempPasswordField.textProperty().bindBidirectional(passwordCreateField.textProperty());
                GridPane.setConstraints(tempPasswordField, 1, 1);
                creategrid.getChildren().remove(passwordCreateField);
                creategrid.getChildren().add(tempPasswordField);

                TextField tempPasswordField2 = new TextField(passwordCreateField2.getText());
                tempPasswordField2.setStyle(passwordCreateField2.getStyle());
                tempPasswordField2.textProperty().bindBidirectional(passwordCreateField2.textProperty());
                GridPane.setConstraints(tempPasswordField2, 1, 2);
                creategrid.getChildren().remove(passwordCreateField2);
                creategrid.getChildren().add(tempPasswordField2);
            } else {
                // Reset the style when hiding the password
                creategrid.getChildren().removeAll(passwordCreateField, passwordCreateField2);

                // Re-add the PasswordFields to the grid
                GridPane.setConstraints(passwordCreateField, 1, 1);
                creategrid.getChildren().add(passwordCreateField);

                GridPane.setConstraints(passwordCreateField2, 1, 2);
                creategrid.getChildren().add(passwordCreateField2);
            }
        });

        ToggleGroup empSup = new ToggleGroup();
        RadioButton emp = new RadioButton("Employee");
        RadioButton sup = new RadioButton("Supervisor");
        
        emp.setToggleGroup(empSup);
        sup.setToggleGroup(empSup);
        
        emp.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';"); // Set radio button text color and font
        sup.setStyle("-fx-text-fill: #d4eceb; -fx-font-family: 'PT Sans Caption';"); // Set radio button text color and font

        GridPane.setConstraints(sup, 1, 3);
        GridPane.setConstraints(emp, 0, 3);

        // Create Account button
        CustomButtonlog2 createAccButton = new CustomButtonlog2("register");
        GridPane.setConstraints(createAccButton, 0, 4);

        creategrid.getChildren().addAll(emp, sup, usernameCreateLabel, usernameCreateField, passwordCreateLabel,
                passwordCreateField, passwordCreateLabel2, passwordCreateField2, createAccButton);

        empSup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> arg0, Toggle oldValue, Toggle newValue) {
                if (newValue == emp) {
                    isSup = false;
                    empsupSelect = true;
                }
                if (newValue == sup) {
                    isSup = true;
                    empsupSelect = true;
                }
            }
        });

        createAccButton.setOnAction(event -> {
            String username = usernameCreateField.getText();
            String password = passwordCreateField.getText();
            String password2 = passwordCreateField2.getText();
            
            if (username.isEmpty()) {
                // Username field is empty
                failedLogin.setText("Please enter a username.");
                GridPane.setConstraints(failedLogin, 2, 0); // Place near the username field
            } else if (username.length() < 3) {
                // Username does not meet length requirement
                failedLogin.setText("Username must be at least 3 characters long.");
                GridPane.setConstraints(failedLogin, 2, 0); // Place near the username field
            } else if (!usernameFormat(username)) {
                // Username format validation failed
                failedLogin.setText("Invalid username format.");
                GridPane.setConstraints(failedLogin, 2, 0); // Place near the username field
            } else if (password.isEmpty()) {
                // Password field is empty
                failedLogin.setText("Please enter a password.");
                GridPane.setConstraints(failedLogin, 2, 1); // Place near the password field
            } else if (passwordFormat(password) == 1) {
                // Password format validation failed
                failedLogin.setText("Invalid password format. Needs Special Character");
                GridPane.setConstraints(failedLogin, 2, 1); // Place near the password field
            } else if (passwordFormat(password) == 2) {
                // Password format validation failed
                failedLogin.setText("Invalid password format. Needs Capital");
                GridPane.setConstraints(failedLogin, 2, 1); // Place near the password field
            } else if (passwordFormat(password) == 3) {
                // Password format validation failed
                failedLogin.setText("Invalid password format. Needs to be 8 characters");
                GridPane.setConstraints(failedLogin, 2, 1); // Place near the password field
            } else if (!password.equals(password2)) {
                // Passwords don't match
                failedLogin.setText("The entered passwords do not match.");
                GridPane.setConstraints(failedLogin, 2, 2); // Place near the re-enter password field
            } else {
                accountExist = false;
                if (!accountList.isEmpty()) {
                    for (Account account : accountList) {
                        if (account.Username.equals(username)) {
                            accountExist = true;
                            // Account already exists
                            failedLogin.setText("Account already exists.");
                            GridPane.setConstraints(failedLogin, 2, 1);
                        }
                    }
                }
                if (!accountExist) {
                    Account newAcc = null;
                    try {
                        newAcc = createAccount(username, password, isSup, accountList.size() + 1);
                    } catch (InterruptedException | IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    accountList.add(newAcc);
                    switchToLogin();
                }
            }

            creategrid.getChildren().removeAll(failedLogin);
            creategrid.getChildren().add(failedLogin);
        });

        Scene createAcc = new Scene(new BorderPane(creategrid, topBar, null, null, null), 550, 400);

        return createAcc;
    }

    
    private void switchTocreateAcc() {
    	createAcc = createCreate();
        primaryStage.setScene(createAcc);
    }

    private void switchToLogin() {
    	loginAttempts = 0;
    	loginApp = createLogin();
        primaryStage.setScene(loginApp);
        
    }
    private void switchToLogin1() {
    	loginAttempts = 0;
    	loginApp = createLogin();
        primaryStage.setScene(loginApp);
        clearConsoleOutputFile("consoleOutput.txt");
        clearConsoleOutputFile("consoleOutput5.txt");
        rowData.clear();
        System.out.println(rowData);
    }
    private void switchToEffortLogger() {
        logOut = effortLogger(primaryStage); // Pass the primaryStage or another Stage reference
        primaryStage.setScene(logOut);
    }

    
    private boolean passwordCheck(String User, String Pass) {
        if (accountList.size() == 0) {
            return false;
        }
        for (int i = 0; i < accountList.size(); i++) {
            Account account = accountList.get(i);
            if (account.Username.equals(User) && account.Password.equals(Pass)) {
                clearConsoleOutputFile("consoleOutput5.txt");
                String scramble = (scrambler(User) + "," + scrambler(Pass));
                try {
                    List<String> lines = Files.readAllLines(Paths.get("consoleOutput4.txt"));
                    for (String line : lines) {
                        if (line.contains(scramble)) {
//                            System.out.println("Content from consoleOutput5.txt is present in consoleOutput4.txt");
//                            System.out.println("Matching line: " + line);

                            // Append the matching line to consoleOutput5.txt
                            Files.write(Paths.get("consoleOutput5.txt"), Collections.singletonList(line), StandardOpenOption.APPEND);

                            break;  // Assuming you want to stop after the first match, remove if not necessary
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return true;
            }
        }
        return false;
    }
   
    private Account createAccount(String user, String pass, boolean isSup, int ID) throws InterruptedException, IOException {
		Account account = new Account();
		account.isSup = isSup;
		account.Password = pass;
		account.Username = user;
		account.accountID = ID;
		saveAccountToFile(account);
		return account;
	}
    
    private boolean usernameFormat(String user) {
    	boolean ret = false;
    	if(user.length() >= 3) {
    		ret = true;
    		return ret;
    	}
    	else {
    		return ret;
    	}
    }
    
    private int passwordFormat(String pass) {
    	        
    	        String specialCharsPattern = "[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]+";
    	        String uppercasePattern = "[A-Z]+";

    	        // Check for special characters
    	        if (!Pattern.compile(specialCharsPattern).matcher(pass).find()) {
    	            System.out.println("Password must contain at least one special character.");
    	            return 1;
    	        }

    	        // Check for uppercase letters
    	        if (!Pattern.compile(uppercasePattern).matcher(pass).find()) {
    	            System.out.println("Password must contain at least one uppercase letter.");
    	            return 2;
    	        }
    	        
    	        if (pass.length() < 8) {
    	        	return 3;
    	        }
    	        return 0; // Password meets the constraints
    }
    
    private void saveAccountToFile(Account account) throws InterruptedException, IOException {
    	String filePath = "consoleOutput4.txt";
        String filePath2 = "consoleOutput3.txt";
    	clearConsoleOutputFile(filePath2);
    	try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath2, true))) {
    		writer.write(
    				scrambler(Integer.toString(account.accountID)) 
    				+ "," + scrambler(account.Username) 
    				+ "," + scrambler(account.Password) + "," 
    				+ scrambler(Boolean.toString(account.isSup)));
    		writer.newLine();
    		
   
    	}
    	catch (IOException e ) {
    		e.printStackTrace();
    	}
    	System.out.println(hasContentInFile(filePath2));

        // Clear the consoleOutput.txt file
        
        
        String nodeJsPath = "/usr/local/bin/node";
        String nodeScriptPath = "/Users/equationiq/workspacenew/ASUHelloWorldJavaFX/interfacer2account.js";

        // Command to run Node.js script
        String command = nodeJsPath + " " + nodeScriptPath;

        // Clear the consoleOutput.txt file

        // Start the Node.js process with the specified working directory
        ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
        processBuilder.directory(new File(filePath).getParentFile());

        // Redirect the output to consoleOutput.txt
        processBuilder.redirectOutput(new File(filePath));

        // Start the Node.js process
        Process process = processBuilder.start();

        // Continuously check the consoleOutput.txt file
        while (true) {
        	//System.out.println(hasContentInFile(filePath2));
        	
            if (hasContentInFile(filePath2) != true) {
            	//System.out.println("none1");
                break;
            }
            
            // Add a delay to avoid constant checking and reduce CPU usage
            Thread.sleep(1000); // 1 second delay
        }

        // Stop the Node.js process
        process.destroy();
        System.out.println("Done");
    }
    
    private void loadAccountsFromFile() throws IOException, InterruptedException {
    	String filePath = "consoleOutput4.txt";
    	String nodeJsPath = "/usr/local/bin/node";
        String nodeScriptPath = "/Users/equationiq/workspacenew/ASUHelloWorldJavaFX/interfaceraccount.js";

        // Command to run Node.js script
        String command = nodeJsPath + " " + nodeScriptPath;

        // Clear the consoleOutput.txt file
        clearConsoleOutputFile(filePath);

        // Start the Node.js process with the specified working directory
        ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
        processBuilder.directory(new File(filePath).getParentFile());

        // Redirect the output to consoleOutput.txt
        processBuilder.redirectOutput(new File(filePath));

        // Start the Node.js process
        Process process = processBuilder.start();
        System.out.println("none");
        // Continuously check the consoleOutput.txt file
        while (true) {
            if (hasContentInFile(filePath)) {
            	System.out.println("Done");
                break;
            }
            System.out.println("none1");
            // Add a delay to avoid constant checking and reduce CPU usage
            Thread.sleep(1000); // 1 second delay
        }

        // Stop the Node.js process
        process.destroy();
    	
    	
    	
    	
    	try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
    		String line;
    		while((line = reader.readLine()) != null) {
    			String[] parts = line.split(",");
    			if (parts.length == 4) {
    				Account account = new Account();
    				account.accountID = Integer.parseInt(unscrambler(parts[0]));
    				account.Username = unscrambler(parts[1]);
    				account.Password = unscrambler(parts[2]);
    				account.isSup = Boolean.parseBoolean(unscrambler(parts[3]));
    				accountList.add(account);
    			}
    		}
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    private String scrambler(String input) {
    	String key = "fasikbasioiafsafhi";
    	byte[] inputByte = input.getBytes(StandardCharsets.UTF_8);
    	byte[] keyByte = key.getBytes(StandardCharsets.UTF_8);
    	
    	byte[] encryptByte = new byte[inputByte.length];
    	for(int i = 0; i < inputByte.length; i++) {
    		encryptByte[i] = (byte) (inputByte[i] ^ keyByte[i%inputByte.length]);
    	}
    	String s = Base64.getEncoder().encodeToString(encryptByte);
    	return s;
    }
    
    private String unscrambler(String input) {
    	String key = "fasikbasioiafsafhi";
    	byte[] inputByte = Base64.getDecoder().decode(input);
    	byte[] keyByte = key.getBytes(StandardCharsets.UTF_8);
    	
    	byte[] decryptByte = new byte[inputByte.length];
    	for(int i = 0; i < inputByte.length; i++) {
    		decryptByte[i] = (byte) (inputByte[i] ^ keyByte[i%inputByte.length]);
    	}
    	String s = new String(decryptByte, StandardCharsets.UTF_8);
    	return s;
    }
    private void handleTimerButton(Button timerButton) {
    	//When Activated
    	 if (timerStatus.get().equals("Not Started")) {
    	        startTime = System.currentTimeMillis();
    	        timerStatus.set("Started");
    	        timerButton.setText("Stop");
    	        clockTimeline.play();  // Start the clock timeline
    	    } else {
        	
        //When Deactivated
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            // Display additional information
            displayAdditionalInfo(startTime, endTime, elapsedTime);

            // Reset the timer and change button
            timerStatus.set("Not Started");
            timerButton.setText("Start");
        }
    	 
    }
    
    //Display time values and set time variables
    private void displayAdditionalInfo(long startTime, long endTime, long elapsedTime) {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Display Date
        Date d = new Date(startTime);
        
        dateVal = dateformat.format(d);
        dateLabel.setText("Date: " + dateVal);
        
        // Display starting time
        sTime = timeformat.format(new Date(startTime));
        startTimeLabel.setText("Starting Time: " + sTime);
        
        // Display ending time
        eTime = timeformat.format(new Date(endTime));
        endTimeLabel.setText("Ending Time: " + eTime);

        
        // Display total time - Broken and curently commented out.
        long minutes = (elapsedTime / 1000) / 60;
        long seconds = (elapsedTime / 1000) % 60;
        
        //totalTimeLabel.setText("Elapsed Time: " + minutes + ":" + seconds);
        tTime = (String.format("%02d:%02d", minutes, seconds));
        totalTimeLabel.setText("Total Time: " + tTime);
        
    }
    
    //Ben's Database Functions will go here. 
    //Adds Values to the database
    private void handleSubmitButton(ComboBox<String> dropdown1, ComboBox<String> dropdown2, ComboBox<String> dropdown3, ComboBox<String> dropdown4,String Name) {
        //Get variables to be added to database
    	String drop1 = dropdown1.getValue();
        String drop2 = dropdown2.getValue();
        String drop3 = dropdown3.getValue();
        String drop4 = dropdown4.getValue();
        
        //Check that all fields are filled. If not, display error and do not log.
        if (drop1 == null || drop2 == null || drop3 == null || drop4 == null) {
        	submitLabel.setText("Submition Failed - Empty Field");
        	return;
        }
        
        //Check that time has been recorded 
        if (dateVal == null || sTime == null || eTime == null || tTime == null) {
        	submitLabel.setText("Submition Failed - No Recorded Time");
        	return;
        }
        
        //Put code to add data to database here.
        //Based on the order in the database, it should be:
        //		dateVal, sTime, eTime, tTime, drop1, drop2, drop3, drop4
        //
        //
        //
        
        String planning = String.valueOf(drop3);
        String newDate = String.valueOf(dateVal);
        String bcd = String.valueOf(drop4);
        String newLifecycle = String.valueOf(drop2);
        String newStart = String.valueOf(sTime);
        String newStop = String.valueOf(eTime);
        //double doubleValue = Double.parseDouble(tTime);
        System.out.println(planning);
        System.out.println(newDate);
        System.out.println(bcd);
        System.out.println(newLifecycle);
        System.out.println(newStart);
        System.out.println(newStop);
        System.out.println(tTime);
        try {
            System.out.println(savedata(drop3, dateVal, drop4, tTime, drop2, sTime, eTime,Name));
            // Rest of the main method remains unchanged...
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        int i = 0;
        if(i == 1) {
        	submitLabel.setText("Error Logging data in database");
        	return;
        }
        
        //Placeholder database index variable.
        int row = 3;
        
        //Confirm submition and location
        submitLabel.setText("Submition Logged with index " + row);
        
        
    }

    //Format time for display
    public static String formatTime(long millis) {
    	System.out.println(millis);
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
    
    		
    private void getItemsForLifeCycleStep(String selectedOption, ComboBox<String> dropdown2) {
        if (selectedOption == "Business Project") {
        	dropdown2.setItems(FXCollections.observableArrayList("Planing", "Information Gathering", "Information Understanding", "Verifying", 
        			"Outlining", "Drafting", "Finalizing", "Team Meeting", "Coach Meeting", "Stakeholder Meeting"));
        	
        } else if (selectedOption == "Development Project") {
        	dropdown2.setItems(FXCollections.observableArrayList("Problem Understanding", "Conceptual Design Plan", "Requirements", "Conceptual Design",
        		    "Conceptual Design Review", "Detailed Design Plan", "Detailed Design/Prototype", "Detailed Design Review", "Implementation Plan",
        		    "Test Case Generation", "Solution Specification", "Solution Review", "Solution Implementation", "Unit/System Test",
        		    "Reflection", "Repository Update"));
        }
    }
    private void getItemsForLifeCycleStep2(String selectedOption, ComboBox<CheckBox> dropdown2) {
        if (selectedOption == "Business Project") {
        	
        	dropdown2.setItems(FXCollections.observableArrayList(createCheckBox("Planing"), createCheckBox("Information Gathering"), createCheckBox("Information Understanding"), createCheckBox("Verifying"), 
        			createCheckBox("Outlining"), createCheckBox("Drafting"), createCheckBox("Finalizing"), createCheckBox("Team Meeting"),createCheckBox("Coach Meeting"), createCheckBox("Stakeholder Meeting")));
        	
        } else if (selectedOption == "Development Project") {
        	dropdown2.setItems(FXCollections.observableArrayList(createCheckBox("Problem Understanding"), createCheckBox("Conceptual Design Plan"), createCheckBox("Requirements"), createCheckBox("Conceptual Design"),
        			createCheckBox("Conceptual Design Review"), createCheckBox("Detailed Design Plan"), createCheckBox("Detailed Design/Prototype"), createCheckBox("Detailed Design Review"), createCheckBox("Implementation Plan"),
        		    		createCheckBox("Test Case Generation"), createCheckBox("Solution Specification"), createCheckBox("Solution Review"), createCheckBox("Solution Implementation"), createCheckBox("Unit/System Test"),
        		    		createCheckBox("Reflection"), createCheckBox("Repository Update")));
        }
    }
    //Set Drop Down 3 - Effort Category
    private void getItemsForEffortCategory(String selectedOption) {
        
    }
    
    //Set Drop Down 4 - This Category changes based on the others 
    private void getItemsForDropdown4(String selectedOption) {
        
        
    }
    
    public static String savedata(String newCategory, String newDate, String newDeliverable,
    		String newDtime, String newLifecycle, String newStart, String newStop,String Name)
            throws IOException, InterruptedException {
    		String scriptDirectory = "/Users/equationiq/Downloads/";
            String consoleOutputFilePath = "consoleOutput.txt";
            String filePath = "consoleOutput2.txt";
            String filePath2 = "consoleOutput.txt";

            // Clear the consoleOutput.txt file
            clearConsoleOutputFile(consoleOutputFilePath);
            
            writeToFile(consoleOutputFilePath, newCategory);
            writeToFile(consoleOutputFilePath, newDate);
            writeToFile(consoleOutputFilePath, newDeliverable);
            writeToFile(consoleOutputFilePath, newDtime);
            writeToFile(consoleOutputFilePath, newLifecycle);
            writeToFile(consoleOutputFilePath, newStart);
            writeToFile(consoleOutputFilePath, newStop);
            writeToFile(consoleOutputFilePath, Name);
            String nodeJsPath = "/usr/local/bin/node";
            String nodeScriptPath = "/Users/equationiq/workspacenew/ASUHelloWorldJavaFX/interfacer2.js";

            // Command to run Node.js script
            String command = nodeJsPath + " " + nodeScriptPath;

            // Clear the consoleOutput.txt file

            // Start the Node.js process with the specified working directory
            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
            processBuilder.directory(new File(filePath).getParentFile());

            // Redirect the output to consoleOutput.txt
            processBuilder.redirectOutput(new File(filePath));

            // Start the Node.js process
            Process process = processBuilder.start();

            // Continuously check the consoleOutput.txt file
            while (true) {
                if (hasContentInFile(filePath2) != true) {
                    break;
                }
                // Add a delay to avoid constant checking and reduce CPU usage
                Thread.sleep(1000); // 1 second delay
            }

            // Stop the Node.js process
            process.destroy();
            return "done";
           
//            
    	}
    public static String updatedata(String newCategory, String newDate, String newDeliverable,
    		String newDtime, String newLifecycle, String newStart, String newStop,String Name,String id)
            throws IOException, InterruptedException {
    		String scriptDirectory = "/Users/equationiq/Downloads/";
            String consoleOutputFilePath = "consoleOutput.txt";
            String filePath = "consoleOutput2.txt";
            String filePath2 = "consoleOutput.txt";

            // Clear the consoleOutput.txt file
            clearConsoleOutputFile(consoleOutputFilePath);
            
            writeToFile(consoleOutputFilePath, newCategory);
            writeToFile(consoleOutputFilePath, newDate);
            writeToFile(consoleOutputFilePath, newDeliverable);
            writeToFile(consoleOutputFilePath, newDtime);
            writeToFile(consoleOutputFilePath, newLifecycle);
            writeToFile(consoleOutputFilePath, newStart);
            writeToFile(consoleOutputFilePath, newStop);
            writeToFile(consoleOutputFilePath, Name);
            writeToFile(consoleOutputFilePath, id);
            String nodeJsPath = "/usr/local/bin/node";
            String nodeScriptPath = "/Users/equationiq/workspacenew/ASUHelloWorldJavaFX/interfacer3.js";

            // Command to run Node.js script
            String command = nodeJsPath + " " + nodeScriptPath;

            // Clear the consoleOutput.txt file

            // Start the Node.js process with the specified working directory
            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
            processBuilder.directory(new File(filePath).getParentFile());

            // Redirect the output to consoleOutput.txt
            processBuilder.redirectOutput(new File(filePath));

            // Start the Node.js process
            Process process = processBuilder.start();

            // Continuously check the consoleOutput.txt file
            while (true) {
                if (hasContentInFile(filePath2) != true) {
                    break;
                }
                // Add a delay to avoid constant checking and reduce CPU usage
                Thread.sleep(1000); // 1 second delay
            }

            // Stop the Node.js process
            process.destroy();
            return "done";
           
//            
    	}
        public static List<YourDataClass> runNodeProcessAndParseFile(String filePath) throws IOException, InterruptedException {
            // Path to the Node.js program
            String nodeJsPath = "/usr/local/bin/node";
            String nodeScriptPath = "/Users/equationiq/workspacenew/ASUHelloWorldJavaFX/interfacer.js";

            // Command to run Node.js script
            String command = nodeJsPath + " " + nodeScriptPath;

            // Clear the consoleOutput.txt file
            clearConsoleOutputFile(filePath);

            // Start the Node.js process with the specified working directory
            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
            processBuilder.directory(new File(filePath).getParentFile());

            // Redirect the output to consoleOutput.txt
            processBuilder.redirectOutput(new File(filePath));

            // Start the Node.js process
            Process process = processBuilder.start();

            // Continuously check the consoleOutput.txt file
            while (true) {
                if (hasContentInFile(filePath)) {
                    break;
                }

                // Add a delay to avoid constant checking and reduce CPU usage
                Thread.sleep(1000); // 1 second delay
            }

            // Stop the Node.js process
            process.destroy();

            return parseConsoleOutputFile(filePath);
        }
        private static void writeToFile(String filePath, String data) {
            try (FileWriter writer = new FileWriter(filePath, true)) {
                // Append the data followed by a newline character
                writer.write(data + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private static void clearConsoleOutputFile(String filePath) {
            try (FileWriter writer = new FileWriter(filePath, false)) {
                // Write an empty string to clear the file
                writer.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static boolean hasContentInFile(String filePath) throws IOException {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filePath)))) {
                return reader.readLine() != null;
            }
        }

        private static List<YourDataClass> parseConsoleOutputFile(String filePath) {
            List<YourDataClass> dataList = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filePath)))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String category = line;
                    String date = reader.readLine();
                    String deliverable = reader.readLine();
                    String dtime = reader.readLine();
                    String lifecycle = reader.readLine();
                    String start = reader.readLine();
                    String stop = reader.readLine();
                    String user = reader.readLine();
                    String id = reader.readLine();
                    YourDataClass data = new YourDataClass(category, date, deliverable, dtime, lifecycle, start, stop, user, id);
                    dataList.add(data);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return dataList;
        }
    
    }
class CustomButton extends Button {

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 60;

    public CustomButton(String text) {
        super(text);

        setPrefWidth(BUTTON_WIDTH);
        setPrefHeight(BUTTON_HEIGHT);
        setFont(Font.font("PT Sans Caption", FontWeight.BOLD, 27));
        // Customize the appearance of the button
        setStyle(
            "-fx-background-color: #09F8C8;" + // Green background color
            "-fx-text-fill: #384373;" +                 // Font size
            "-fx-background-radius: 17px;"       // Rounded corners
        );

        // Add custom actions or event handlers if needed
        setOnAction(e -> {
            // Add custom logic here
            System.out.println("Custom button clicked!");
        });
    }
}


class CustomButtonvert extends Button {


    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 60;

    public CustomButtonvert(String text) {
        super(text);

        setPrefWidth(BUTTON_WIDTH);
        setPrefHeight(BUTTON_HEIGHT);
        setFont(Font.font("PT Sans Caption", FontWeight.BOLD, 27));
        // Customize the appearance of the button
        setStyle(
            "-fx-background-color: #272F51;" + // Green background color
            "-fx-text-fill: #D0FFF6;" +                 // Font size
            "-fx-background-radius: 17px;"       // Rounded corners
        );

        // Add custom actions or event handlers if needed
        setOnAction(e -> {
            // Add custom logic here
            System.out.println("Custom button clicked!");
        });
    }
}


class CustomButtonlog extends Button {


    private static final int BUTTON_WIDTH = 60;
    private static final int BUTTON_HEIGHT = 20;

    public CustomButtonlog(String text) {
        super(text);

        setPrefWidth(BUTTON_WIDTH);
        setPrefHeight(BUTTON_HEIGHT);
        setFont(Font.font("PT Sans Caption", FontWeight.BOLD, 12));
        // Customize the appearance of the button
        setStyle(
            "-fx-background-color: #272F51;" + // Green background color
            "-fx-text-fill: #D0FFF6;" +                 // Font size
            "-fx-background-radius: 3px;"       // Rounded corners
        );

        // Add custom actions or event handlers if needed
        setOnAction(e -> {
            // Add custom logic here
            System.out.println("Custom button clicked!");
        });
    }
}
class CustomButtonlog2 extends Button {


    private static final int BUTTON_WIDTH = 70;
    private static final int BUTTON_HEIGHT = 20;

    public CustomButtonlog2(String text) {
        super(text);

        setPrefWidth(BUTTON_WIDTH);
        setPrefHeight(BUTTON_HEIGHT);
        setFont(Font.font("PT Sans Caption", FontWeight.BOLD, 12));
        // Customize the appearance of the button
        setStyle(
            "-fx-background-color: #272F51;" + // Green background color
            "-fx-text-fill: #D0FFF6;" +                 // Font size
            "-fx-background-radius: 3px;"       // Rounded corners
        );

        // Add custom actions or event handlers if needed
        setOnAction(e -> {
            // Add custom logic here
            System.out.println("Custom button clicked!");
        });
    }
}
class CustomButtonmain extends Button {


    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 20;

    public CustomButtonmain(String text) {
        super(text);

        setPrefWidth(BUTTON_WIDTH);
        setPrefHeight(BUTTON_HEIGHT);
        setFont(Font.font("PT Sans Caption", FontWeight.BOLD, 17));
        // Customize the appearance of the button
        setStyle(
            "-fx-background-color: transparent;" + // Green background color
            "-fx-text-fill: #D0FFF6;" +                 // Font size
            "-fx-background-radius: 3px;"       // Rounded corners
        );

        // Add custom actions or event handlers if needed
        setOnAction(e -> {
            // Add custom logic here
            System.out.println("Custom button clicked!");
        });
    }
}