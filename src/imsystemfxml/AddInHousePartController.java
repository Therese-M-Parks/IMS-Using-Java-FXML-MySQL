/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsystemfxml;

import imsystemclasses.InHousePart;
import imsystemclasses.Inventory;
import imsystemclasses.Part;
import imsystemclasses.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import static javafx.beans.binding.Bindings.max;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Therese Parks
 */
public class AddInHousePartController implements Initializable {

    // ids for inHousePart
    @FXML
    private Label addPartLabel;

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
    private TextField macIDtextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private Button saveInHousePart;

    @FXML
    void AddInHousePart(ActionEvent event) throws IOException {
        //Pull text from the fields on the Add Screen
        String name = nameTextField.getText();
        String inv = invTextField.getText();
        String max = maxTextField.getText();
        String min = minTextField.getText();
        String macID = macIDtextField.getText();
        String price = priceTextField.getText();
        // Create a new Part out of the information pulled from the Add Screen
        Part newPart = new InHousePart(0, name, Double.parseDouble(price), Integer.parseInt(inv), Integer.parseInt(min), 
                Integer.parseInt(max), Integer.parseInt(macID));
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
            stage = (Stage) addPartLabel.getScene().getWindow();
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

    @FXML
    void AddOutSourcedPart(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        //get reference to the button's stage         
        stage = (Stage) addPartLabel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("AddOutSourcedPart.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML // Controls for cancel button
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
            stage = (Stage) addPartLabel.getScene().getWindow();
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
