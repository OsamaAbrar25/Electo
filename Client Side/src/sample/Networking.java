package sample;

import com.mysql.cj.conf.DatabaseUrlContainer;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Networking {
    private static Socket socket = null;
    public static DataOutputStream output = null;
    public static DataInputStream input = null;

    public static void connect(String Address, int port) {
        try {
            socket = new Socket(Address, port);
            System.out.print("Connected");
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            output = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                    , ButtonType.OK);
            alert.showAndWait();
            Main.ch = false;
        }
    }
}
