package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import model.Customer;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Addcustomerform implements Initializable {

    @FXML
    private JFXTextField address;

    @FXML
    private DatePicker bday;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXComboBox<String> title;

    @FXML
    void btnActionadd(ActionEvent event) {
        String Name=name.getText();
        String Address = address.getText();
        String Phone=phone.getText();
        LocalDate Bday=bday.getValue();
        String Title =title.getValue();

        if (Title==null){
            new Alert(Alert.AlertType.WARNING,"Enter Title ").showAndWait();
            return;
        }
        if (Name==""){
            new Alert(Alert.AlertType.WARNING,"Enter name ").showAndWait();
            return;
        }
        if (Address==""){
            new Alert(Alert.AlertType.WARNING,"Enter Address ").showAndWait();
            return;
        }
        if (Phone==""){
            new Alert(Alert.AlertType.WARNING,"Enter phone number ").showAndWait();
            return;
        }

        List<Customer> connection = DBConnection.getInstance().getDBConnection();
        connection.add(new Customer(id.getText(),Title+"",Name,Address,Phone,Bday));

        new Alert(Alert.AlertType.CONFIRMATION,"Customer added successfully  ").showAndWait();


        id.setText("00"+generate());
        name.setText("");
        address.setText("");
        phone.setText("");
        title.setValue(null);
        bday.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        title.setItems(FXCollections.observableArrayList(new String[]{"Mr.", "Miss", "Mrs."}));
        id.setText("00"+generate());
    }

    public int generate(){
        List<Customer> temp = DBConnection.getInstance().getDBConnection();
        int index = 000;
        if(temp.size()==0){
            index = 000;
        }
        else {
            index = Integer.parseInt(temp.get(temp.size()-1).getId());
        }
        return ++index;
    }
}