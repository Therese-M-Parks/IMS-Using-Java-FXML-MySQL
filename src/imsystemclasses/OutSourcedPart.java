/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsystemclasses;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Therese Parks
 */
public class OutSourcedPart extends Part{
    
      StringProperty companyName = new SimpleStringProperty();

      public OutSourcedPart(int id, String partName, double partPrice, int partInStock, int partMin, int partMax, String compName) {
            super(id, partName, partPrice, partInStock, partMin, partMax);
            this.companyName.set(compName);
        }

        // methods for OutSourcedPart
        public String getCompanyName() {
            return companyName.get();
        }

        public void setCompanyName(StringProperty companyName) {
            this.companyName = companyName;
        }

        public StringProperty companyNameProperty() {
            return companyName;
        }
    }// end OutSourcedPart


    

