/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsystemfxml;

import imsystemclasses.InHousePart;
import imsystemclasses.Product;
import imsystemclasses.Inventory;
import static imsystemclasses.Inventory.allParts;
import static imsystemclasses.Inventory.products;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Therese Parks
 */
public class MainScreenController implements Initializable {

    @FXML // id label
    private Label partsLabel;

    @FXML // id for close button
    private Button closeButton;

    @FXML
    private TextField partSearchField;

    //public static InHousePart selectedPart; 
    public static Part selectedPart;
    // public static OutSourcedPart selectedPart2;

    // ids for product buttons
    @FXML
    private Button partSearchButton;

    @FXML
    private Button productSearchButton;

    @FXML
    private TextField productSearchField;

    // ids for Product
    @FXML
    private TableView<Product> prodTableView;

    @FXML
    private TableColumn<Product, Integer> prodColumnID;

    @FXML
    private TableColumn<Product, String> prodColumnName;

    @FXML
    private TableColumn<Product, Integer> prodColumnIL;

    @FXML
    private TableColumn<Product, Double> prodColumnUCost;

    public static Product selectedProduct;

    //ids for Parts
    @FXML
    private TableView<Part> partTable;

    @FXML
    private TableColumn<Part, Integer> partColumnID;

    @FXML
    private TableColumn<Part, String> partColumnName;

    @FXML
    private TableColumn<Part, Integer> partColumnIL;

    @FXML
    private TableColumn<Part, Double> partColumnUC;

    //Methods for Part Buttons Main Screen
    @FXML
    void AddInHousePart(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        //get reference to the button's stage         
        stage = (Stage) partsLabel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("AddInHousePart.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ModifyInHousePart(ActionEvent event) throws IOException {
        selectedPart = partTable.getSelectionModel().getSelectedItem();
        Parent root;

        if (selectedPart instanceof InHousePart) {
            //  selectedPart = (InHousePart) partTable.getSelectionModel().getSelectedItem() ;
            root = FXMLLoader.load(getClass().getResource("ModifyInHousePart.fxml"));
        } else {
            //  selectedPart = (InHousePart) partTable.getSelectionModel().getSelectedItem() ;
            root = FXMLLoader.load(getClass().getResource("ModifyOutSourcedPart.fxml"));
        }
        Stage stage;
        //get reference to the button's stage         
        stage = (Stage) partsLabel.getScene().getWindow();
        //load up OTHER FXML document
        //    root = FXMLLoader.load(getClass().getResource("ModifyInHousePart.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ModifyOutSourcedPart(ActionEvent event) throws IOException {
        selectedPart = (OutSourcedPart) partTable.getSelectionModel().getSelectedItem();
        Stage stage;
        Parent root;
        //get reference to the button's stage         
        stage = (Stage) partsLabel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("ModifyOutSourcedPart.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void deletePart(ActionEvent event) //TODO: do the same for modify
    {
        Part part = partTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Are you sure you want to delete this part?");
        alert.setContentText("Please click Okay to confirm, or click Cancel");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // user chose OK
            Inventory.deletePart(part);
        } else {
            // user chose CANCEL or closed the dialog
            alert.close();
        }

    }

    @FXML //controls for search button
    void lookupPart(ActionEvent event) {
        String s = partSearchField.getText();

        try {
            int id = Integer.parseInt(s);
            for (int i = 0; i < allParts.size(); i++) {
                if (id == allParts.get(i).getPartID().get()) {
                    partTable.getSelectionModel().select(allParts.get(i));
                }
            }
        } catch (NumberFormatException E) {
            for (int i = 0; i < allParts.size(); i++) {
                if (s.equalsIgnoreCase(allParts.get(i).getName())) {
                    partTable.getSelectionModel().select(allParts.get(i));
                }
            }
        }
    }

    @FXML  // id for part search button // controls for search field
    void partSearchButton(ActionEvent event) {

    }

//Methods for Product Buttons on Main Screen
    @FXML // add button for Products
    void AddProduct(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        //get reference to the button's stage         
        stage = (Stage) partsLabel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML // modify button for Products
    void ModifyProduct(ActionEvent event) throws IOException {
        selectedProduct = prodTableView.getSelectionModel().getSelectedItem();
        Stage stage;
        Parent root;
        //get reference to the button's stage         
        stage = (Stage) partsLabel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void deleteProduct(ActionEvent event) {
        Product product = prodTableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure you want to delete this Product?");
        alert.setHeaderText("The Product has one or more Parts.");
        alert.setContentText("Please click Okay to delete a Product that still has parts, or Cancel.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // user chose OK
            // if the Product does not have an associated part, go ahead and delete it
            Inventory.removeProduct(product);
        } else {
            // user chose CANCEL or closed the dialog
            alert.close();
        }
    }

    @FXML //controls for search button
    void lookupProduct(ActionEvent event) {
        String s = productSearchField.getText();
        try {
            int id = Integer.parseInt(s);
            for (int i = 0; i < products.size(); i++) {
                if (id == products.get(i).getProductID().get()) {
                    prodTableView.getSelectionModel().select(products.get(i));
                }
            }
        } catch (NumberFormatException E) {
            for (int i = 0; i < products.size(); i++) {
                if (s.equalsIgnoreCase(products.get(i).getName())) {
                    prodTableView.getSelectionModel().select(products.get(i));
                }
            }
        }
    }

    @FXML // controls to close IMSystem
    void closeButtonAction(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Are you sure you want to exit the Inventory Management System?");
        alert.setContentText("Please click Okay to confirm, or click Cancel");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // user chose OK
            stage.close();
        } else {
            // user chose CANCEL or closed the dialog
            alert.close();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //=================== bindings for Part====================//
        prodColumnID.setCellValueFactory(cellData
                -> cellData.getValue().getProductID().asObject());

        prodColumnName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        prodColumnIL.setCellValueFactory(cellData
                -> cellData.getValue().getInStock().asObject());

        prodColumnUCost.setCellValueFactory(cellData
                -> cellData.getValue().getPrice().asObject());

        prodTableView.setItems(Inventory.products);

        //=================== bindings for Part====================//
        partColumnID.setCellValueFactory(cellData
                -> cellData.getValue().getPartID().asObject());

        partColumnName.setCellValueFactory(cellData
                -> cellData.getValue().nameProperty());

        partColumnIL.setCellValueFactory(cellData
                -> cellData.getValue().getInStock().asObject());

        partColumnUC.setCellValueFactory(cellData
                -> cellData.getValue().getPrice().asObject());

        partTable.setItems(Inventory.allParts);

    }

}
