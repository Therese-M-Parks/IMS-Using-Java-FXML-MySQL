/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsystemclasses;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Therese Parks
 */
public class InHousePart extends Part{
      IntegerProperty machineID = new SimpleIntegerProperty();
       // int macID = 0;
     public InHousePart(int id, String partName, double partPrice, int partInStock, int partMin, int partMax, int macID) {         
         super(id, partName, partPrice, partInStock, partMin, partMax);
         setMachineID(macID);
     }
     

          
// methods for inHouse Part

        public IntegerProperty getMachineID() {
            return machineID;
        }

        public void setMachineID(int machineID) {
            this.machineID.set(machineID);
            
        }

        public IntegerProperty machineIDProperty() {
            return machineID;
        }

    public String getCompanyName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }//end  InHouse Part

    
    

