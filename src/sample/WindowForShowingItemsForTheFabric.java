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
public class WindowForShowingItemsForTheFabric {
    private TableView<Item> table;
    private BorderPane borderPane = new BorderPane();

    private static final String url = "jdbc:mysql://localhost:3306/my2db";
    private static final String user = "root";
    private static final String password = "vivabelarus";


    private static Connection con;
    private static Statement stmt;


    public WindowForShowingItemsForTheFabric(){

        borderPane.setCenter(getTable());
    }

    public void showWindowForItemsForTheFabrics() {
        Stage stageForMoreInformation = new Stage();

        stageForMoreInformation.setTitle("All Fabrics");
        stageForMoreInformation.setMinWidth(370);
        stageForMoreInformation.setMinHeight(200);


        Scene scene = new Scene(borderPane);

        stageForMoreInformation.setScene(scene);
        stageForMoreInformation.showAndWait();
    }

    public TableView<Item> getTable(){
        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Item, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        readFromDataBase();

        table = new TableView<>();
        table.setItems(readFromDataBase());
        table.getColumns().addAll(nameColumn, priceColumn);

        return table;
    }


    public ObservableList<Item> readFromDataBase(){
        ObservableList<Item> items = FXCollections.observableArrayList();
        ResultSet rs = null;

        String sFabric = MainWindow.getValueFromComboBoxForChoosingFabric();
        String query = "SELECT i.name, i.price FROM (items i INNER JOIN items_by_fabrics it ON (i.id = it.id)) INNER JOIN fabrics f ON (f.kod = it.kod) WHERE f.name = '" + sFabric + "';";
        System.out.println(query);

            try {

            con = DriverManager.getConnection(url, user, password);

            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString(1);
                Integer price = rs.getInt(2);


                Item item = new Item();
                item.setName(name);
                item.setPrice(price);

                items.add(item);

                System.out.println();

            }



        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {

            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }

        return items;
    }
}
