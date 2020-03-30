package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class Posts implements Initializable {
    @FXML
    TextField po1;
    @FXML
    TextField po2;
    @FXML
    TextField po3;
    @FXML
    TextField po4;

    public void save(ActionEvent event)throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"All the data will be deleted. Do you want to continue?",ButtonType.YES);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES){
        Connection con = Main.getConnection();
        try {
            String get = "UPDATE SettingsInfo SET Name_of_posts = CASE Id WHEN 1 THEN ? WHEN 2 THEN ? WHEN 3 THEN ? WHEN 4 THEN ? END";
            PreparedStatement getd = con.prepareStatement(get);
            String p1 = po1.getText().trim();
            getd.setString(1,p1);
            String p2 = po2.getText().trim();
            getd.setString(2,p2);
            String p3 = po3.getText().trim();
            getd.setString(3,p3);
            String p4 = po4.getText().trim();
            getd.setString(4,p4);
            getd.executeUpdate();
            con.close();
        }
        catch (SQLException e){
            e.printStackTrace();
            Alert alertt = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    , ButtonType.OK);
            alertt.showAndWait();
        }


        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("Settings.fxml"));
        Parent parent2 = loader2.load();
        Settings sc = loader2.getController();
        sc.setPosts();
        Stage stage2 = sc.getStage();
        stage2.setScene(new Scene(parent2));
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection con = Main.getConnection();
        try {
            String get = "SELECT Name_of_posts FROM SettingsInfo";
            Statement getd = con.createStatement();
            ResultSet getDetails = getd.executeQuery(get);
            getDetails.next();
            po1.setText(getDetails.getString(1));
            getDetails.next();
            po2.setText(getDetails.getString(1));
            getDetails.next();
            po3.setText(getDetails.getString(1));
            getDetails.next();
            po4.setText(getDetails.getString(1));
            con.close();
       }
       catch (SQLException e){
            e.printStackTrace();
           Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                   , ButtonType.OK);
           alert.showAndWait();
       }
    }
}



