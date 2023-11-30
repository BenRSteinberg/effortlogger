package asuHelloWorldJavaFX;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EffortData {
    private final StringProperty number;
    private final StringProperty date;
    private final StringProperty lifeCycleStep;
    private final StringProperty effortCategory;
    private final StringProperty deliverable;
    private final StringProperty startTime;
    private final StringProperty stopTime;
    private final StringProperty deltaTime;
    private final StringProperty user;
    private final StringProperty id;
    //Formats data into simpleStrings to work with table.
    public EffortData(String number, String date, String startTime, String stopTime, String deltaTime, String lifeCycleStep, String effortCategory, String deliverable, String user,String id) {
        this.number = new SimpleStringProperty(number);
        this.date = new SimpleStringProperty(date);
        this.lifeCycleStep = new SimpleStringProperty(lifeCycleStep);
        this.effortCategory = new SimpleStringProperty(effortCategory);
        this.deliverable = new SimpleStringProperty(deliverable);
        this.startTime = new SimpleStringProperty(startTime);
        this.stopTime = new SimpleStringProperty(stopTime);
        this.deltaTime = new SimpleStringProperty(deltaTime);
        this.user = new SimpleStringProperty(user);
        this.id = new SimpleStringProperty(id);
    }

    // Getters for values - keep in mind these are not strings. 
    	//Once database is set up, any instances of these not in the table itself should pull from the database directly

    public StringProperty numberProperty() {
        return number;
    }

    public StringProperty dateProperty() {
        return date;
    }

    public StringProperty lifeCycleStepProperty() {
        return lifeCycleStep;
    }

    public StringProperty effortCategoryProperty() {
        return effortCategory;
    }

    public StringProperty deliverableProperty() {
        return deliverable;
    }

    public StringProperty startTimeProperty() {
        return startTime;
    }

    public StringProperty stopTimeProperty() {
        return stopTime;
    }

    public StringProperty deltaTimeProperty() {
        return deltaTime;
    }
    public StringProperty userProperty() {
        return user;
    }
    public StringProperty idProperty() {
        return id;
    }
}
