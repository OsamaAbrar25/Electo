package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class Main extends Application {
    static Stage stage;
    static boolean ch = true;
    @Override
    public void start(Stage primaryStage) throws Exception {

        Networking.connect("127.0.0.1", 5000);
        //login scene : opening scene
        //to change the value of stage to primaryStage
        stage = primaryStage;
        if(ch) {
            String logo = "Logo.png";
            //loading login scene
            Parent root = FXMLLoader.load(getClass().getResource("opening.fxml"));
            stage.setTitle("Electo");
            stage.setScene(new Scene(root));
            stage.show();
            //stage.getIcons().add(new Image(logo));
        }
        stage.setOnCloseRequest(event -> {
    try {
            Networking.output.writeUTF("Exit");
        }
        catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                    , ButtonType.OK);
            alert.showAndWait();
        }});
    }

    public static void main(String[] args) {
        launch(args);
    }
    //function to connect to database


    public Stage getStage()
    {
        return stage;
    }
    public static Connection getConnection(){
        Connection c = null;
       return c;
    }
    }








