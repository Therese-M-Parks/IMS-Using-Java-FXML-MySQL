/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsystemfxml;

import imsystemclasses.Inventory;
import static imsystemclasses.Inventory.allParts;
import imsystemclasses.Part;
import imsystemclasses.Product;
import static imsystemfxml.MainScreenController.selectedProduct;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Therese Parks
 */
public class ModifyProductController implements Initializable {

    ObservableList<Part> temp = FXCollections.observableArrayList();
    @FXML // id Label
    private Label modifyProductLabel;

    @FXML
    private TextField partSearchField;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField invTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField maxTextField;

    @FXML
    private TextField minTextField;

    @FXML
    private TableView<Part> topTableView2;

    @FXML
    private TableColumn<Part, Integer> topPartID2;

    @FXML
    private TableColumn<Part, String> topPartName2;

    @FXML
    private TableColumn<Part, Integer> topPartInv2;

    @FXML
    private TableColumn<Part, Double> topPartPrice2;

    @FXML
    private TableView<Part> bottomTableView2;

    @FXML
    private TableColumn<Part, Integer> bottomPartID2;

    @FXML
    private TableColumn<Part, String> bottomTableName2;

    @FXML
    private TableColumn<Part, Integer> bottomInv2;

    @FXML
    private TableColumn<Part, Double> bottomPrice2;

    @FXML
    private Button saveModProduct;

    @FXML // controls for cancel button
    void MainScreen(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Are you sure you want to leave the MODIFY PRODUCT screen?");
        alert.setContentText("Please click Okay to confirm, or click Cancel");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // user chose OK
            Stage stage;
            Parent root;
            //get reference to the button's stage         
            stage = (Stage) modifyProductLabel.getScene().getWindow();
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

    @FXML // action event for save button
    void ModifyProduct(ActionEvent event) throws IOException {
        //Pull text from the fields on the Add Screen
        String id = idTextField.getText();
        String inv = invTextField.getText();
        String price = priceTextField.getText();
        String name = nameTextField.getText();
        String max = maxTextField.getText();
        String min = minTextField.getText();
        String message = "";
        boolean valid = true;
        Product newProduct;

        // Create a new Product out of the information pulled from the Add Screen
        try {

            Double.parseDouble(price);
            Integer.parseInt(inv);
            Integer.parseInt(min);
            Integer.parseInt(max);
            newProduct = new Product(0, name, Double.parseDouble(price), Integer.parseInt(inv), Integer.parseInt(min), Integer.parseInt(max));
            newProduct.associatedParts = temp;
        } catch (NumberFormatException E) {
            newProduct = null;
            message += " Product must have a price that is a double precision number, and min and max and inv that are integers.";
            valid = false;
        }
        if (name.equals("")) {
            message += " Product must have a name.";
            valid = false;
        }
        if (valid) {
            double prodPrice = Double.parseDouble(price);
            double priceParts = 0;
            for (Part part : temp) {
                priceParts += part.getPrice().get();
            }
            if (prodPrice < priceParts) {
                message += " Price of Products must be greater than price of Parts.";
                valid = false;
            }

            // Do not allow the addition unless certain conditions are met
            if ((Integer.parseInt(inv) >= Integer.parseInt(max) || Integer.parseInt(inv) <= Integer.parseInt(min)) // if inventory is breaking rules
                    || (Integer.parseInt(max) <= Integer.parseInt(min) || Integer.parseInt(min) >= Integer.parseInt(max))) // if min and max are breaking rules
            {
                message += " 1. Inventory value must be between the minimum or maximum values."
                        + " 2. The maximum value must be greater than the minimum value.";
                //Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                valid = false;
            }
            // make sure Product has at least one part
            if (temp.isEmpty()) {
                message += " Please add at least one part to the product before saving";
                //   Alert alert2 = new Alert(Alert.AlertType.WARNING);
                valid = false;
            }
        }
        if (valid == true) {
            Inventory.updateProduct(newProduct);
            Inventory.removeProduct(selectedProduct);
            // public Product(int id, String prodName, double prodPrice, int prodInStock, int prodMin, int prodMax) {        
            Stage stage;
            Parent root;
            //get reference to the button's stage         
            stage = (Stage) modifyProductLabel.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            //create a new scene with root and set the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            //  valid = true;
        } else {
            {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Problem with Data");
                alert2.setHeaderText(" Please fix data before saving");
                alert2.setContentText(message);
                alert2.showAndWait();
                valid = true;
            }
        }
    }

    @FXML
    void lookupPart(ActionEvent event
    ) {
        String s = partSearchField.getText();

        try {
            int id = Integer.parseInt(s);
            for (int i = 0; i < allParts.size(); i++) {
                if (id == allParts.get(i).getPartID().get()) {
                    topTableView2.getSelectionModel().select(allParts.get(i));
                }
            }
        } catch (NumberFormatException E) {
            for (int i = 0; i < allParts.size(); i++) {
                if (s.equalsIgnoreCase(allParts.get(i).getName())) {
                    topTableView2.getSelectionModel().select(allParts.get(i));
                }
            }
        }

    }

    @FXML // controls for add button
    void AddAssociatedPart2(ActionEvent event) throws IOException {
        //Pull text from the fields on the Add Screen
        String id = idTextField.getText();
        String inv = invTextField.getText();
        String price = priceTextField.getText();
        String name = nameTextField.getText();
        String max = maxTextField.getText();
        String min = minTextField.getText();
        // Create a new Product out of the information pulled from the Add Screen
        Product newProduct = new Product(0, name, Double.parseDouble(price), Integer.parseInt(inv), Integer.parseInt(min), Integer.parseInt(max));
        Part part = topTableView2.getSelectionModel().getSelectedItem();
        temp.add(part);
//        Inventory.updateProduct(newProduct);
//        Stage stage;
//        Parent root;
//        //get reference to the button's stage         
//        stage = (Stage) modifyProductLabel.getScene().getWindow();
//        //load up OTHER FXML document
//        root = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
//        //create a new scene with root and set the stage
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show(); 

    }

    @FXML // controls for delete button
    void removeAssociatedPart(ActionEvent event
    ) {
        Part part = bottomTableView2.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Are you sure you want to remove this Part from this Product?");
        alert.setContentText("Please click Okay to confirm, or click Cancel");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // user chose OK
            temp.remove(part);
        } else {
            // user chose CANCEL or closed the dialog
            alert.close();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        idTextField.setText(new Integer(MainScreenController.selectedProduct.getProductID().get()).toString());
        invTextField.setText(new Integer(MainScreenController.selectedProduct.getInStock().get()).toString());
        priceTextField.setText(new Double(MainScreenController.selectedProduct.getPrice().get()).toString());
        nameTextField.setText(new String(MainScreenController.selectedProduct.getName()));
        maxTextField.setText(new Integer(MainScreenController.selectedProduct.getMax().get()).toString());
        minTextField.setText(new Integer(MainScreenController.selectedProduct.getMin().get()).toString());

        topPartID2.setCellValueFactory(cellData
                -> cellData.getValue().getPartID().asObject());

        topPartName2.setCellValueFactory(cellData
                -> cellData.getValue().nameProperty());

        topPartInv2.setCellValueFactory(cellData
                -> cellData.getValue().getInStock().asObject());

        topPartPrice2.setCellValueFactory(cellData
                -> cellData.getValue().getPrice().asObject());

        topTableView2.setItems(Inventory.allParts);

//        
//        
//        
//        
        //==========Bottom Table View==========// 
        bottomPartID2.setCellValueFactory(cellData
                -> cellData.getValue().getPartID().asObject());

        bottomTableName2.setCellValueFactory(cellData
                -> cellData.getValue().nameProperty());

        bottomInv2.setCellValueFactory(cellData
                -> cellData.getValue().getInStock().asObject());

        bottomPrice2.setCellValueFactory(cellData
                -> cellData.getValue().getPrice().asObject());

        bottomTableView2.setItems(MainScreenController.selectedProduct.associatedParts);
        temp = MainScreenController.selectedProduct.associatedParts;
    }

}
