package imsystemclasses;

import static imsystemclasses.Inventory.allParts;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.omg.CORBA.SystemException;


public class Inventory {

    
    static ArrayList<Product> list = new ArrayList<Product>();
    // Now add observability by wrapping it with ObservableList.
    public static ObservableList<Product> products = FXCollections.observableArrayList();


    static ArrayList<Part> list2 = new ArrayList<Part>();
    // Now add observability by wrapping it with ObservableList.
    public static ObservableList<Part> allParts = FXCollections.observableArrayList(list2);

  

    public static void addProduct(Product product) {
        products.add(product);
    }

    
    public static boolean removeProduct(Product product) {
        products.remove(product);
        return true;
    }
    
     public static boolean removeProduct(int productID) {
       // products.remove(product);
        return true;
    }

    public boolean lookupProduct(Product product) {
        return products.contains(product);
    }
      
    public boolean lookupProduct(int productID) {
          return true;
    }
    

    public static void updateProduct(Product product) {
        products.add(product);
    }
    
    public static void addPart(Part part) {
        allParts.add(part);
    }
   
    public static boolean deletePart(Part part) {
        allParts.remove(part);
        return true;
    }
     public static boolean deletePart(int partID) {
        return true;
     }
   
     
    public boolean lookupPart(Part part) {
        return allParts.contains(part);
    }
    
     public boolean lookupPart(int partID) {
        return true;
    }

    public static void updatePart(Part part) {
     
         allParts.add(part);      
    } 
//     public void setPrice(double price) {
//        this.price.set(price);
//    }
   
   

}
