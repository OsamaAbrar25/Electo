package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class askForResult_scene_Controller {
    Stage stage;
    @FXML
    TextField userid;
    @FXML
    PasswordField pass;
    @FXML
    Label alert;
    
    
    
 

    public void login(ActionEvent event) throws IOException {

        try {
            Connection con = Main.getConnection();
            Statement id = con.createStatement();
            Statement password = con.createStatement();
            ResultSet getid = id.executeQuery("SELECT Username FROM LoginInfo");
            ResultSet getpassword = password.executeQuery("SELECT Password FROM LoginInfo");
            getid.next();
            getpassword.next();
            String idd = getid.getString("Username");
            String passwordd = getpassword.getString("Password");

             //Checking the username and password
            if (userid.getText().equals(idd) && pass.getText().equals(passwordd)) {
                //for captain
                stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                Parent parent = FXMLLoader.load(getClass().getResource("ResultScreen1.fxml"));
                stage.setScene(new Scene(parent));
                stage.show();

            } else if (userid.getText().equals("") || pass.getText().equals("")) {
                alert.setText("All fields are mandatory!!!!");
            } else {
                alert.setText("Wrong id or password");
            }
            con.close();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    , ButtonType.OK);
            alert.showAndWait();
        }
    }
}

