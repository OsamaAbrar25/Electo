package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    private TextField userid;
    @FXML
    private PasswordField pass;
    @FXML
    private Label label;
    @FXML
    private Hyperlink forgot;


    Stage stage;


    private String Hint = "";


    // for login into administrator or polling window
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
            //for common stage
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //Checking the username and password
            if (userid.getText().equals(idd) && pass.getText().equals(passwordd)) {
                    Parent administratorr = FXMLLoader.load(getClass().getResource("PostSelector.fxml"));
                    stage.setScene(new Scene(administratorr));
                    stage.show();

            }
            //if feilds are empty...
            else if (userid.getText().equals("") || pass.getText().equals("")) {
                label.setText("All fields are mandatory!!!");
            }
// if wrong username or password
            else {
                String geth = "SELECT Hint FROM LoginInfo";
                Statement getHint = con.createStatement();
                ResultSet hint = getHint.executeQuery(geth);
                hint.next();
                Hint = hint.getString(1);
                label.setText("Wrong Username or Password");
                forgot.setDisable(false);
                forgot.setOpacity(1.0);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    , ButtonType.OK);
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stage = Main.stage;
        stage.setResizable(false);

    }

    @FXML
    private void link()
    {
        forgot.setText("Hint:- "+Hint);
    }
}
