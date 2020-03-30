package sample;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ResultScreen4 implements Initializable{
    @FXML
    Button Prev_Button;
    @FXML
    Button Next_Button;

    @FXML
    Label p1;
    @FXML
    Label p2;
    @FXML
    Label p3;
    @FXML
    Label p4;
    @FXML
    Label p5;
    @FXML
    Label p6;

    @FXML
    Label p11;
    @FXML
    Label p22;
    @FXML
    Label p33;
    @FXML
    Label p44;
    @FXML
    Label p55;
    @FXML
    Label p66;
    @FXML
    Label post1_label;
    @FXML
    Label category;

    static int su;
    Stage stage;

    public void previous(ActionEvent event) throws IOException
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent Result2 = FXMLLoader.load(getClass().getResource("ResultScreen3.fxml"));
        stage.setScene(new Scene(Result2));
        stage.show();

    }

    public void back(ActionEvent event)throws Exception{
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent Result2 = FXMLLoader.load(getClass().getResource("PostSelector.fxml"));
        stage.setScene(new Scene(Result2));
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resource) {
        Connection conn = Main.getConnection();
        try {
            String cbox = "SELECT Category FROM whichCat";
            Statement ss = conn.createStatement();
            ResultSet r = ss.executeQuery(cbox);
            r.next();
            su = r.getInt(1);

            cbox = "SELECT categories FROM categories WHERE id = "+ su;
            r = ss.executeQuery(cbox);
            r.next();
            category.setText(r.getString(1));

            cbox = "SELECT posts FROM posts WHERE id = 4";
            r = ss.executeQuery(cbox);
            r.next();
            post1_label.setText(r.getString(1));

            String getDet1 = "SELECT Name FROM $t WHERE Name IS NOT NULL;";

            if(su == 1){
                getDet1 = getDet1.replace("$t","Post4");
            }

            else{
                getDet1 = getDet1.replace("$t", "Post" + (su * 10 + 4));
            }

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(getDet1);

            String getDet2 = "SELECT Votes FROM $t;";

            if(su == 1){
                getDet2 = getDet2.replace("$t","Post4");
            }

            else{
                getDet2 = getDet2.replace("$t","Post"+(su*10+4));
            }


            Statement statement2 = conn.createStatement();
            ResultSet resultSet2 = statement2.executeQuery(getDet2);

            Label[] p = {p1,p2,p3,p4,p5,p6};
            Label[] pp = {p11,p22,p33,p44,p55,p66};
            int s = 0;
            while(resultSet.next())
            {
                p[s].setText(resultSet.getString(1));
                resultSet2.next();
                pp[s].setOpacity(1);
                pp[s].setText(Integer.toString(resultSet2.getInt(1)));
                s++;
            }

            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    , ButtonType.OK);
            alert.showAndWait();
        }

    }
}


