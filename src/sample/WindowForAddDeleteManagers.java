package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by kiannet on 01.11.2017.
 */
public class WindowForAddDeleteManagers {
    private GridPane gridPane = new GridPane();
    private TextField textFieldAddNumber = new TextField("Kod");
    private TextField textFieldAddName = new TextField("Name");
    private TextField textFieldAddContactName = new TextField("Contact Name");
    private TextField textFieldAddPhone = new TextField("Telephone");
    private Button buttonAddItem = new Button("Add marketing one");
    private TextField textFieldSearchMarketingByKod = new TextField("Enter kod");
    private TextField textFieldAddNewName = new TextField("Enter new name");
    private TextField textFieldAddNewContactName = new TextField("Enter new contact name");
    private TextField textFieldAddNewPhone = new TextField("Enter new telephone");
    private Button buttonChangeInformation = new Button("Change");
    private Button buttonDeleteFabric = new Button("Delete marketing one");


    private static final String url = "jdbc:mysql://localhost:3306/my2db";
    private static final String user = "root";
    private static final String password = "vivabelarus";



    private static Connection con;
    private static Statement stmt;

    public WindowForAddDeleteManagers(){
        allOnPane();
    }

    public void allOnPane(){
        gridPane.add(textFieldAddNumber, 0, 0);
        gridPane.add(textFieldAddName, 0, 1);
        gridPane.add(textFieldAddContactName,0, 2);
        gridPane.add(textFieldAddPhone, 0 ,3);
        gridPane.add(buttonAddItem, 0, 4);

        gridPane.add(textFieldSearchMarketingByKod, 1, 0);
        gridPane.add(buttonDeleteFabric, 1, 1);

        gridPane.add(textFieldAddNewName, 2, 0);
        gridPane.add(textFieldAddNewContactName, 2, 1);
        gridPane.add(textFieldAddNewPhone, 2, 2);
        gridPane.add(buttonChangeInformation, 2, 3);

        buttonAddItem.setOnAction(e -> addItemToDataBase());
        buttonDeleteFabric.setOnAction(e -> deleteItemFromDatabase());
        buttonChangeInformation.setOnAction(e -> updateItemsInformation());
    }

    public void showWindowForAddDeleteMarketing(){
        Stage stageForMoreInformation = new Stage();

        stageForMoreInformation.setTitle("Add Change Delete");
        stageForMoreInformation.setMinWidth(370);
        stageForMoreInformation.setMinHeight(200);


        Scene scene = new Scene(gridPane);

        stageForMoreInformation.setScene(scene);
        stageForMoreInformation.showAndWait();
    }

    public void addItemToDataBase(){

        Integer kod = Integer.parseInt(textFieldAddNumber.getText());
        String name = textFieldAddName.getText();
        String contactName = textFieldAddContactName.getText();
        String phone = textFieldAddPhone.getText();


        String query = "INSERT INTO marketing VALUES ('" + name + "', '" + contactName + "', '" + phone + "', " + kod + ");";
        System.out.println(query);

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
        Integer number = Integer.parseInt(textFieldSearchMarketingByKod.getText());

        String query = "DELETE FROM marketing WHERE kod = " + number + ";";

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

        Integer number = Integer.parseInt(textFieldSearchMarketingByKod.getText());
        String name = textFieldAddNewName.getText();
        String contactName = textFieldAddNewContactName.getText();
        String phone = textFieldAddNewPhone.getText();
        String query = "UPDATE marketing m SET m.marketolog_name = '"+ name +"', m.contact_name = '" + contactName + "', m.contact_phone = "+ phone +" WHERE kod = " + number + ";";

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
