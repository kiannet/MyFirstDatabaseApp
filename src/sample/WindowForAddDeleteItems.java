package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

/**
 * Created by kiannet on 24.10.2017.
 */
public class WindowForAddDeleteItems {
    private GridPane gridPane = new GridPane();
    private TextField textFieldAddNumber = new TextField("Number");
    private TextField textFieldAddName = new TextField("Name");
    private TextField textFieldAddPrice = new TextField("Price");
    private TextField textFieldAddType = new TextField("Type");
    private Button buttonAddItem = new Button("Add item");
    private TextField textFieldSearchItemById = new TextField("Enter ID");
    private TextField textFieldAddNewName = new TextField("Enter new name");
    private TextField textFieldAddNewPrice = new TextField("Enter new price");
    private TextField textFieldAddNewType = new TextField("Enter new type");
    private Button buttonChangeInformation = new Button("Change");
    private Button buttonDeleteItem = new Button("Delete item");


    private static final String url = "jdbc:mysql://localhost:3306/my2db";
    private static final String user = "root";
    private static final String password = "vivabelarus";



    private static Connection con;
    private static Statement stmt;

    public WindowForAddDeleteItems(){
        allOnPane();
    }

    public void allOnPane(){
        gridPane.add(textFieldAddNumber, 0, 0);
        gridPane.add(textFieldAddName, 0, 1);
        gridPane.add(textFieldAddType,0, 2);
        gridPane.add(textFieldAddPrice, 0 ,3);
        gridPane.add(buttonAddItem, 0, 4);

        gridPane.add(textFieldSearchItemById, 1, 0);
        gridPane.add(buttonDeleteItem, 1, 1);

        gridPane.add(textFieldAddNewName, 2, 0);
        gridPane.add(textFieldAddNewType, 2, 1);
        gridPane.add(textFieldAddNewPrice, 2, 2);
        gridPane.add(buttonChangeInformation, 2, 3);

        buttonAddItem.setOnAction(e -> addItemToDataBase());
        buttonDeleteItem.setOnAction(e -> deleteItemFromDatabase());
        buttonChangeInformation.setOnAction(e -> updateItemsInformation());
    }

    public void showWindowForAddDeleteItems(){
        Stage stageForMoreInformation = new Stage();

        stageForMoreInformation.setTitle("Add Change Delete");
        stageForMoreInformation.setMinWidth(370);
        stageForMoreInformation.setMinHeight(200);


        Scene scene = new Scene(gridPane);

        stageForMoreInformation.setScene(scene);
        stageForMoreInformation.showAndWait();
    }

    public void addItemToDataBase(){

        Integer number = Integer.parseInt(textFieldAddNumber.getText());
        String name = textFieldAddName.getText();
        String type = textFieldAddType.getText();
        Integer price = Integer.parseInt(textFieldAddPrice.getText());
        System.out.println(number);

        String query = "INSERT INTO items VALUES ("+ number + ", '" + name + "', '" + type + "', " + price + ");";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            stmt.executeUpdate(query);



        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }

    }

    public void deleteItemFromDatabase(){
        Integer number = Integer.parseInt(textFieldSearchItemById.getText());

        String query = "DELETE FROM items WHERE id = " + number + ";";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            stmt.executeUpdate(query);



        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    public void updateItemsInformation(){
        Integer number = Integer.parseInt(textFieldSearchItemById.getText());
        String name = textFieldAddNewName.getText();
        String type = textFieldAddNewType.getText();
        Integer price = Integer.parseInt(textFieldAddNewPrice.getText());
        String query = "UPDATE items i SET i.name = '"+ name +"', i.type = '"+ type+ "', i.price = "+ price +" WHERE id = " + number + ";";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            stmt.executeUpdate(query);



        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }


}
