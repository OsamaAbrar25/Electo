package sample;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class v13 implements Initializable{

    @FXML
    Label n1;
    @FXML
    Label n2;
    @FXML
    Label n3;
    @FXML
    Label n4;
    @FXML
    Label n5;
    @FXML
    Label n6;
    @FXML
    Label c1;
    @FXML
    Label c2;
    @FXML
    Label c3;
    @FXML
    Label c4;
    @FXML
    Label c5;
    @FXML
    Label c6;
    @FXML
    Label s1;
    @FXML
    Label s2;
    @FXML
    Label s3;
    @FXML
    Label s4;
    @FXML
    Label s5;
    @FXML
    Label s6;
    @FXML
    ImageView i1;
    @FXML
    ImageView i2;
    @FXML
    ImageView i3;
    @FXML
    ImageView i4;
    @FXML
    ImageView i5;
    @FXML
    ImageView i6;

    @FXML
    VBox a1;
    @FXML
    VBox a2;
    @FXML
    VBox a3;
    @FXML
    VBox a4;
    @FXML
    VBox a5;
    @FXML
    VBox a6;
    @FXML
    Button v1;
    @FXML
    Button v2;
    @FXML
    Button v3;
    @FXML
    Button v4;
    @FXML
    Button v5;
    @FXML
    Button v6;
    static String name3;

    private static int su;
    private static int ch = 0;



    private Stage stage;

    public void vote(ActionEvent event){
        try{
            if(ch > 3)
            {
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Parent p = FXMLLoader.load(getClass().getResource("v14.fxml"));
                stage.setScene(new Scene(p));
                stage.show();

                if(event.getSource() == v1){
                    name3 = n1.getText();
                }

                else if(event.getSource() == v2){
                    name3 = n2.getText();
                }

                else if(event.getSource() == v3){
                    name3 = n3.getText();
                }

                else if(event.getSource() == v4){
                    name3 = n4.getText();
                }

                else if(event.getSource() == v5){
                    name3 = n5.getText();
                }

                else if(event.getSource() == v6){
                    name3 = n6.getText();
                }
            }
            else {

                Networking.output.writeUTF("Vote3");


                String m1 = "SELECT id FROM $t WHERE Name = "+"'"+v11.name1+"'";
                String vo1 = "SELECT Votes FROM $t WHERE id = ?";
                String v1 = "UPDATE $t SET Votes = $v WHERE id = ?";

                String m2 = "SELECT id FROM $t WHERE Name = "+"'"+v12.name2+"'";
                String vo2 = "SELECT Votes FROM $t WHERE id = ?";
                String v2 = "UPDATE $t SET Votes = $v WHERE id = ?";

                String m3 = "SELECT id FROM $t WHERE Name = "+"'"+name3+"'";
                String vo3 = "SELECT Votes FROM $t WHERE id = ?";
                String v3 = "UPDATE $t SET Votes = $v WHERE id = ?";


                if(su == 1){
                    vo1 = vo1.replace("$t","Post1");
                    v1 = v1.replace("$t","Post1");
                    m1 = m1.replace("$t","Post1");

                    Networking.output.writeUTF(m1);
                    Networking.output.writeUTF(vo1);
                    Networking.output.writeUTF(v1);

                    vo2 = vo2.replace("$t","Post2");
                    v2 = v2.replace("$t","Post2");
                    m2 = m2.replace("$t","Post2");

                    Networking.output.writeUTF(m2);
                    Networking.output.writeUTF(vo2);
                    Networking.output.writeUTF(v2);

                    vo3 = vo3.replace("$t","Post3");
                    v3 = v3.replace("$t","Post3");
                    m3 = m3.replace("$t","Post3");

                    Networking.output.writeUTF(m3);
                    Networking.output.writeUTF(vo3);
                    Networking.output.writeUTF(v3);
                }

                else{
                    vo1 = vo1.replace("$t","Post"+(su*10+1));
                    v1 = v1.replace("$t","Post"+(su*10+1));
                    m1 = m1.replace("$t","Post"+(su*10+1));

                    Networking.output.writeUTF(m1);
                    Networking.output.writeUTF(vo1);
                    Networking.output.writeUTF(v1);

                    vo2 = vo2.replace("$t","Post"+(su*10+2));
                    v2 = v2.replace("$t","Post"+(su*10+2));
                    m2 = m2.replace("$t","Post"+(su*10+2));

                    Networking.output.writeUTF(m2);
                    Networking.output.writeUTF(vo2);
                    Networking.output.writeUTF(v2);

                    vo3 = vo3.replace("$t","Post"+(su*10+3));
                    v3 = v3.replace("$t","Post"+(su*10+3));
                    m3 = m3.replace("$t","Post"+(su*10+3));

                    Networking.output.writeUTF(m3);
                    Networking.output.writeUTF(vo3);
                    Networking.output.writeUTF(v3);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Parent p = FXMLLoader.load(getClass().getResource("waitingScreen.fxml"));
                stage.setScene(new Scene(p));
                stage.show();

            }

        }
        catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                    , ButtonType.OK);
            alert.showAndWait();
        }
    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        int c = 0;
        Main m = new Main();
        Stage stage = m.getStage();
        stage.setResizable(true);

        try {
            Networking.output.writeUTF("Initialize3");
        }
        catch (IOException cc)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                    , ButtonType.OK);
            alert.showAndWait();
        }

        Label[] name = {n1, n2, n3, n4, n5, n6};
        Label[] classs = {c1, c2, c3, c4, c5, c6};
        Label[] sec = {s1, s2, s3, s4, s5, s6};
        ImageView[] image = {i1, i2, i3, i4, i5, i6};


        try {
            ch = Networking.input.readInt();
            c = Networking.input.readInt();
        }
        catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                    , ButtonType.OK);
            alert.showAndWait();
        }

        String det = "";
        int cl;
        VBox[] a = {a1, a2, a3, a4, a5, a6};
        for (int i = 0; i < c; i++) {
            a[i].setDisable(false);
            a[i].setOpacity(1);
            try {

                det = Networking.input.readUTF();
                name[i].setText(det);
                cl = Networking.input.readInt();
                classs[i].setText(Integer.toString(cl));
                det = Networking.input.readUTF();
                sec[i].setText(det);
                det = Networking.input.readUTF();
                if(det.equals("null")){
                    image[i].setImage(new Image(new File("").toURI().toString()));
                    image[i].setFitHeight(256);
                    image[i].setFitWidth(280);
                    image[i].setPreserveRatio(false);}
                else{
                    image[i].setImage(new Image(new File(det).toURI().toString()));
                    image[i].setFitHeight(256);
                    image[i].setFitWidth(280);
                    image[i].setPreserveRatio(false);}

            }
            catch (IOException exx)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                    , ButtonType.OK);
                alert.showAndWait();
            }
        }


    }


    public void next(java.awt.event.ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("v14.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }

}
