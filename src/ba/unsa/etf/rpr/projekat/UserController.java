package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserController {
    public Label idLabel;
    public TextField idField;
    public Label nameLabel;
    public TextField nameField;
    public Label roleLabel;
    public SimpleStringProperty idProperty;
    public SimpleStringProperty nameProperty;
    public SimpleObjectProperty<Role> roleProperty;
    public ComboBox role;

    private AirportDAO dao;
    private User currentUser = null;

    @FXML
    public Button cancelButton;
    @FXML
    public Button okButton;

    public UserController(AirportDAO dao, User user) {
        this.dao = dao;
        this.currentUser = user;
        idProperty = new SimpleStringProperty("");
        nameProperty = new SimpleStringProperty("");
        roleProperty = new SimpleObjectProperty<>();
    }


    @FXML
    public void initialize() {
        role.setItems(dao.getRoles());
        initializeDataBinding();

        role.setConverter(new StringConverter<Role>() {
            @Override
            public String toString(Role role) {
                return role.getName();
            }

            @Override
            public Role fromString(String string) {
                Role newRole = new Role(0, string);
                return newRole;
            }
        });

        if (currentUser != null) {
            fillForm();
        }
    }

    private void fillForm() {
        idProperty.set(String.valueOf(currentUser.getId()));
        nameProperty.set(currentUser.getName());
        roleProperty.set(currentUser.getRole());
    }

    private void initializeDataBinding() {
        idField.textProperty().bindBidirectional(idProperty);
        nameField.textProperty().bindBidirectional(nameProperty);
        role.valueProperty().bindBidirectional(roleProperty);
    }

    public void cancelFormBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void okFormBtn(ActionEvent actionEvent) {
        if (isFormValid()) {
            boolean adding = currentUser == null;

            if (currentUser == null)
                currentUser = new User();

            currentUser.setId(Integer.valueOf((idProperty.get())));
            currentUser.setName(nameProperty.get());
            currentUser.setRole(roleProperty.get());


            if (adding) {
                dao.addUser(currentUser);
            } else {
                dao.changeUser(currentUser);
            }
            Stage stage = (Stage) okButton.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
    }

    private boolean isFormValid() {
    return true;
    }

}
