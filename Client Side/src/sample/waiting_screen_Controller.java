package sample;


import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class waiting_screen_Controller implements Initializable
{
    @FXML
    ProgressBar pb;

    int s = 0;

    Stage stage;

   public void waitscr()
   {
       try {
           stage = Login.stage;
           Networking.output.writeUTF("time");
           s = Networking.input.readInt();
           System.out.print(s);
           Timeline timeline = new Timeline(
                   new KeyFrame(Duration.ZERO, new KeyValue(pb.progressProperty(), 0)), new
                   KeyFrame(Duration.seconds(s), event -> {
            try{
               stage = Login.stage;
               Parent p = FXMLLoader.load(getClass().getResource("v11.fxml"));
               stage.setScene(new Scene(p));
               stage.show();}
            catch (IOException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                        , ButtonType.OK);
                alert.showAndWait();
            } }, new KeyValue(pb.progressProperty(), 1)));

           //timeline.setCycleCount(Animation);
           timeline.play();

       }
        catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                    , ButtonType.OK);
            alert.showAndWait();
        }
   }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      waitscr();
   }
}




