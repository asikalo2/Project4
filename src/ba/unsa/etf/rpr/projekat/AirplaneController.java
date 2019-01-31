package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AirplaneController {
    public Label idLabel;
    public TextField idField;
    public Label airlineLabel;
    public TextField airlineField;
    public Label manufacturerLabel;
    public TextField manufacturerField;
    public Label typeLabel;
    public TextField typeField;
    public Label numberOfSeatsLabel;
    public TextField numberOfSeatsField;
    public SimpleStringProperty idProperty;
    public SimpleStringProperty airlineProperty;
    public SimpleStringProperty manufacturerProperty;
    public SimpleStringProperty typeProperty;
    public SimpleStringProperty numberOfSeatsProperty;

    private AirportDAO dao;
    private Airplane currentAirplane = null;

    public AirplaneController(AirportDAO dao, Airplane airplane) {
        this.dao = dao;
        this.currentAirplane = airplane;
        idProperty = new SimpleStringProperty("");
        airlineProperty = new SimpleStringProperty("");
        manufacturerProperty = new SimpleStringProperty("");
        typeProperty = new SimpleStringProperty("");
        numberOfSeatsProperty = new SimpleStringProperty("");
    }

}
