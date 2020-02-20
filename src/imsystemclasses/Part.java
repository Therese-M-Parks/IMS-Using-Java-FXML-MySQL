/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsystemclasses;

import static imsystemclasses.Inventory.allParts;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Therese Parks
 */
public abstract class Part {

    IntegerProperty partID = new SimpleIntegerProperty();
    StringProperty name = new SimpleStringProperty();
    DoubleProperty price = new SimpleDoubleProperty();
    IntegerProperty inStock = new SimpleIntegerProperty();
    IntegerProperty min = new SimpleIntegerProperty();
    IntegerProperty max = new SimpleIntegerProperty();
    public static int numParts; 
   
    // default constructor
    
    public Part(int id, String partName, double partPrice, int partInStock, int partMin, int partMax) {
        this.partID.set(numParts++); 
        this.name.set(partName);
        this.price.set(partPrice);
        this.inStock.set(partInStock);
        this.min.set(partMin);
        this.max.set(partMax);
    }
    
    //methods for Part

    // will be needed to manipulate user interface
    public  String toString() {
        return new Integer(partID.get()).toString() + " " + name.get();

    }

    public IntegerProperty getPartID() {
        return partID;
    }

    public void setPartID(int partID) {
        this.partID.set(partID);
    }

    public IntegerProperty partIDProperty() {
        return partID;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public DoubleProperty getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public IntegerProperty getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }

    public IntegerProperty inStockProperty() {
        return inStock;
    }

    public IntegerProperty getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    public IntegerProperty minProperty() {
        return min;
    }

    public IntegerProperty getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max.set(max);
    }

    public IntegerProperty maxProperty() {
        return max;
    }

    public boolean contains(DoubleProperty price) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}// end Part