package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Categories {

    @FXML
    TextField tf1;
    @FXML
    TextField tf2;
    @FXML
    TextField tf3;
    @FXML
    TextField tf4;

    @FXML
    public void savee(ActionEvent e)
    {
        Connection conn = Main.getConnection();
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        try {
            TextField[] tf = {tf1, tf2, tf3, tf4};
            for (int i = 0, c = 0; i < 4; i++) {
                if (!(tf[i].getText() == null)) {
                    ++c;
                    String save = "UPDATE categories SET categories = ? WHERE id = ?";
                    PreparedStatement ps = conn.prepareStatement(save);
                    ps.setString(1, tf[i].getText().trim());
                    ps.setInt(2, c);
                    ps.executeUpdate();
                }
            }
            conn.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    , ButtonType.OK);
            alert.showAndWait();
        }
        stage.close();
    }

}
