package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServerThread extends Thread {
    final Socket socket;
    final DataInputStream input;
    final DataOutputStream output;

    static int su;
    public ServerThread(Socket clientSocket, DataInputStream input, DataOutputStream output) {
        this.socket = clientSocket;
        this.input = input;
        this.output = output;
    }

    public void run() {
        String line = "";
                while(true) {
            try {
                line = input.readUTF();
                if(line.equalsIgnoreCase("Exit"))
                {
                    this.socket.close();
                    break;
                }
                doWork(line);
            } catch (IOException e) {
                e.printStackTrace();
               // Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                     //   , ButtonType.OK);
               // alert.showAndWait();
            }


            }
        }




    private void doWork(String line) {
        Connection conn = Main.getConnection();

        switch (line) {

            case "Exit":
                try {
                    this.socket.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                break;

            case "Send login details":

                Main main = new Main();
                String details = main.giveLoginDetails();
                String id = " ";
                String password = " ";
                int i = 0;
                while (details.charAt(i) != ' ') {
                    id = id + details.charAt(i);
                    i++;
                }

                try {
                    id = id.trim();
                    password = details.substring(i + 1);
                    output.writeUTF(id + " " + password);

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    //Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                     //       , ButtonType.OK);
                    //alert.showAndWait();
                }
                break;

            case "Login successfull":

                        String check = "SELECT posts FROM Posts WHERE posts IS NOT NULL;";
                        try {

                            Statement stmt2 = conn.createStatement();

                            String cbox = "SELECT Category FROM whichCat";
                            ResultSet rs2 = stmt2.executeQuery(cbox);
                            rs2.next();
                            su = rs2.getInt(1);

                            boolean che = true;

                            int s = 0;
                            rs2 = stmt2.executeQuery(check);

                            while (rs2.next()) {
                                s++;
                            }
                            for (int z = 0; z < s; z++) {
                                if (su == 1) {
                                    rs2 = stmt2.executeQuery("SELECT Name FROM Post" + s);
                                    rs2.next();
                                    String a = rs2.getString(1);
                                    if (a == null) {
                                        che = false;
                                    }
                                } else {
                                    rs2 = stmt2.executeQuery("SELECT Name FROM Post" + (su * 10 + s));
                                    rs2.next();
                                    String a = rs2.getString(1);
                                    if (a == null) {
                                        che = false;
                                    }
                                }
                            }

                            if (che)
                                output.writeUTF("true");
                            else
                                output.writeUTF("False");

                        }
                        catch (SQLException e)
                        {
                            e.printStackTrace();
                           // Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                           //         , ButtonType.OK);
                           // alert.showAndWait();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                           // Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                            //        , ButtonType.OK);
                           // alert.showAndWait();
                        }

                        break;


            case "Hint" :
                    try {
                        String geth = "SELECT Hint FROM LoginInfo";
                        Statement getHint = conn.createStatement();
                        ResultSet hint = getHint.executeQuery(geth);
                        hint.next();
                        output.writeUTF(hint.getString(1));
                        conn.close();
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                       // Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                       //         , ButtonType.OK);
                        //alert.showAndWait();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                        //Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                        //        , ButtonType.OK);
                        //alert.showAndWait();
                    }
                    break;

            case "Initialize1":
            try{
                int ch = 0;
                String check2 = "SELECT posts FROM Posts WHERE posts IS NOT NULL;;";
                Statement stmt2 = conn.createStatement();
                ResultSet rs2 = stmt2.executeQuery(check2);
                while(rs2.next())
                {
                    ch++;
                }
                output.writeInt(ch);

                check2 = "SELECT No_of_cand FROM settingsinfo WHERE id = 1;";
                rs2 = stmt2.executeQuery(check2);
                rs2.next();
                int cz = rs2.getInt(1);
                output.writeInt(cz);

                String getDet1 = "SELECT * FROM $t;";
                if(su == 1)
                    getDet1 = getDet1.replace("$t", "Post1");
                else
                    getDet1 = getDet1.replace("$t", "Post"+(su*10+1));
                rs2 = stmt2.executeQuery(getDet1);
                String d;
                int dd;

                for (int x = 0; x < cz; x++) {
                    rs2.next();
                    d = rs2.getString(2);
                    output.writeUTF(d);
                    dd = rs2.getInt(3);
                    output.writeInt(dd);
                    d = rs2.getString(4);
                    output.writeUTF(d);
                    d = rs2.getString(5);
                    if(d == null) {
                        output.writeUTF("null");
                        }
                    else{
                        output.writeUTF(d);
                        }

                }
        }
            catch (SQLException e)
            {
                e.printStackTrace();
                ////Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                        //, ButtonType.OK);
                //alert.showAndWait();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                //Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                //        , ButtonType.OK);
               // alert.showAndWait();
            }
        break;

            case "Initialize2":
                try{
                    int ch = 0;
                    String check2 = "SELECT posts FROM Posts WHERE posts IS NOT NULL;";
                    Statement stmt2 = conn.createStatement();
                    ResultSet rs2 = stmt2.executeQuery(check2);
                    while(rs2.next())
                    {
                        ch++;
                    }
                    System.out.print(ch);
                    output.writeInt(ch);

                    check2 = "SELECT No_of_cand FROM settingsinfo WHERE id = 1;";
                    rs2 = stmt2.executeQuery(check2);
                    rs2.next();
                    int cz = rs2.getInt(1);
                    output.writeInt(cz);

                    String getDet1 = "SELECT * FROM $t;";
                    if(su == 1)
                        getDet1 = getDet1.replace("$t", "Post2");
                    else
                        getDet1 = getDet1.replace("$t", "Post"+(su*10+2));
                    rs2 = stmt2.executeQuery(getDet1);
                    String d;
                    int dd;

                    for (int x = 0; x < cz; x++) {
                        rs2.next();
                        d = rs2.getString(2);
                        output.writeUTF(d);
                        dd = rs2.getInt(3);
                        output.writeInt(dd);
                        d = rs2.getString(4);
                        output.writeUTF(d);
                        d = rs2.getString(5);
                        if(d == null) {
                            output.writeUTF("null");
                        }
                        else{
                            output.writeUTF(d);
                        }

                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                   // Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    //        , ButtonType.OK);
                   // alert.showAndWait();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                   // Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                    //        , ButtonType.OK);
                   // alert.showAndWait();
                }
                break;

            case "Initialize3":
                try{
                    int ch = 0;
                    String check2 = "SELECT posts FROM Posts WHERE posts IS NOT NULL;";
                    Statement stmt2 = conn.createStatement();
                    ResultSet rs2 = stmt2.executeQuery(check2);
                    while(rs2.next())
                    {
                        ch++;
                    }
                    output.writeInt(ch);

                    check2 = "SELECT No_of_cand FROM settingsinfo WHERE id = 1;";
                    rs2 = stmt2.executeQuery(check2);
                    rs2.next();
                    int cz = rs2.getInt(1);
                    output.writeInt(cz);

                    String getDet1 = "SELECT * FROM $t;";
                    if(su == 1)
                        getDet1 = getDet1.replace("$t", "Post3");
                    else
                        getDet1 = getDet1.replace("$t", "Post"+(su*10+3));
                    rs2 = stmt2.executeQuery(getDet1);
                    String d;
                    int dd;

                    for (int x = 0; x < cz; x++) {
                        rs2.next();
                        d = rs2.getString(2);
                        output.writeUTF(d);
                        dd = rs2.getInt(3);
                        output.writeInt(dd);
                        d = rs2.getString(4);
                        output.writeUTF(d);
                        d = rs2.getString(5);
                        if(d == null) {
                            output.writeUTF("null");
                        }
                        else{
                            output.writeUTF(d);
                        }

                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                   // Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    //        , ButtonType.OK);
                    //alert.showAndWait();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    //Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                     //       , ButtonType.OK);
                    //alert.showAndWait();
                }
                break;

            case "Initialize4":
                try{
                    int ch = 0;
                    String check2 = "SELECT posts FROM Posts WHERE posts IS NOT NULL;";
                    Statement stmt2 = conn.createStatement();
                    ResultSet rs2 = stmt2.executeQuery(check2);
                    while(rs2.next())
                    {
                        ch++;
                    }
                    output.writeInt(ch);

                    check2 = "SELECT No_of_cand FROM settingsinfo WHERE id = 1;";
                    rs2 = stmt2.executeQuery(check2);
                    rs2.next();
                    int cz = rs2.getInt(1);
                    output.writeInt(cz);

                    String getDet1 = "SELECT * FROM $t;";
                    if(su == 1)
                        getDet1 = getDet1.replace("$t", "Post4");
                    else
                        getDet1 = getDet1.replace("$t", "Post"+(su*10+4));
                    rs2 = stmt2.executeQuery(getDet1);
                    String d;
                    int dd;

                    for (int x = 0; x < cz; x++) {
                        rs2.next();
                        d = rs2.getString(2);
                        output.writeUTF(d);
                        dd = rs2.getInt(3);
                        output.writeInt(dd);
                        d = rs2.getString(4);
                        output.writeUTF(d);
                        d = rs2.getString(5);
                        if(d == null) {
                            output.writeUTF("null");
                        }
                        else{
                            output.writeUTF(d);
                        }

                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                   // Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                   //         , ButtonType.OK);
                    //alert.showAndWait();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    //Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                   //         , ButtonType.OK);
                   // alert.showAndWait();
                }
                break;

            case "Vote1":

                try {
                    String idQ = input.readUTF();
                    Statement s = conn.createStatement();
                    ResultSet set = s.executeQuery(idQ);
                    set.next();
                    int Vid = set.getInt(1);

                    String VQ =  input.readUTF();
                    VQ = VQ.replace("?", Integer.toString(Vid));
                    set = s.executeQuery(VQ);
                    set.next();
                    int Votes = set.getInt(1);

                    String UpV = input.readUTF();
                    UpV = UpV.replace("$v", Integer.toString(Votes+1));
                    UpV = UpV.replace("?", Integer.toString(Vid));
                    s.executeUpdate(UpV);
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                    //Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                     //      , ButtonType.OK);
                    //alert.showAndWait();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    //Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                     //       , ButtonType.OK);
                    //.showAndWait();
                }
                break;

            case "Vote2":

                try {
                    String idQ = input.readUTF();
                    Statement s = conn.createStatement();
                    ResultSet set = s.executeQuery(idQ);
                    set.next();
                    int Vid = set.getInt(1);

                    String VQ =  input.readUTF();
                    VQ = VQ.replace("?", Integer.toString(Vid));
                    set = s.executeQuery(VQ);
                    set.next();
                    int Votes = set.getInt(1);

                    String UpV = input.readUTF();
                    UpV = UpV.replace("$v", Integer.toString(Votes+1));
                    UpV = UpV.replace("?", Integer.toString(Vid));
                    s.executeUpdate(UpV);

                    //vote2
                    idQ = input.readUTF();
                    s = conn.createStatement();
                    set = s.executeQuery(idQ);
                    set.next();
                    Vid = set.getInt(1);

                    VQ =  input.readUTF();
                    VQ = VQ.replace("?", Integer.toString(Vid));
                    set = s.executeQuery(VQ);
                    set.next();
                    Votes = set.getInt(1);

                    UpV = input.readUTF();
                    UpV = UpV.replace("$v", Integer.toString(Votes+1));
                    UpV = UpV.replace("?", Integer.toString(Vid));
                    s.executeUpdate(UpV);
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                    //Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    //        , ButtonType.OK);
                   //alert.showAndWait();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                   // Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                     //       , ButtonType.OK);
                    //alert.showAndWait();
                }
                break;

            case "Vote3":

                try {
                    String idQ = input.readUTF();
                    Statement s = conn.createStatement();
                    ResultSet set = s.executeQuery(idQ);
                    int Vid = set.getInt(1);

                    String VQ =  input.readUTF();
                    VQ = VQ.replace("?", Integer.toString(Vid));
                    set = s.executeQuery(VQ);
                    int Votes = set.getInt(1);

                    String UpV = input.readUTF();
                    UpV = UpV.replace("$v", Integer.toString(Votes+1));
                    UpV = UpV.replace("?", Integer.toString(Vid));
                    s.executeUpdate(UpV);

                    //votes2
                    idQ = input.readUTF();
                    s = conn.createStatement();
                    set = s.executeQuery(idQ);
                    set.next();
                    Vid = set.getInt(1);

                    VQ =  input.readUTF();
                    VQ = VQ.replace("?", Integer.toString(Vid));
                    set = s.executeQuery(VQ);
                    set.next();
                    Votes = set.getInt(1);

                    UpV = input.readUTF();
                    UpV = UpV.replace("$v", Integer.toString(Votes+1));
                    UpV = UpV.replace("?", Integer.toString(Vid));
                    s.executeUpdate(UpV);

                    //votes3
                    idQ = input.readUTF();
                    s = conn.createStatement();
                    set = s.executeQuery(idQ);
                    set.next();
                    Vid = set.getInt(1);

                    VQ =  input.readUTF();
                    VQ = VQ.replace("?", Integer.toString(Vid));
                    set = s.executeQuery(VQ);
                    set.next();
                    Votes = set.getInt(1);

                    UpV = input.readUTF();
                    UpV = UpV.replace("$v", Integer.toString(Votes+1));
                    UpV = UpV.replace("?", Integer.toString(Vid));
                    s.executeUpdate(UpV);
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                    //Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                        //    , ButtonType.OK);
                   // alert.showAndWait();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                   // Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                    //        , ButtonType.OK);
                    //alert.showAndWait();
                }
                break;

            case "Vote4":

                try {
                    String idQ = input.readUTF();
                    Statement s = conn.createStatement();
                    ResultSet set = s.executeQuery(idQ);
                    int Vid = set.getInt(1);

                    String VQ =  input.readUTF();
                    VQ = VQ.replace("?", Integer.toString(Vid));
                    set = s.executeQuery(VQ);
                    int Votes = set.getInt(1);

                    String UpV = input.readUTF();
                    UpV = UpV.replace("$v", Integer.toString(Votes+1));
                    UpV = UpV.replace("?", Integer.toString(Vid));
                    s.executeUpdate(UpV);

                    //votes2
                    idQ = input.readUTF();
                    s = conn.createStatement();
                    set = s.executeQuery(idQ);
                    set.next();
                    Vid = set.getInt(1);

                    VQ =  input.readUTF();
                    VQ = VQ.replace("?", Integer.toString(Vid));
                    set = s.executeQuery(VQ);
                    set.next();
                    Votes = set.getInt(1);

                    UpV = input.readUTF();
                    UpV = UpV.replace("$v", Integer.toString(Votes+1));
                    UpV = UpV.replace("?", Integer.toString(Vid));
                    s.executeUpdate(UpV);

                    //votes3
                    idQ = input.readUTF();
                    s = conn.createStatement();
                    set = s.executeQuery(idQ);
                    set.next();
                    Vid = set.getInt(1);

                    VQ =  input.readUTF();
                    VQ = VQ.replace("?", Integer.toString(Vid));
                    set = s.executeQuery(VQ);
                    set.next();
                    Votes = set.getInt(1);

                    UpV = input.readUTF();
                    UpV = UpV.replace("$v", Integer.toString(Votes+1));
                    UpV = UpV.replace("?", Integer.toString(Vid));
                    s.executeUpdate(UpV);

                    //votes4
                    idQ = input.readUTF();
                    s = conn.createStatement();
                    set = s.executeQuery(idQ);
                    set.next();
                    Vid = set.getInt(1);

                    VQ =  input.readUTF();
                    VQ = VQ.replace("?", Integer.toString(Vid));
                    set = s.executeQuery(VQ);
                    set.next();
                    Votes = set.getInt(1);

                    UpV = input.readUTF();
                    UpV = UpV.replace("$v", Integer.toString(Votes+1));
                    UpV = UpV.replace("?", Integer.toString(Vid));
                    s.executeUpdate(UpV);
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                   // Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    //    , ButtonType.OK);
                  //  alert.showAndWait();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    //Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                   //         , ButtonType.OK);
                   // alert.showAndWait();
                }
                break;

            case "time":

                try {
                    String getTime = "SELECT waiting_time from settingsinfo WHERE id = 1";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(getTime);
                    rs.next();
                    int s = rs.getInt(1);
                    output.writeInt(s);
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                  //  Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                   //         , ButtonType.OK);
                   // alert.showAndWait();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                   // Alert alert = new Alert(Alert.AlertType.ERROR, "Server error. Try restarting the app"
                      //      , ButtonType.OK);
                    //alert.showAndWait();
                }
                break;
                }
                try {
                    conn.close();
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                   // Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    //        , ButtonType.OK);
                   // alert.showAndWait();
                }
        }
        }