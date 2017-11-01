package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

import java.sql.*;

/**
 * Created by kiannet on 24.10.2017.
 */
public class MainWindow extends GridPane{

    private static ComboBox comboBoxForChoosingFabric = new ComboBox();
    private static ComboBox comboBoxForChoosingThing = new ComboBox();
    private Button buttonForOpeningThingTable = new Button("Add/Change/Remove (Things)");
    private Button buttonForOpeningFabricTable = new Button("Add/Change/Remove (Fabrics)");
    private Button buttonForOpeningDirectorTable = new Button("Add/Change/Remove (Directors)");
    private Button buttonForOpeningManagersTable = new Button("Add/Change/Remove (Managers)");
    private Button buttonForShowingThingsTable = new Button("Show things for this Fabric");
    private Button buttonForShowingFabricsTable = new Button("Show fabrics for this Thing");
    private Button buttonForShowingAllFabrics = new Button("Show All Fabrics");

    private static final String url = "jdbc:mysql://localhost:3306/my2db";
    private static final String user = "root";
    private static final String password = "vivabelarus";

    private static Connection con;
    private static Statement stmt;


    public MainWindow() {
        this.showMainWindow();
        buttonForShowingAllFabrics.setOnAction(e -> new WindowForShowingAllFabrics().showWindowForAllFabrics());
        buttonForOpeningThingTable.setOnAction(e -> new WindowForAddDeleteItems().showWindowForAddDeleteItems());
        buttonForShowingThingsTable.setOnAction(e -> new WindowForShowingItemsForTheFabric().showWindowForItemsForTheFabrics());
        buttonForShowingFabricsTable.setOnAction(e -> new WindowForShowingFabricsForTheItem().showWindowForShowingFabricsForTheItem());
        buttonForOpeningFabricTable.setOnAction(e -> new WindowForAddDeleteFabrics().showWindowForAddDeleteFabrics());
        buttonForOpeningManagersTable.setOnAction(e -> new WindowForAddDeleteManagers().showWindowForAddDeleteMarketing());
        buttonForOpeningDirectorTable.setOnAction(e -> new WindowForAddDeleteDirectors().showWindowForAddDeleteDirectors());
        comboBoxForChoosingFabric.setItems(getAllFabricsNames());
        comboBoxForChoosingThing.setItems(getAllItemsNames());

    }

    public void showMainWindow(){

        this.setMinHeight(300);
        this.setMinWidth(300);
        //comboBoxForChoosingFabric.setItems(getAllFabricsNames());
        this.add(comboBoxForChoosingFabric, 1, 0);
        this.add(comboBoxForChoosingThing, 1, 2);
        this.add(buttonForShowingThingsTable, 1, 1);
        this.add(buttonForShowingFabricsTable, 1, 3);
        this.add(buttonForOpeningThingTable, 0, 0);
        this.add(buttonForOpeningManagersTable, 0, 1);
        this.add(buttonForOpeningDirectorTable, 0, 2);
        this.add(buttonForOpeningFabricTable, 0 ,3);
        this.add(buttonForShowingAllFabrics, 0, 4);


    }

    public ObservableList<String> getAllFabricsNames(){
        ObservableList<String > fabricsNames = FXCollections.observableArrayList();

        ResultSet rs = null;

        String query = "select name from fabrics;";

        try {

            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString(1);

                fabricsNames.add(name);

            }



        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {

            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }


        return fabricsNames;
    }


    public ObservableList<String> getAllItemsNames(){
        ObservableList<String > itemsNames = FXCollections.observableArrayList();

        ResultSet rs = null;

        String query = "select name from items;";

        try {

            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString(1);

                itemsNames.add(name);

            }



        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {

            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }


        return itemsNames;
    }


    public static String getValueFromComboBoxForChoosingFabric(){
        return String.valueOf(comboBoxForChoosingFabric.getValue());
    }

    public static String getValueFromComboBoxForChoosingItem(){
        return String.valueOf(comboBoxForChoosingThing.getValue());
    }


}
