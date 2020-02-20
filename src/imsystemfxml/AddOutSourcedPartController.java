/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsystemfxml;

import imsystemclasses.InHousePart;
import imsystemclasses.Inventory;
import imsystemclasses.OutSourcedPart;
import imsystemclasses.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Therese Parks
 */
public class AddOutSourcedPartController implements Initializable {

    @FXML
    private Label addPartsLabelOS; // ID label

    @FXML
    private ToggleGroup partOutToggles;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField invTextField;

    @FXML
    private TextField maxTextField;

    @FXML
    private TextField minTextField;

    @FXML
    private TextField coNameField;

    @FXML
    private Button saveOutPartButton;

    @FXML
    private TextField priceTextField;

    @FXML //controls for inHouse label radio button
    void AddInHousePart(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        //get reference to the button's stage         
        stage = (Stage) addPartsLabelOS.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("AddInHousePart.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void addOutSourcedPart(ActionEvent event) throws IOException {
        String name = nameTextField.getText();
        String inv = invTextField.getText();

        String max = maxTextField.getText();
        String min = minTextField.getText();
        String compName = coNameField.getText();
        String price = priceTextField.getText();
        Part newPart = new OutSourcedPart(0, name, Double.parseDouble(price), Integer.parseInt(inv), Integer.parseInt(min), Integer.parseInt(max), compName);
        // Do not allow the addition unless certain conditions are met
        String message = "";
        boolean valid = true;
        // Add the new Part to the list

        if ((Integer.parseInt(inv) >= Integer.parseInt(max) || Integer.parseInt(inv) <= Integer.parseInt(min)) // if inventory is breaking rules
                || (Integer.parseInt(max) <= Integer.parseInt(min) || Integer.parseInt(min) >= Integer.parseInt(max))) // if min and max are breaking rules
        {
            message += " 1. Inventory value must be between the minimum or maximum values."
                    + " 2. The maximum value must be greater than the minimum value.";
            valid = false;
        }
        if (valid == true) {
            Inventory.addPart(newPart);
            //  public InHousePart(int id, String partName, double partPrice, int partInStock, int partMin, int partMax, int macID) {
            Stage stage;
            Parent root;
            //get reference to the button's stage         
            stage = (Stage) addPartsLabelOS.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            //create a new scene with root and set the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();

        } else {

            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Problem with Data");
            alert2.setHeaderText(" Please fix data before saving");
            alert2.setContentText(message);
            alert2.showAndWait();
            valid = true;
        }
    }

    @FXML // controls for cancel button
    void MainScreen(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Are you sure you want to leave the ADD PART screen?");
        alert.setContentText("Please click Okay to confirm, or click Cancel");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // user chose OK
            Stage stage;
            Parent root;
            //get reference to the button's stage         
            stage = (Stage) addPartsLabelOS.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            //create a new scene with root and set the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            // user chose CANCEL or closed the dialog
            alert.close();
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
