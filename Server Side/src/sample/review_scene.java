package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class review_scene implements Initializable{

    @FXML
    TableView<TableItems> table1;
    @FXML
    TableView<TableItems> table2;
    @FXML
    TableView<TableItems> table3;
    @FXML
    TableView<TableItems> table4;
    @FXML
    TableColumn<TableItems,String> Name1;
    @FXML
    TableColumn<TableItems,Integer> Class1;
    @FXML
    TableColumn<TableItems,String> Sec1;
    @FXML
    TableColumn<TableItems,String> Name2;
    @FXML
    TableColumn<TableItems,Integer> Class2;
    @FXML
    TableColumn<TableItems,String> Sec2;
    @FXML
    TableColumn<TableItems,ImageView> Image1;
    @FXML
    TableColumn<TableItems,ImageView> Image2;
    @FXML
    TableColumn<TableItems,String> Name3;
    @FXML
    TableColumn<TableItems,Integer> Class3;
    @FXML
    TableColumn<TableItems,String> Sec3;
    @FXML
    TableColumn<TableItems,String> Name4;
    @FXML
    TableColumn<TableItems,Integer> Class4;
    @FXML
    TableColumn<TableItems,String> Sec4;
    @FXML
    TableColumn<TableItems,ImageView> Image3;
    @FXML
    TableColumn<TableItems,ImageView> Image4;
    @FXML
    Label Postlabel1;
    @FXML
    Label Postlabel2;
    @FXML
    Label Postlabel3;
    @FXML
    Label Postlabel4;
    @FXML
    Label categ;
    
    Stage stage;
    String logo ="file:///D:/IdeaProjects/Voting_App/Logo.png";


    public void back(ActionEvent event)throws  IOException
    {
        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        Parent back = FXMLLoader.load(getClass().getResource("PostSelector.fxml"));
        stage.setScene(new Scene(back));
        stage.show();
    }

    public void openSetting()throws  IOException{
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
    public void logout(ActionEvent event)throws IOException{
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));
            stage.setScene(new Scene(login));
            stage.show();
            }
    
    @Override
   	public void initialize(URL arg0, ResourceBundle arg1) {

        try{
            Connection conn = Main.getConnection();
            String cbox = "SELECT Category FROM whichCat";
            Statement ss = conn.createStatement();
            ResultSet r = ss.executeQuery(cbox);
            r.next();
            Post1.su = r.getInt(1);
            Post2.su = r.getInt(1);
            Post3.su = r.getInt(1);
            Post4.su = r.getInt(1);

            cbox = "SELECT categories FROM categories WHERE id = "+r.getString(1);
            r = ss.executeQuery(cbox);
            r.next();
            categ.setText(r.getString(1));

            Label[] postlabel = {Postlabel1, Postlabel2, Postlabel3, Postlabel4};

            int c = 0;
            String check2 = "SELECT posts FROM Posts;";
            r = ss.executeQuery(check2);
            while(r.next())
            {
                System.out.print(r.getString(1));
                postlabel[c].setText(r.getString(1));
                c++;
            }


            Class1.setCellValueFactory(new PropertyValueFactory<>("Classs"));
            Name1.setCellValueFactory(new PropertyValueFactory<>("Name"));
            Sec1.setCellValueFactory(new PropertyValueFactory<>("Sec"));
            Image1.setCellValueFactory(new PropertyValueFactory<>("image"));
            Post1 post1 = new Post1();
            table1.setItems(post1.getDetails());
      
            Class2.setCellValueFactory(new PropertyValueFactory<>("Classs"));
            Name2.setCellValueFactory(new PropertyValueFactory<>("Name"));
            Sec2.setCellValueFactory(new PropertyValueFactory<>("Sec"));
            Image2.setCellValueFactory(new PropertyValueFactory<>("image"));
            Post2 post2 = new Post2();
            table2.setItems(post2.getDetails());

            Class3.setCellValueFactory(new PropertyValueFactory<>("Classs"));
            Name3.setCellValueFactory(new PropertyValueFactory<>("Name"));
            Sec3.setCellValueFactory(new PropertyValueFactory<>("Sec"));
            Image3.setCellValueFactory(new PropertyValueFactory<>("image"));
            Post3 post3 = new Post3();
            table3.setItems(post3.getDetails());

            Class4.setCellValueFactory(new PropertyValueFactory<>("Classs"));
            Name4.setCellValueFactory(new PropertyValueFactory<>("Name"));
            Sec4.setCellValueFactory(new PropertyValueFactory<>("Sec"));
            Image4.setCellValueFactory(new PropertyValueFactory<>("image"));
            Post4 post4 = new Post4();
            table4.setItems(post4.getDetails());
            conn.close();
       	 }
       	 catch(SQLException ex){
            ex.printStackTrace();
             Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                     , ButtonType.OK);
             alert.showAndWait();
       	 }
   		
   		}
}
