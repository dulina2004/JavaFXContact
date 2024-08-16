package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewCustomerform implements Initializable {

    List<Customer> templist = DBConnection.getInstance().getDBConnection();

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
    private TableView<Customer> resulttable;

    @FXML
    private TableColumn<?, ?> colladdress;

    @FXML
    private TableColumn<?, ?> collcontact;

    @FXML
    private TableColumn<?, ?> colldob;

    @FXML
    private TableColumn<?, ?> collid;

    @FXML
    private TableColumn<?, ?> collname;

    @FXML
    private TableColumn<?, ?> colltitle;

    @FXML
    private JFXTextField search;

    @FXML
    private JFXComboBox<String> title;

    @FXML
    void btnActiondelete(ActionEvent event) {
        int index = check(search.getText());
        templist.remove(index);
        id.setText("");
        name.setText("");
        address.setText("");
        phone.setText("");
        title.setValue(null);
        bday.setValue(null);
        search.setText("");
        view();
    }


    @FXML
    void btnActionsearch(ActionEvent event) {
        int index = check(search.getText());
        id.setText(templist.get(index).getId());
        name.setText(templist.get(index).getName());
        title.setValue(templist.get(index).getTitle());
        address.setText(templist.get(index).getAddress());
        phone.setText(templist.get(index).getPhone());
        bday.setValue(templist.get(index).getBday());
    }

    @FXML
    void btnActionupdate(ActionEvent event) {
        int index = check(search.getText());
        templist.get(index).setName(name.getText());
        templist.get(index).setTitle(title.getValue()+"");
        templist.get(index).setAddress(address.getText());
        templist.get(index).setPhone(phone.getText());
        templist.get(index).setBday(bday.getValue());

        id.setText("");
        name.setText("");
        address.setText("");
        phone.setText("");
        title.setValue(null);
        bday.setValue(null);
        search.setText("");
        view();
    }

    @FXML
    void mouseclicked(MouseEvent event) {
        int index = resulttable.getSelectionModel().getFocusedIndex();
        search.setText(templist.get(index).getId());
        id.setText(templist.get(index).getId());
        name.setText(templist.get(index).getName());
        title.setValue(templist.get(index).getTitle());
        address.setText(templist.get(index).getAddress());
        phone.setText(templist.get(index).getPhone());
        bday.setValue(templist.get(index).getBday());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        title.setItems(FXCollections.observableArrayList(new String[]{"Mr.", "Miss", "Mrs."}));
        id.setDisable(true);
        view();
    }
    @FXML
    void btnRefreshAction(ActionEvent event) {
        view();
    }
    public void view(){

        this.collid.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colltitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        this.collname.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.colladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        this.collcontact.setCellValueFactory(new PropertyValueFactory<>("phone"));
        this.colldob.setCellValueFactory(new PropertyValueFactory<>("bday"));

        List<Customer> customerlist= DBConnection.getInstance().getDBConnection();
        ObservableList<Customer> customerObservableList= FXCollections.observableArrayList();
        customerlist.forEach(obj -> {
            customerObservableList.add(obj);
        });
        this.resulttable.setItems(customerObservableList);
        this.resulttable.refresh();
    }
    public int check(String search) {
        List<Customer> temp = DBConnection.getInstance().getDBConnection();
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getId().equals(search)) {
                return i;
            }
        }
        return -1;
    }
}
