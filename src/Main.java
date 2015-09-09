
/**
 * Main class. 
 * @author Csaba Farkas R00117945
 * 
 */
import controller.CensusController;
import controller.CensusDataPersistor;
import controller.CensusGUI;
import controller.DatabaseController;
import model.CensusData;
import mysql.MySQLController;
import view.CensusFrame;

public class Main {

    public static void main(String[] arguments) {
        
        //Create data model
        CensusData censusDataModel = new CensusData();
        //Create a CenusDataPersistor and assign it to the DatabaseController to connect to and communicate with the DB
        CensusDataPersistor censusDataPersistor = new MySQLController();
        DatabaseController.getInstance().setCensusDataPersistor(censusDataPersistor);
        //Set the data model that was created to the data model of the DatabaseController
        DatabaseController.getInstance().getCensusDataPersistor().setCensusData(censusDataModel);
        //Set the data model of the CensusController to the data model of the DatabaseController
        CensusController.getInstance().setCensusDataModel(DatabaseController.getInstance().getCensusDataPersistor().getCensusData());
        //Create a GUI and set it to the GUI reference of the CensusController
        CensusGUI censusFrame = new CensusFrame("Census 1901");
        CensusController.getInstance().setGUIReference(censusFrame);
        
    }

}
