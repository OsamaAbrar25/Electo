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
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class PostSelector implements Initializable {
    @FXML
    Button b1;
    @FXML
    Button b2;
    @FXML
    Button b3;
    @FXML
    Button b4;
    @FXML
    CheckBox cb1;
    @FXML
    CheckBox cb2;
    @FXML
    CheckBox cb3;
    @FXML
    CheckBox cb4;

    static Stage stage;

    String logo ="file:///D:/IdeaProjects/Voting_App/Logo.png";
    public void Post1(ActionEvent event) throws IOException{
        if(cb1.isDisable())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No category is selected", ButtonType.OK);
            alert.showAndWait();
        }

        else {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent post1 = FXMLLoader.load(getClass().getResource("Post1.fxml"));
            stage.setScene(new Scene(post1));
            stage.show();
        }
    }
    public void Post2(ActionEvent event) throws IOException {
        if(cb1.isDisable())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No category is selected", ButtonType.OK);
            alert.showAndWait();
        }

        else {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent post2 = FXMLLoader.load(getClass().getResource("Post2.fxml"));
            stage.setScene(new Scene(post2));
            stage.show();
        }
    }

    public void Post3(ActionEvent event) throws IOException {
        if(cb1.isDisable())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No category is selected", ButtonType.OK);
            alert.showAndWait();
        }

        else {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent post3 = FXMLLoader.load(getClass().getResource("Post3.fxml"));
            stage.setScene(new Scene(post3));
            stage.show();
        }
    }
    public void Post4(ActionEvent event) throws IOException{

        if(cb1.isDisable())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No category is selected", ButtonType.OK);
            alert.showAndWait();
        }

        else {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent post4 = FXMLLoader.load(getClass().getResource("Post4.fxml"));
            stage.setScene(new Scene(post4));
            stage.show();
        }
    }
    public void Show_Result(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent result = FXMLLoader.load(getClass().getResource("askForResult_scene.fxml"));
        stage.setScene(new Scene(result));
        stage.show();
    }
    public void logout(ActionEvent event)throws IOException{
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setScene(new Scene(login));
        stage.show();
    }

    public void openSetting(ActionEvent event)throws  IOException{
    	stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Stage about = new Stage();
    	about.initModality(Modality.APPLICATION_MODAL);
    	Parent set = FXMLLoader.load(getClass().getResource("Settings.fxml"));
    	about.setScene(new Scene(set));
    	about.showAndWait();
    	about.setResizable(false);
    	about.getIcons().add(new Image(logo));
    }
    public void aboutus()throws IOException{
    	Stage about = new Stage();
    	about.initModality(Modality.APPLICATION_MODAL);
    	Parent aboutm = FXMLLoader.load(getClass().getResource("AboutUs.fxml"));
    	about.setScene(new Scene(aboutm));
    	about.show();
    	about.setResizable(false);
    	about.getIcons().add(new Image(logo));
    }
    public void review(ActionEvent event)throws IOException{
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent p = FXMLLoader.load(getClass().getResource("review_scene.fxml"));
        stage.setScene(new Scene(p));
        stage.show();
    }


    public void setPosts() {
        Connection conn = Main.getConnection();
        try {
            String get = "SELECT posts FROM Posts";
            PreparedStatement getd = conn.prepareStatement(get);
            ResultSet getData = getd.executeQuery();
            getData.next();
            String n1 = getData.getString(1);
            if(n1 != null){
                b1.setDisable(false);
                b1.setOpacity(1);
                b1.setText(n1);}
            System.out.print(n1);
            getData.next();
            String n2 = getData.getString(1);
            if(n2 != null){
                b2.setDisable(false);
                b2.setOpacity(1);
                b2.setText(n2);}
            getData.next();
            String n3 = getData.getString(1);
            if(n3 != null){
                b3.setDisable(false);
                b3.setOpacity(1);
                b3.setText(n3);}
            getData.next();
            String n4 = getData.getString(1);
            if(n4 != null){
                b4.setDisable(false);
                b4.setOpacity(1);
                b4.setText(n4); }

            conn.close();
        }
        catch (SQLException ee){
            ee.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    , ButtonType.OK);
            alert.showAndWait();
        }
    }
    public Stage getStage(){
        return stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection conn = Main.getConnection();
        try {
            String GSC = "Select posts from Posts;";
            Statement exGetC = conn.createStatement();
            ResultSet g = exGetC.executeQuery(GSC);
            g.next();
            if (g.getString(1) != null) {
                b1.setText(g.getString(1));
                b1.setDisable(false);
                b1.setOpacity(1);
            }

            g.next();
            if (g.getString(1) != null) {
                b2.setText(g.getString(1));
                b2.setDisable(false);
                b2.setOpacity(1);
            }

            g.next();
            if (g.getString(1) != null) {
                b3.setText(g.getString(1));
                b3.setDisable(false);
                b3.setOpacity(1);
            }

            g.next();

            if (g.getString(1) != null) {
                b4.setText(g.getString(1));
                b4.setDisable(false);
                b4.setOpacity(1);
            }

            String cat = "SELECT Categories FROM Categories;";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(cat);

            CheckBox[] cb = {cb1,cb2,cb3,cb4};
            int c = 0;
            while(rs.next())
            {
                if(rs.getString(1).length() > 0) {
                    cb[c].setText(rs.getString(1));
                    cb[c].setDisable(false);
                    c++;
                }
            }

            String cbox = "SELECT Category FROM whichCat";
            Statement ss = conn.createStatement();
            ResultSet r = ss.executeQuery(cbox);
            r.next();
            int su = r.getInt(1);

            cb[su-1].setSelected(true);

            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    , ButtonType.OK);
            alert.showAndWait();
        }

        Main m = new Main();
        Stage stage = m.getStage();
        stage.setResizable(true);

    }

    @FXML
    public void cbox1()
    {
        Connection conn = Main.getConnection();
        try {
            cb2.setSelected(false);
            cb3.setSelected(false);
            cb4.setSelected(false);
            String update = "UPDATE whichCat SET Category = ? WHERE Id = 1";
            PreparedStatement ps = conn.prepareStatement(update);
            ps.setInt(1,1);
            ps.executeUpdate();
            conn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                , ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    public void cbox2()
    {
        Connection conn = Main.getConnection();
        try {
        cb1.setSelected(false);
        cb3.setSelected(false);
        cb4.setSelected(false);

        String update = "UPDATE whichCat SET Category = ? WHERE Id = 1";
        PreparedStatement ps = conn.prepareStatement(update);
        ps.setInt(1,2);
        ps.executeUpdate();
        conn.close();
    }
        catch (SQLException e)
    {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                , ButtonType.OK);
        alert.showAndWait();
    }
    }

    @FXML
    public void cbox3()
    {
        Connection conn = Main.getConnection();
        try {
        cb2.setSelected(false);
        cb1.setSelected(false);
        cb4.setSelected(false);

        String update = "UPDATE whichCat SET Category = ? WHERE Id = 1";
        PreparedStatement ps = conn.prepareStatement(update);
        ps.setInt(1,3);
        ps.executeUpdate();
        conn.close();
    }
        catch (SQLException e)
                {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                            , ButtonType.OK);
                    alert.showAndWait();
                }
    }

    @FXML
    public void cbox4()
    {
        Connection conn = Main.getConnection();
        try {
        cb2.setSelected(false);
        cb3.setSelected(false);
        cb1.setSelected(false);

        String update = "UPDATE whichCat SET Category = ? WHERE Id = 1";
        PreparedStatement ps = conn.prepareStatement(update);
        ps.setInt(1,4);
        ps.executeUpdate();
        conn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    , ButtonType.OK);
            alert.showAndWait();
        }
    }

}
