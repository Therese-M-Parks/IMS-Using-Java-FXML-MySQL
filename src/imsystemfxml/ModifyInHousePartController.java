/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsystemfxml;

import imsystemclasses.InHousePart;
import imsystemclasses.Inventory;
import imsystemclasses.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
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
public class ModifyInHousePartController extends MainScreenController implements Initializable {

    @FXML
    private Label modifyPartLabel;

    @FXML
    private ToggleGroup modInPart;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField invField;

    @FXML
    private TextField maxField;

    @FXML
    private TextField minField;

    @FXML
    private TextField macIDField;

    @FXML
    private TextField priceField;

    @FXML
    private Button modSavePart; // TODO: button needs to have controls that lead save and lead to main screen

    @FXML //controls for inhouse label radio button
    void ModifyInHousePart(ActionEvent event) throws IOException {

        //Pull text from the fields on the Add Screen
        String name = nameField.getText();
        String inv = invField.getText();
        String max = maxField.getText();
        String min = minField.getText();
        String macID = macIDField.getText();
        String price = priceField.getText();
        // Create a new Part out of the information pulled from the Add Screen
        Part newPart = new InHousePart(0, name, Double.parseDouble(price), Integer.parseInt(inv), Integer.parseInt(min), Integer.parseInt(max), Integer.parseInt(macID));
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
            Inventory.updatePart(newPart);
            Inventory.deletePart(selectedPart);
            //  public InHousePart(int id, String partName, double partPrice, int partInStock, int partMin, int partMax, int macID) {
            Stage stage;
            Parent root;
            //get reference to the button's stage         
            stage = (Stage) modifyPartLabel.getScene().getWindow();
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

    @FXML // controls for OutSourced label radion button
    void ModifyOutSourcedPart(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        //get reference to the button's stage         
        stage = (Stage) modifyPartLabel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("ModifyOutSourcedPart.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML //controls for cancel button
    void MainScreen(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Are you sure you want to leave the MODIFY PART screen?");
        alert.setContentText("Please click Okay to confirm, or click Cancel");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // user chose OK
            Stage stage;
            Parent root;
            //get reference to the button's stage         
            stage = (Stage) modifyPartLabel.getScene().getWindow();
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
        //  partTable.setItems(Inventory.allParts);
        idField.setText(new Integer(MainScreenController.selectedPart.getPartID().get()).toString());
        nameField.setText(new String(MainScreenController.selectedPart.getName()));
        invField.setText(new Integer(MainScreenController.selectedPart.getInStock().get()).toString());
        priceField.setText(new Double(MainScreenController.selectedPart.getPrice().get()).toString());
        maxField.setText(new Integer(MainScreenController.selectedPart.getMax().get()).toString());
        minField.setText(new Integer(MainScreenController.selectedPart.getMin().get()).toString());
        macIDField.setText(new Integer(((InHousePart) MainScreenController.selectedPart).getMachineID().get()).toString());
    }

}
