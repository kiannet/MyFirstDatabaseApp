package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.*;

/**
 * Created by kiannet on 25.10.2017.
 */
public class WindowForShowingFabricsForTheItem {
    private TableView<Fabric> table;
    private BorderPane borderPane = new BorderPane();

    private static final String url = "jdbc:mysql://localhost:3306/my2db";
    private static final String user = "root";
    private static final String password = "vivabelarus";


    private static Connection con;
    private static Statement stmt;


    public WindowForShowingFabricsForTheItem(){

        borderPane.setCenter(getTable());
    }

    public void showWindowForShowingFabricsForTheItem() {
        Stage stageForMoreInformation = new Stage();

        stageForMoreInformation.setTitle("All Fabrics");
        stageForMoreInformation.setMinWidth(370);
        stageForMoreInformation.setMinHeight(200);


        Scene scene = new Scene(borderPane);

        stageForMoreInformation.setScene(scene);
        stageForMoreInformation.showAndWait();
    }

    public TableView<Fabric> getTable(){
        TableColumn<Fabric, Integer> kodColumn = new TableColumn<>("Kod");
        kodColumn.setMinWidth(100);
        kodColumn.setCellValueFactory(new PropertyValueFactory<>("kod"));

        TableColumn<Fabric, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Fabric, String> placeColumn = new TableColumn<>("Place");
        placeColumn.setMinWidth(100);
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("place"));

        TableColumn<Fabric, String> phoneColumn = new TableColumn<>("Telephone");
        phoneColumn.setMinWidth(100);
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        readFromDataBase();

        table = new TableView<>();
        table.setItems(readFromDataBase());
        table.getColumns().addAll(kodColumn, nameColumn, placeColumn, phoneColumn);

        return table;
    }


    public ObservableList<Fabric> readFromDataBase(){
        ObservableList<Fabric> fabrics = FXCollections.observableArrayList();
        ResultSet rs = null;

        String sItem = MainWindow.getValueFromComboBoxForChoosingItem();
        String query = "SELECT f.kod, f.name, f.address, f.phone FROM (fabrics f INNER JOIN items_by_fabrics it ON (f.kod = it.kod)) INNER JOIN items i ON (i.id = it.id) WHERE i.name='" + sItem + "';";

        System.out.println(query);

        try {

            con = DriverManager.getConnection(url, user, password);

            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Integer kod = rs.getInt(1);
                String name = rs.getString(2);
                String address = rs.getString(3);
                String phone = rs.getString(4);


                Fabric fabric = new Fabric();
                fabric.setName(name);
                fabric.setPlace(address);
                fabric.setTelephone(phone);
                fabric.setKod(kod);

                fabrics.add(fabric);

            }



        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {

            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }

        return fabrics;
    }
}
