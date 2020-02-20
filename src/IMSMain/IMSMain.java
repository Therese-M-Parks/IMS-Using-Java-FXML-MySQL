
package IMSMain;

import imsystemclasses.Inventory;
import imsystemclasses.Product;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/** Main class of the project. Launches the Application user interface
 *@author Therese Parks
 */
public class IMSMain extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/imsystemfxml/MainScreen.fxml"));
       
        Scene scene = new Scene(root);
   //   boolean add = scene.getStylesheets().add(getClass().getResource("IMSStyle.css").toExternalForm());
        scene.getStylesheets().add(IMSMain.class.getResource("IMSStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
                     launch(args);
                          
   }
         
     
       
      
    
    
}
