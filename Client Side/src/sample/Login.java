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

    private static String Hint = "";

    static Stage stage;


    // for login into administrator or polling window
    public void login(ActionEvent event) throws IOException {

        String returnn = "";

        try {
            Networking.output.writeUTF("Send login details");
            returnn = Networking.input.readUTF();
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the application"
                    , ButtonType.OK);
            alert.showAndWait();
        }


        String idd = " ";
        String passwordd = " ";
        int i = 0;
        while(returnn.charAt(i) != ' ')
        {
            idd = idd + returnn.charAt(i);
            i++;
        }

        idd = idd.trim();
        passwordd = returnn.substring(i+1);

            //for common stage
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            //Checking the username and password
            if (userid.getText().equals(idd) && pass.getText().equals(passwordd)) {
                try {
                    Networking.output.writeUTF("Login successfull");
                    }
                catch (IOException ee)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the application"
                            , ButtonType.OK);
                    alert.showAndWait();
                }

                String tf = Networking.input.readUTF();
                Boolean che = Boolean.valueOf(tf);
                if (che) {
                    Parent parent = FXMLLoader.load(getClass().getResource("v11.fxml"));
                    stage.setScene(new Scene(parent));
                    stage.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "No or incomplete information found for voting", ButtonType.OK);
                    alert.showAndWait();
                }


            }
            //if feilds are empty...
            else if(userid.getText().equals("") || pass.getText().equals("")) {
                label.setText("All fields are mandatory!!!");
            }
                 else {
                    Networking.output.writeUTF("Hint");
                    Hint = Networking.input.readUTF();

                label.setText("Wrong Username or Password");
                forgot.setDisable(false);
                forgot.setOpacity(1.0);
            }
        }

    @FXML
    public void showHint() {
        forgot.setText(Hint);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stage = Main.stage;
        stage.setResizable(false);
    }
}
