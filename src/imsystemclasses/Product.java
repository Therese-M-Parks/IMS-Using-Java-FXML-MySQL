/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsystemclasses;

import imsystemclasses.Inventory;
import static imsystemclasses.Inventory.allParts;
import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    // Use Java Collections to create the List.

    ArrayList<Part> list3 = new ArrayList<Part>();
   
    public ObservableList<Part> associatedParts = FXCollections.observableArrayList(list3);
    private IntegerProperty productID = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private DoubleProperty price = new SimpleDoubleProperty();
    private IntegerProperty inStock = new SimpleIntegerProperty();
    private IntegerProperty min = new SimpleIntegerProperty();
    private IntegerProperty max = new SimpleIntegerProperty();
    public static int numParts;

// will be needed to manipulate user interface
    public String toString() {
        return new Integer(productID.get()).toString() + " " + name.get();
    }
//  default constructor

    public Product(int id, String prodName, double prodPrice, int prodInStock, int prodMin, int prodMax) {
        productID.set(numParts++);
        name.set(prodName);
        price.set(prodPrice);
        inStock.set(prodInStock);
        min.set(prodMin);
        max.set(prodMax);
    }

    public IntegerProperty getProductID() {
        return productID;
    }

    public void setProductID(IntegerProperty productID) {
        this.productID = productID;
    }

    public IntegerProperty productIDProperty() {
        return productID;
    }

    public String getName() {
        return name.get();
    }

    public void setName(StringProperty name) {
        this.name = name;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public DoubleProperty getPrice() {
        return price;
    }

    public void setPrice(DoubleProperty price) {
        this.price = price;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public IntegerProperty getInStock() {
        return inStock;
    }

    public void setInStock(IntegerProperty inStock) {
        this.inStock = inStock;
    }

    public IntegerProperty inStockProperty() {
        return inStock;
    }

    public IntegerProperty getMin() {
        return min;
    }

    public void setMin(IntegerProperty min) {
        this.min = min;
    }

    public IntegerProperty minProperty() {
        return min;
    }

    public IntegerProperty getMax() {
        return max;
    }

    public void setMax(IntegerProperty max) {
        this.max = max;
    }

    public IntegerProperty maxProperty() {
        return max;
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

//public void addAssociatedParts(Part part) {
//        associatedParts.add();
//    }
    public boolean removeAssociatedPart(Part part) {
        associatedParts.remove(part);
        return true;
    }

    public boolean lookupAssociatedPart(Part part) {
        return associatedParts.contains(part);
    }

    public boolean lookupAssociatedPart(int partID) {
        return true;
    }
}
