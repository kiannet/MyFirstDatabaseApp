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
public class WindowForAddDeleteDirectors {
    private GridPane gridPane = new GridPane();
    private TextField textFieldAddNumber = new TextField("Kod");
    private TextField textFieldAddName = new TextField("Name");
    private TextField textFieldAddSpeciality = new TextField("Speciality");
    private Button buttonAddDirector = new Button("Add director");
    private TextField textFieldSearchDirectorById = new TextField("Enter id");
    private TextField textFieldAddNewName = new TextField("Enter new name");
    private TextField textFieldAddNewSpeciality = new TextField("Enter new speciality");
    private Button buttonChangeInformation = new Button("Change");
    private Button buttonDeleteDirector = new Button("Delete director");


    private static final String url = "jdbc:mysql://localhost:3306/my2db";
    private static final String user = "root";
    private static final String password = "vivabelarus";



    private static Connection con;
    private static Statement stmt;

    public WindowForAddDeleteDirectors(){
        allOnPane();
    }

    public void allOnPane(){
        gridPane.add(textFieldAddNumber, 0, 0);
        gridPane.add(textFieldAddName, 0, 1);
        gridPane.add(textFieldAddSpeciality,0, 2);
        gridPane.add(buttonAddDirector, 0, 3);

        gridPane.add(textFieldSearchDirectorById, 1, 0);
        gridPane.add(buttonDeleteDirector, 1, 1);

        gridPane.add(textFieldAddNewName, 2, 0);
        gridPane.add(textFieldAddNewSpeciality, 2, 1);
        gridPane.add(buttonChangeInformation, 2, 3);

        buttonAddDirector.setOnAction(e -> addItemToDataBase());
        buttonDeleteDirector.setOnAction(e -> deleteItemFromDatabase());
        buttonChangeInformation.setOnAction(e -> updateItemsInformation());
    }

    public void showWindowForAddDeleteDirectors(){
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
        String speciality = textFieldAddSpeciality.getText();


        String query = "INSERT INTO directors VALUES ('" + name + "', '" + speciality + "', " + kod + ");";

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
        Integer number = Integer.parseInt(textFieldSearchDirectorById.getText());

        String query = "DELETE FROM directors WHERE kod = " + number + ";";

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

        Integer number = Integer.parseInt(textFieldSearchDirectorById.getText());
        String name = textFieldAddNewName.getText();
        String speciality = textFieldAddNewSpeciality.getText();

        String query = "UPDATE directors d SET d.full_name = '"+ name +"', d.speciality = '" + speciality + "' WHERE d.kod = " + number + ";";

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
