/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsystemfxml;

import imsystemclasses.Inventory;
import static imsystemclasses.Inventory.allParts;
import static imsystemclasses.Inventory.products;
import imsystemclasses.Part;
import imsystemclasses.Product;
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
public class AddProductController implements Initializable {

    ObservableList<Part> temp = FXCollections.observableArrayList();

    @FXML // id label
    private Label addProductLabel;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField invField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField maxField;

    @FXML
    private TextField minField;

    @FXML
    private Button partSearchButton;

    @FXML
    private TextField partSearchField;

    @FXML
    private Button saveProdButton;

    //=========================================
    @FXML
    private TableView<Part> topTableView;

    @FXML
    private TableColumn<Part, Integer> topPartID;

    @FXML
    private TableColumn<Part, String> topPartName;

    @FXML
    private TableColumn<Part, Integer> topPartInv;

    @FXML
    private TableColumn<Part, Double> topPartPrice;

    @FXML
    private TableView<Part> bottomTableView;

    @FXML
    private TableColumn<Part, Integer> bottomPartID;

    @FXML
    private TableColumn<Part, String> bottomTableName;

    @FXML
    private TableColumn<Part, Integer> bottomInv;

    @FXML
    private TableColumn<Part, Double> bottomPrice;

    @FXML // controls for Cancel Button
    void MainScreen(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Are you sure you want to leave the ADD PRODUCT screen?");
        alert.setContentText("Please click Okay to confirm, or click Cancel");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // user chose OK
            Stage stage;
            Parent root;
            //get reference to the button's stage         
            stage = (Stage) addProductLabel.getScene().getWindow();
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

    @FXML
    void saveProduct(ActionEvent event) throws IOException {

    }

    @FXML // controls for save button
    void AddProduct(ActionEvent event) throws IOException {
        String id = idField.getText();
        String name = nameField.getText();
        String inv = invField.getText();
        String min = minField.getText();
        String max = maxField.getText();
        String price = priceField.getText();
        String message = "";
        boolean valid = true;
        Product newProduct;
        try {

            Double.parseDouble(price);
            Integer.parseInt(inv);
            Integer.parseInt(min);
            Integer.parseInt(max);
            newProduct = new Product(0, name, Double.parseDouble(price), 
                    Integer.parseInt(inv), Integer.parseInt(min), Integer.parseInt(max));
            newProduct.associatedParts = temp;
        } catch (NumberFormatException E) {
            newProduct = null;
            message += " Product must have a price that is a double "
                    + "precision number, and min and max and inv that are integers.";
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
                valid = false;
            }
            // make sure Product has at least one part
            if (temp.isEmpty()) {
                message += " Please add at least one part to the product before saving";
                valid = false;
            }
        }
        if (valid == true) {
            Inventory.addProduct(newProduct);
            // public Product(int id, String prodName, double prodPrice, int prodInStock, int prodMin, int prodMax) {        
            Stage stage;
            Parent root;
            //get reference to the button's stage         
            stage = (Stage) addProductLabel.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            //create a new scene with root and set the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            //  valid = true;

            // ensure that price of product cannot be less than cost of parts.
//            String tempPrice = bottomPrice.getText();
//            double partPrice = Double.parseDouble(tempPrice);
//            double prodPrice = Double.parseDouble(price);
//            if (partPrice > prodPrice) {
//                message += " Price of Product must be greater than Price of Part";
//                valid = false;
//            }
        } else {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Problem with Data");
            alert2.setHeaderText(" Please fix data before saving");
            alert2.setContentText(message);
            alert2.showAndWait();
            valid = true;
        }

    }

    @FXML //controls for search button
    void lookupPart(ActionEvent event) {
        String s = partSearchField.getText();

        try {
            int id = Integer.parseInt(s);
            for (int i = 0; i < allParts.size(); i++) {
                if (id == allParts.get(i).getPartID().get()) {
                    topTableView.getSelectionModel().select(allParts.get(i));
                }
            }
        } catch (NumberFormatException E) {
            for (int i = 0; i < allParts.size(); i++) {
                if (s.equalsIgnoreCase(allParts.get(i).getName())) {
                    topTableView.getSelectionModel().select(allParts.get(i));
                }
            }
        }

    }

    @FXML // Controls for add button on Add Product Screen
    void AddAssociatedPart(ActionEvent event) throws IOException {
        Part part = topTableView.getSelectionModel().getSelectedItem();
        temp.add(part);
    }

    @FXML // controls for delete button
    void removeAssociatedPart(ActionEvent event) {
        Part part = bottomTableView.getSelectionModel().getSelectedItem();
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
    public void initialize(URL url, ResourceBundle rb) {
        //==============add product screen to keep fields populated after adding associated parts

//           idField.setText(new Integer(MainScreenController.selectedProduct.getProductID().get()).toString()); 
//           invField.setText(new Integer(MainScreenController.selectedProduct.getInStock().get()).toString());  
//           priceField.setText(new Double(MainScreenController.selectedProduct.getPrice().get()).toString());
//           nameField.setText(new String(MainScreenController.selectedProduct.getName()));
//           maxField.setText(new Integer(MainScreenController.selectedProduct.getMax().get()).toString());
//           minField.setText(new Integer(MainScreenController.selectedProduct.getMin().get()).toString());
        //==========Top Table View==========// 
        topPartID.setCellValueFactory(cellData
                -> cellData.getValue().getPartID().asObject());

        topPartName.setCellValueFactory(cellData
                -> cellData.getValue().nameProperty());

        topPartInv.setCellValueFactory(cellData
                -> cellData.getValue().getInStock().asObject());

        topPartPrice.setCellValueFactory(cellData
                -> cellData.getValue().getPrice().asObject());

        topTableView.setItems(Inventory.allParts);

        //==========Bottom Table View==========// 
        bottomPartID.setCellValueFactory(cellData
                -> cellData.getValue().getPartID().asObject());

        bottomTableName.setCellValueFactory(cellData
                -> cellData.getValue().nameProperty());

        bottomInv.setCellValueFactory(cellData
                -> cellData.getValue().getInStock().asObject());

        bottomPrice.setCellValueFactory(cellData
                -> cellData.getValue().getPrice().asObject());

        bottomTableView.setItems(temp);
    }

}
