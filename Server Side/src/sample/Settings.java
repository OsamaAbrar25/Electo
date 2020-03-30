package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Settings implements  Initializable {

    @FXML
    ChoiceBox<String> candidates;
    @FXML
    ChoiceBox<String> waitingtime;
    @FXML
    Button filechooser;
    @FXML
    PasswordField oldph;
    @FXML
    PasswordField oldp;
    @FXML
    PasswordField newp;
    @FXML
    PasswordField cnewp;
    @FXML
    TextField hint;
    @FXML
    Label error;
    @FXML
    MenuButton mb;
    @FXML
    String fivee;
    @FXML
    String ten;
    @FXML
    String fifteen;
    @FXML
    String two;
    @FXML
    String three;
    @FXML
    String four;
    @FXML
    String five;
    @FXML
    String six;
    @FXML
    Button d1;
    @FXML
    Button d2;
    @FXML
    Button d3;
    @FXML
    Button d4;

    private CheckBox cb0;
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;

    static Stage stage;

    private boolean ch1 = true;
    private boolean ch2 = true;
    private boolean ch3 = true;

    public void save_settings(ActionEvent e) throws SQLException {


        if (oldp.getText().length() > 0 || newp.getText().length() > 0 || cnewp.getText().length() > 0) {
            Connection conn = Main.getConnection();
            String checkpass = "SELECT Password FROM logininfo";
            PreparedStatement ck = conn.prepareStatement(checkpass);
            ResultSet s = ck.executeQuery(checkpass);
            s.next();
            String check = s.getString(1);
            if (check.equals(oldp.getText()) && newp.getText().equals(cnewp.getText())) {
                String chnge = "UPDATE logininfo SET Password= ?";
                PreparedStatement change = conn.prepareStatement(chnge);
                change.setString(1, newp.getText());
                change.executeUpdate();
            } else if (oldp.getText().equals("")) {
                error.setText("All feilds are mandatory");
            } else if (newp.getText().isEmpty() || oldp.getText().isEmpty() || cnewp.getText().isEmpty()) {
                error.setText("All Feilds are Mandatory");
            } else {
                error.setText("Wrong password");
            }
        }

        //for hint
        if (oldph.getText().length() > 0 || hint.getText().length() > 0) {
            if (hint.getText().isEmpty()) {
                error.setText("Hint Feild is empty!!!");
            }
            Connection conn = Main.getConnection();
            String checkpass = "SELECT Password FROM LoginInfo";
            Statement ck = conn.createStatement();
            ResultSet s = ck.executeQuery(checkpass);
            s.next();
            String check = s.getString(1);
            if (check.equals(oldph.getText())) {
                String chnge = "UPDATE LoginInfo SET Hint = ?";
                PreparedStatement change = conn.prepareStatement(chnge);
                change.setString(1, hint.getText());
                change.executeUpdate();
            } else {
                error.setText("Wrong password");
            }

        }


        Connection conn = Main.getConnection();
        if(cb0.isSelected()){
            String add1 = "UPDATE Posts SET posts = ? WHERE id = 1;";
            PreparedStatement add11 = conn.prepareStatement(add1);
            add11.setString(1,cb0.getText());
            add11.executeUpdate();
        }
        else
        {
            if(cb1.isSelected()){
                ch1 = false;
                String add2 = "UPDATE Posts SET posts = ? WHERE id = 1;";
                PreparedStatement add22 = conn.prepareStatement(add2);
                add22.setString(1,cb1.getText());
                add22.executeUpdate();
            }

            else if(cb2.isSelected()){
                ch2 = false;
                String add3 = "UPDATE Posts SET posts = ? WHERE id = 1;";
                PreparedStatement add33 = conn.prepareStatement(add3);
                add33.setString(1,cb2.getText());
                add33.executeUpdate();
            }
            else if(cb3.isSelected()){
                ch3 = false;
                String add4 = "UPDATE Posts SET posts = ? WHERE id = 1;";
                PreparedStatement add44 = conn.prepareStatement(add4);
                add44.setString(1,cb3.getText());
                add44.executeUpdate();
            }
            else{
            String add4 = "UPDATE Posts SET posts = ? WHERE id = 1;";
            PreparedStatement add44 = conn.prepareStatement(add4);
            add44.setString(1,null);
            add44.executeUpdate();}
        }
        if(cb1.isSelected() && ch1){
            String add2 = "UPDATE Posts SET posts = ? WHERE id = 2;";
            PreparedStatement add22 = conn.prepareStatement(add2);
            add22.setString(1, cb1.getText());
            add22.executeUpdate();

        }
        else
        {
            if(cb2.isSelected() && ch2){
                    ch2 = false;
                    String add3 = "UPDATE Posts SET posts = ? WHERE id = 2;";
                    PreparedStatement add33 = conn.prepareStatement(add3);
                    add33.setString(1, cb2.getText());
                    add33.executeUpdate();
                }
            else if(cb3.isSelected() && ch3) {
                ch3 = false;
                String add4 = "UPDATE Posts SET posts = ? WHERE id = 2;";
                PreparedStatement add44 = conn.prepareStatement(add4);
                add44.setString(1, cb3.getText());
                add44.executeUpdate();
            }

            else{
                String add4 = "UPDATE Posts SET posts = ? WHERE id = 2;";
                PreparedStatement add44 = conn.prepareStatement(add4);
                add44.setString(1,null);
                add44.executeUpdate();}
        }
        if(cb2.isSelected() && ch2){
                String add3 = "UPDATE Posts SET posts = ? WHERE id = 3;";
                PreparedStatement add33 = conn.prepareStatement(add3);
                add33.setString(1, cb2.getText());
                add33.executeUpdate();
            }
        else
        {
            if(cb3.isSelected() && ch3){
                ch3 = false;
                String add4 = "UPDATE Posts SET posts = ? WHERE id = 3;";
                PreparedStatement add44 = conn.prepareStatement(add4);
                add44.setString(1,cb3.getText());
                add44.executeUpdate();
            }
            else{
                String add4 = "UPDATE Posts SET posts = ? WHERE id = 3;";
                PreparedStatement add44 = conn.prepareStatement(add4);
                add44.setString(1,null);
                add44.executeUpdate();}
        }
        if(cb3.isSelected() && ch3){
            String add4 = "UPDATE Posts SET posts = ? WHERE id = 4;";
            PreparedStatement add44 = conn.prepareStatement(add4);
            add44.setString(1,cb3.getText());
            add44.executeUpdate();
        }
        else
        {
            String add4 = "UPDATE Posts SET posts = ? WHERE id = 4;";
            PreparedStatement add44 = conn.prepareStatement(add4);
            add44.setString(1,null);
            add44.executeUpdate();
        }
        try {
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("PostSelector.fxml"));
            Parent parent = loader1.load();
            PostSelector ps = loader1.getController();
            ps.setPosts();
            Stage stage1 = ps.getStage();
            stage1.setScene(new Scene(parent));
        }
        catch (IOException ex){
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                    , ButtonType.OK);
            alert.showAndWait();
        }

        //for timer
        String time = "UPDATE settingsinfo set waiting_time = ? WHERE id = 1";
        PreparedStatement pstmt = conn.prepareStatement(time);
        pstmt.setString(1,waitingtime.getValue());
        pstmt.executeUpdate();

        //for no. of candidates
        String cst = "UPDATE settingsinfo SET No_of_cand = ? WHERE id = 1;";
        PreparedStatement pstmnt = conn.prepareStatement(cst);
        pstmnt.setString(1,candidates.getValue());
        pstmnt.executeUpdate();


    Stage st = (Stage)((Node) e.getSource()).getScene().getWindow();
    st.close();
    conn.close();
    }


    public void setPosts() {
        try {

            Connection con = Main.getConnection();
            String name = "SELECT  Name_of_posts from settingsinfo";
            Statement getn = con.createStatement();
            ResultSet getName = getn.executeQuery(name);

            mb.getItems().clear();

            getName.next();
            String n1 = getName.getString(1);
            if (n1.length() > 0) {
                d1.setDisable(false);
                cb0.setText(n1);
                CustomMenuItem item0 = new CustomMenuItem(cb0);
                item0.setHideOnClick(false);
                mb.getItems().add(item0);
            }
            else
                d1.setDisable(true);

            getName.next();
            String n2 = getName.getString(1);
            if (n2.length() > 0) {
                d2.setDisable(false);
                cb1.setText(getName.getString(1));
                CustomMenuItem item1 = new CustomMenuItem(cb1);
                item1.setHideOnClick(false);
                mb.getItems().add(item1);
            }
            else
                d2.setDisable(true);

            getName.next();
            String n3 = getName.getString(1);
            if (n3.length() > 0) {
                d3.setDisable(false);
                cb2.setText(getName.getString(1));
                CustomMenuItem item2 = new CustomMenuItem(cb2);
                item2.setHideOnClick(false);
                mb.getItems().add(item2);
            }
            else
                d3.setDisable(true);

            getName.next();
            String n4 = getName.getString(1);
            if (n4.length() > 0) {
                d4.setDisable(false);
                cb3.setText(getName.getString(1));
                CustomMenuItem item3 = new CustomMenuItem(cb3);
                item3.setHideOnClick(false);
                mb.getItems().add(item3);
            }
            else
                d4.setDisable(true);
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    , ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    public void editPosts(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("Posts.fxml"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.showAndWait();
    }


    public Stage getStage() {
        return stage;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            cb0 = new CheckBox();
            cb1 = new CheckBox();
            cb2 = new CheckBox();
            cb3 = new CheckBox();

            Connection con = Main.getConnection();

            String name = "SELECT  Name_of_posts from settingsinfo";
            Statement getn = con.createStatement();
            ResultSet getName = getn.executeQuery(name);

            getName.next();
            String n1 = getName.getString(1);
            if (n1.length() > 0) {
                d1.setDisable(false);
                cb0.setText(n1);
                CustomMenuItem item0 = new CustomMenuItem(cb0);
                item0.setHideOnClick(false);
                mb.getItems().add(item0);
            }

            getName.next();
            String n2 = getName.getString(1);
            if (n2.length() > 0) {
                d2.setDisable(false);
                cb1.setText(getName.getString(1));
                CustomMenuItem item1 = new CustomMenuItem(cb1);
                item1.setHideOnClick(false);
                mb.getItems().add(item1);
            }

            getName.next();
            String n3 = getName.getString(1);
            if (n3.length() > 0) {
                d3.setDisable(false);
                cb2.setText(getName.getString(1));
                CustomMenuItem item2 = new CustomMenuItem(cb2);
                item2.setHideOnClick(false);
                mb.getItems().add(item2);
            }

            getName.next();
            String n4 = getName.getString(1);
            if (n4.length() > 0) {
                d4.setDisable(false);
                cb3.setText(getName.getString(1));
                CustomMenuItem item3 = new CustomMenuItem(cb3);
                item3.setHideOnClick(false);
                mb.getItems().add(item3);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    , ButtonType.OK);
            alert.showAndWait();
        }

        Connection conn = Main.getConnection();
        try {
            String GSC = "SELECT posts FROM Posts;";
            String s = "SELECT Name_of_posts FROM settingsinfo;;";
            Statement exGetC = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet g = exGetC.executeQuery(GSC);
            ResultSet set = stmt.executeQuery(s);

            g.next();
            set.next();
            String n1 = set.getString(1);
            set.next();
            String n2 = set.getString(1);
            set.next();
            String n3 = set.getString(1);
            set.next();
            String n4 = set.getString(1);


                String pos1 = g.getString(1);
                if (pos1 != null && pos1.equals(n1)) {
                    cb0.setSelected(true);
                }
                else if(pos1 != null && pos1.equals(n2)){
                    cb1.setSelected(true);
                }
                else if(pos1 != null && pos1.equals(n3)){
                    cb2.setSelected(true);
                }
                else if(pos1 != null && pos1.equals(n4)){
                    cb3.setSelected(true);
                }

            g.next();

                String pos2 = g.getString(1);
                if (pos2 != null && pos2.equals(n1)) {
                    cb0.setSelected(true);
                }
                else if(pos2 != null && pos2.equals(n2)){
                    cb1.setSelected(true);
                }
                else if(pos2 != null && pos2.equals(n3)){
                    cb2.setSelected(true);
                }
                else if(pos2 != null && pos2.equals(n4)){
                    cb3.setSelected(true);
                }

            g.next();

                String pos3 = g.getString(1);
                if (pos3 != null && pos3.equals(n1)) {
                    cb0.setSelected(true);
                }
                else if(pos3 != null && pos3.equals(n2)){
                    cb1.setSelected(true);
                }
                else if(pos3 != null && pos3.equals(n3)){
                    cb2.setSelected(true);
                }
                else if(pos3 != null && pos3.equals(n4)){
                    cb3.setSelected(true);
                }

            g.next();

                String pos4 = g.getString(1);
                if (pos4 != null && pos4.equals(n1)) {
                    cb0.setSelected(true);
                }
                else if(pos4 != null && pos4.equals(n2)){
                    cb1.setSelected(true);
                }
                else if(pos4 != null && pos4.equals(n3)){
                    cb2.setSelected(true);
                }
                else if(pos4 != null && pos4.equals(n4)){
                    cb3.setSelected(true);
                }
            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    , ButtonType.OK);
            alert.showAndWait();
        }

        //for no. of candidate
        String cand = "SELECT No_of_cand from settingsinfo WHERE id = 1";
        try {
            Statement cnd = conn.createStatement();
            ResultSet cndd = cnd.executeQuery(cand);
            cndd.next();
            String num = cndd.getString(1);

            if(candidates.getValue() == null){
                candidates.setValue(four);
            }
           if (num != null) {
               switch (num) {
                   case "2":
                       candidates.setValue(two);
                       break;
                   case "3":
                       candidates.setValue(three);
                       break;
                   case "4":
                       candidates.setValue(four);
                       break;
                   case "5":
                       candidates.setValue(five);
                       break;
                   case "6":
                       candidates.setValue(six);
                       break;
               }
            }
        }

        catch (SQLException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    , ButtonType.OK);
            alert.showAndWait();
        }

        //for waiting time
        if(waitingtime.getValue() == null){
            waitingtime.setValue(fivee);
        }
        try {
            String getTime = "SELECT waiting_time from settingsinfo WHERE id = 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(getTime);
            rs.next();
            if(rs.getInt(1) == 5){
                waitingtime.setValue(fivee);
            }
            else if(rs.getInt(1) == 10){
                waitingtime.setValue(ten);
            }
            else if(rs.getInt(1) == 15){
                waitingtime.setValue(fifteen);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    , ButtonType.OK);
            alert.showAndWait();
        }

     }


    @FXML
    public void category(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("Categories.fxml"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    public void eraseVote(ActionEvent event)
    {
        int su;
        Connection conn = Main.getConnection();
        String cbox = "SELECT Category FROM whichCat";
        try {
            Statement ss = conn.createStatement();
            ResultSet r = ss.executeQuery(cbox);
            r.next();
            su = r.getInt(1);

            cbox = "SELECT categories FROM categories WHERE id = "+ su;
            r = ss.executeQuery(cbox);
            r.next();
            String category = r.getString(1);

            String safe = "SET SQL_SAFE_UPDATES=0;";
            ss.executeUpdate(safe);

        Button sourceButton = (Button)event.getSource();

        if(sourceButton == d1)
        {

            cbox = "SELECT Name_of_posts FROM settingsinfo WHERE id = 1";
            r = ss.executeQuery(cbox);
            r.next();
            String post1 = r.getString(1);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete all votes of "+category+" "+post1,
                    ButtonType.YES, ButtonType.NO);

            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                if (su == 1) {
                    String erase = "UPDATE Post1 SET Votes = 0";
                    ss.executeUpdate(erase);

                } else {
                    String erase = "UPDATE Post" + (su * 10 + 1) + "SET Votes = 0";
                    ss.executeUpdate(erase);
                }
            }
        }

        if(sourceButton.equals(d2))
        {

            cbox = "SELECT Name_of_posts FROM settingsinfo WHERE id = 2";
            r = ss.executeQuery(cbox);
            r.next();
            String post2 = r.getString(1);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete all votes of "+category+" "+post2,
                    ButtonType.YES, ButtonType.NO);

            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {

                if (su == 1) {
                    String erase = "UPDATE Post2 SET Votes = 0";
                    ss.executeUpdate(erase);
                } else {
                    String erase = "UPDATE Post" + (su * 10 + 2) + "SET Votes = 0";
                    ss.executeUpdate(erase);
                }
            }
        }

        if(sourceButton == d3)
        {

            cbox = "SELECT Name_of_posts FROM settingsinfo WHERE id = 3";
            r = ss.executeQuery(cbox);
            r.next();
            String post3 = r.getString(1);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete all votes of "+category+" "+post3,
                    ButtonType.YES, ButtonType.NO);

            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                if (su == 1) {
                    String erase = "UPDATE Post3 SET Votes = 0";
                    ss.executeUpdate(erase);
                } else {
                    String erase = "UPDATE Post" + (su * 10 + 3) + "SET Votes = 0";
                    ss.executeUpdate(erase);
                }
            }
        }

        if(sourceButton == d4)
        {

            cbox = "SELECT Name_of_posts FROM settingsinfo WHERE id = 1";
            r = ss.executeQuery(cbox);
            r.next();
            String post4 = r.getString(1);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete all votes of "+category+" "+post4,
                    ButtonType.YES, ButtonType.NO);

            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                if (su == 1) {
                    String erase = "UPDATE Post4 SET Votes = 0";
                    ss.executeUpdate(erase);
                } else {
                    String erase = "UPDATE Post" + (su * 10 + 4) + "SET Votes = 0";
                    ss.executeUpdate(erase);
                }
            }
        }
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

