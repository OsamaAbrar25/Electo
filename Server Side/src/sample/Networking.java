package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Networking {

    public static void connect(int port)
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(port);

            while(true)
            {
                Socket socket;
                    socket = serverSocket.accept();
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                    Thread thread = new ServerThread(socket, input, output);
                    thread.start();
            }
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
            exception.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the application"
                    , ButtonType.OK);
            alert.showAndWait();
        }
    }

}
