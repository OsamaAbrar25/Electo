package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class Post4 implements Initializable {

    @FXML
    TextField Name1;
    @FXML
    TextField Class1;
    @FXML
    TextField Sec1;
    @FXML
    TextField Name2;
    @FXML
    TextField Class2;
    @FXML
    TextField Sec2;
    @FXML
    TextField Name3;
    @FXML
    TextField Class3;
    @FXML
    TextField Sec3;
    @FXML
    TextField Name4;
    @FXML
    TextField Class4;
    @FXML
    TextField Sec4;
    @FXML
    TextField Name5;
    @FXML
    TextField Class5;
    @FXML
    TextField Sec5;
    @FXML
    TextField Name6;
    @FXML
    TextField Class6;
    @FXML
    TextField Sec6;
    @FXML
    Button filechooser1;
    @FXML
    Button filechooser2;
    @FXML
    Button filechooser3;
    @FXML
    Button filechooser4;
    @FXML
    Button filechooser5;
    @FXML
    Button filechooser6;
    @FXML
    Label warning;
    @FXML
    Label postlabel;
    @FXML
    VBox v1;
    @FXML
    VBox v2;
    @FXML
    VBox v3;
    @FXML
    VBox v4;
    @FXML
    VBox v5;
    @FXML
    VBox v6;
    @FXML
    ScrollPane scroll;
    @FXML
    HBox pane;

    static int su;

    Stage stage;

    String logo = "file:///D:/IdeaProjects/Voting_App/Logo.png";
    private String filepath1 = "";
    private String filepath2 = "";
    private String filepath3 = "";
    private String filepath4 = "";
    private String filepath5 = "";
    private String filepath6 = "";


    public void logout(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setScene(new Scene(login));
        stage.show();
    }

    public void file_chooser(ActionEvent event) {

        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("jpeg file", "*.jpg");
        chooser.getExtensionFilters().addAll(filter, new FileChooser.ExtensionFilter("png file", "*.png"));
        File file = chooser.showOpenDialog(null);
        if (event.getSource() == filechooser1) {
            filepath1 = file.getAbsolutePath();
        } else if (event.getSource() == filechooser2) {
            filepath2 = file.getAbsolutePath();
        } else if (event.getSource() == filechooser3) {
            filepath3 = file.getAbsolutePath();
        } else if (event.getSource() == filechooser4) {
            filepath4 = file.getAbsolutePath();
        }else if (event.getSource() == filechooser5) {
            filepath5 = file.getAbsolutePath();
        }else if (event.getSource() == filechooser6) {
            filepath6 = file.getAbsolutePath();
        }

    }


    public void insertdata(ActionEvent event){

        boolean check = true;

        TextField[] Name = {Name1, Name2, Name3, Name4, Name5, Name6};
        TextField[] Class = {Class1, Class2, Class3, Class4, Class5, Class6};
        TextField[] Sec = {Sec1, Sec2, Sec3, Sec4, Sec5, Sec6};
        String[] filepath = {filepath1, filepath2, filepath3, filepath4, filepath5, filepath6};

            Connection conn = Main.getConnection();

            try {
                //deletes the previous votes

                String clearcVote = "UPDATE $t SET Votes = CASE Id WHEN 1 THEN 0 WHEN 2 THEN 0 WHEN 3 THEN 0 WHEN 4 THEN 0 WHEN 5 THEN 0 WHEN 6 THEN 0 END";
                String nocand = "SELECT No_of_cand FROM settingsinfo WHERE id = 1";


                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(nocand);
                rs.next();
                int num = rs.getInt(1);

                for (int s = 0; s < num; s++) {
                    if (Name[s].getText().length() == 0 || Class[s].getText().length() == 0 || Sec[s].getText().length() == 0 || filepath[s].length() == 0) {
                        check = false;
                        warning.setText("All informations are mandatory.");
                    }
                }

                if (check) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "All the previous data will be deleted. Do you want to continue?", ButtonType.YES);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {

                        if (su == 1) {
                            clearcVote = clearcVote.replace("$t", "Post4");
                        } else {
                            clearcVote = clearcVote.replace("$t", "Post" + (su * 10 + 4));
                        }

                        PreparedStatement clear1 = conn.prepareStatement(clearcVote);

                        for (int s = 0; s < num; s++) {
                            String updateStatement3 = "UPDATE $t SET Name = ? WHERE id = ?";
                            if (su == 1) {
                                updateStatement3 = updateStatement3.replace("$t", "Post4");
                            } else {
                                updateStatement3 = updateStatement3.replace("$t", "Post" + (su * 10 + 4));
                            }
                            PreparedStatement insertdata3 = conn.prepareStatement(updateStatement3);
                            insertdata3.setString(1, Name[s].getText());
                            insertdata3.setInt(2, s + 1);
                            insertdata3.executeUpdate();
                        }


                        for (int s = 0; s < num; s++) {
                            String updateStatement3 = "UPDATE $t SET Class = ? WHERE id = ?";

                            if (su == 1) {
                                updateStatement3 = updateStatement3.replace("$t", "Post4");
                            } else {
                                updateStatement3 = updateStatement3.replace("$t", "Post" + (su * 10 + 4));
                            }
                            PreparedStatement insertdata3 = conn.prepareStatement(updateStatement3);
                            insertdata3.setString(1, Class[s].getText());
                            insertdata3.setInt(2, s + 1);
                            insertdata3.executeUpdate();
                        }


                        for (int s = 0; s < num; s++) {
                            String updateStatement3 = "UPDATE $t SET Sec = ? WHERE id = ?";

                            if (su == 1) {
                                updateStatement3 = updateStatement3.replace("$t", "Post4");
                            } else {
                                updateStatement3 = updateStatement3.replace("$t", "Post" + (su * 10 + 4));
                            }
                            PreparedStatement insertdata3 = conn.prepareStatement(updateStatement3);
                            insertdata3.setString(1, Sec[s].getText());
                            insertdata3.setInt(2, s + 1);
                            insertdata3.executeUpdate();
                        }


                        for (int s = 0; s < num; s++) {
                            String updateStatement3 = "UPDATE $t SET Imageaddress = ? WHERE id = ?";

                            if (su == 1) {
                                updateStatement3 = updateStatement3.replace("$t", "Post4");
                            } else {
                                updateStatement3 = updateStatement3.replace("$t", "Post" + (su * 10 + 4));
                            }
                            PreparedStatement insertdata3 = conn.prepareStatement(updateStatement3);
                            insertdata3.setString(1, filepath[s]);
                            insertdata3.setInt(2, s + 1);
                            insertdata3.executeUpdate();
                        }
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("PostSelector.fxml"));
                            stage.setScene(new Scene(parent));
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Alert alert1 = new Alert(Alert.AlertType.ERROR, "Error: Try restarting the application",
                                    ButtonType.OK);
                            alert1.showAndWait();
                        }
                    }
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                Alert alertt = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                        , ButtonType.OK);
                alertt.showAndWait();
            }
    }



    public void review(ActionEvent event) throws IOException {
        Parent reviewScene = FXMLLoader.load(getClass().getResource("review_scene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reviewScene));
    }

    public ObservableList<TableItems> getDetails() throws SQLException {
        Connection conn = Main.getConnection();

        String check = "SELECT No_of_cand FROM settingsinfo;";
        Statement stmnt =  conn.createStatement();
        ResultSet rs = stmnt.executeQuery(check);
        rs.next();
        int c = rs.getInt(1);

        ObservableList<TableItems> list = FXCollections.observableArrayList();

        String[] n = new String[6];
        String[] cl = new String[6];
        String[] sec = new String[6];
        String[] im = new String[6];
        ImageView[] i = new ImageView[6];

        String exist;
        String r = "SELECT * FROM $t WHERE Id = ?";

        if(su == 1){
            r = r.replace("$t","Post4");
            exist = "SELECT Name FROM Post4 WHERE id = 1";
        }

        else{
            r = r.replace("$t","Post"+(su*10+4));
            exist = "SELECT Name FROM Post"+(su*10+4)+" WHERE id = 1";
        }
        Statement st = conn.createStatement();
        ResultSet d = st.executeQuery(exist);
        d.next();
        String l = d.getString(1);
        PreparedStatement s1 = conn.prepareStatement(r);
        for(int s = 0; s < c; s++){
            if(l == null)
            {
                n[s] = " ";
                cl[s] = " ";
                sec[s] = " ";
                im[s] = "Profile.png";
                i[s] = new ImageView(new Image(new File(im[s]).toURI().toString()));
            }
            else {
                s1.setInt(1, s + 1);
                ResultSet rs1 = s1.executeQuery();
                rs1.next();
                n[s] = rs1.getString(2);
                cl[s] = rs1.getString(3);
                sec[s] = rs1.getString(4);
                im[s] = rs1.getString(5);
                i[s] = new ImageView(new Image(new File(im[s]).toURI().toString()));
            }
            i[s].setFitWidth(150);
            i[s].setFitHeight(100);
            i[s].setPreserveRatio(true);
            i[s].setSmooth(true);
            list.add(new TableItems(n[s], cl[s], sec[s], i[s]));
        }

        conn.close();
        return list;
    }

    public void openSetting() throws IOException {
        Stage about = new Stage();
        about.initModality(Modality.APPLICATION_MODAL);
        Parent set = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        about.setScene(new Scene(set));
        about.showAndWait();
        about.setResizable(false);
        about.getIcons().add(new Image(logo));

    }

    public void aboutus() throws IOException {
        Stage about = new Stage();
        about.initModality(Modality.APPLICATION_MODAL);
        Parent aboutm = FXMLLoader.load(getClass().getResource("AboutUs.fxml"));
        about.setScene(new Scene(aboutm));
        about.show();
        about.setResizable(false);
        about.getIcons().add(new Image(logo));
    }

    public void back(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent login = FXMLLoader.load(getClass().getResource("PostSelector.fxml"));
        stage.setScene(new Scene(login));
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection conn = Main.getConnection();
        try {

            String cbox = "SELECT Category FROM whichCat";
            Statement ss = conn.createStatement();
            ResultSet r = ss.executeQuery(cbox);
            r.next();
            su = r.getInt(1);

            String nocand = "SELECT No_of_cand FROM settingsinfo WHERE id = 1";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(nocand);
            rs.next();
            int no = rs.getInt(1);


            VBox[] v = {v1, v2, v3, v4, v5, v6};
            for (int su = 0; su < no; su++) {
                v[su].setOpacity(1);
                v[su].setDisable(false);
            }

            String checkL = "SELECT posts from Posts WHERE id = 4";
            Statement checklab = conn.createStatement();
            ResultSet getLabel = checklab.executeQuery(checkL);
            getLabel.next();
            postlabel.setText(getLabel.getString(1) + "  Info");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace();Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    , ButtonType.OK);
            alert.showAndWait();
        }
        scroll.setFitToWidth(true);

    }

}



