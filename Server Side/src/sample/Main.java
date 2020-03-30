package sample;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.sql.*;

public class Main extends Application {
    static Stage stage;


    private String idd = null;
    private String passwordd = null;


    @Override
    public void start(Stage primaryStage) throws Exception {
        //login scene : opening scene
        //to change the value of stage to primaryStage
        stage = primaryStage;
        String logo ="file:///D://VotingSoftware-master/Logo.png";
        //loading login scene
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setTitle("Electo");
        stage.setScene(new Scene(root));
        stage.show();
        stage.getIcons().add(new Image(logo));

        new Thread(() -> Networking.connect(5000)).start();
        }


    public static void main(String[] args) {
        Database.InitializeData();
        launch(args);
        final JFXPanel jfxPanel = new JFXPanel();
    }


    public String giveLoginDetails()
    {
        try {
            Connection con = getConnection();
        Statement id = con.createStatement();
        Statement password = con.createStatement();
        ResultSet getid = id.executeQuery("SELECT Username FROM LoginInfo");
        ResultSet getpassword = password.executeQuery("SELECT Password FROM LoginInfo");
        getid.next();
        getpassword.next();
        idd = getid.getString("Username");
        passwordd = getpassword.getString("Password");
        con.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    , ButtonType.OK);
            alert.showAndWait();
        }
        return idd+" "+passwordd;
    }

    //function to connect to database
    public static Connection getConnection(){
        Connection con=null;
        try {
            con = Database.dataSource.getConnection();
        }
        catch(SQLException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    , ButtonType.OK);
            alert.showAndWait();
        }
        return con;
    }

    public Stage getStage()
    {
        return stage;
    }

    }