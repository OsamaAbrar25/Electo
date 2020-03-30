package sample;

import com.mysql.cj.jdbc.MysqlDataSource;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    public static MysqlDataSource dataSource;

    static void InitializeData()
    {
         dataSource = new MysqlDataSource();
         dataSource.setUser("root");
         dataSource.setPassword("zxcvbnm@123");

        try{
        Connection con = Main.getConnection();
        Statement existence = con.createStatement();
        existence.execute("CREATE DATABASE IF NOT EXISTS votingdata");

        Statement st = con.createStatement();
        st.execute("USE votingdata");

        dataSource.setDatabaseName("votingdata");

        Statement existence2 = con.createStatement();
        existence2.execute("CREATE TABLE IF NOT EXISTS LoginInfo(Username VARCHAR(100), Password" +
                " VARCHAR(100), Hint VARCHAR(100))");

        Statement data = con.createStatement();
        ResultSet data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM LoginInfo)");
        data1.next();
        int i = data1.getInt(1);
        if (i == 0) {
            String query = "INSERT INTO LoginInfo(Username, Password) " +
                    "   VALUES('abc','123')";
            Statement fill = con.createStatement();
            fill.executeUpdate(query);
        }

            existence2.execute("CREATE TABLE IF NOT EXISTS settingsinfo(id INT, Theme INT, Name_of_posts VARCHAR(200)," +
                    "No_of_cand INT, waiting_time INT, Background VARCHAR(1000))");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM settingsinfo)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query2 = "UPDATE settingsinfo SET No_of_cand = 4 WHERE id = 1";
                String query3 = "UPDATE settingsinfo SET waiting_time = 5 WHERE id = 1";
                String query1 = "INSERT INTO settingsinfo(id)" +
                        "VALUES(1)," +
                        "(2)," +
                        "(3)," +
                        "(4);";
                data.executeUpdate(query1);
                data.executeUpdate(query2);
                data.executeUpdate(query3);
            }

            existence2.execute("CREATE TABLE IF NOT EXISTS Posts(id INT, posts varchar(50))");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM Posts)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query = "INSERT INTO Posts(id)" +
                        "VALUES(1)," +
                        "(2)," +
                        "(3)," +
                        "(4);";
                data.executeUpdate(query);
            }

            existence2.execute("CREATE TABLE IF NOT EXISTS whichCat(id INT, Category INT)");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM whichCat)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query = "INSERT INTO whichCat(id, Category)" +
                        "VALUES(1, 1)";
                data.execute(query);
            }

            existence2.execute("CREATE TABLE IF NOT EXISTS categories(id INT, categories varchar(50))");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM categories)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query = "INSERT INTO categories(id)" +
                        "VALUES(1)," +
                        "(2)," +
                        "(3)," +
                        "(4);";
                data.execute(query);
            }

            existence2.execute("CREATE TABLE IF NOT EXISTS Post1(id INT, Name VARCHAR(100), Class VARCHAR(20)," +
                    " Sec VARCHAR(10), Imageaddress VARCHAR(1000), Votes INT)");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM Post1)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query = "INSERT INTO Post1(id, Votes)" +
                        "VALUES(1, 0)," +
                        "(2, 0)," +
                        "(3, 0)," +
                        "(4, 0)," +
                        "(5, 0)," +
                        "(6, 0);";
                data.execute(query);
            }


            existence2.execute("CREATE TABLE IF NOT EXISTS Post21(id INT, Name VARCHAR(100), Class VARCHAR(20)," +
                    " Sec VARCHAR(10), Imageaddress VARCHAR(1000), Votes INT)");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM Post21)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query = "INSERT INTO Post21(id, Votes)" +
                        "VALUES(1, 0)," +
                        "(2, 0)," +
                        "(3, 0)," +
                        "(4, 0)," +
                        "(5, 0)," +
                        "(6, 0);";
                data.execute(query);
            }

            existence2.execute("CREATE TABLE IF NOT EXISTS Post31(id INT, Name VARCHAR(100), Class VARCHAR(20)," +
                    " Sec VARCHAR(10), Imageaddress VARCHAR(1000), Votes INT)");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM Post31)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query = "INSERT INTO Post31(id, Votes)" +
                        "VALUES(1, 0)," +
                        "(2, 0)," +
                        "(3, 0)," +
                        "(4, 0)," +
                        "(5, 0)," +
                        "(6, 0);";
                data.execute(query);
            }

            existence2.execute("CREATE TABLE IF NOT EXISTS Post41(id INT, Name VARCHAR(100), Class VARCHAR(20)," +
                    " Sec VARCHAR(10), Imageaddress VARCHAR(1000), Votes INT)");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM Post41)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query = "INSERT INTO Post41(id, Votes)" +
                        "VALUES(1, 0)," +
                        "(2, 0)," +
                        "(3, 0)," +
                        "(4, 0)," +
                        "(5, 0)," +
                        "(6, 0);";
                data.execute(query);
            }

            existence2.execute("CREATE TABLE IF NOT EXISTS Post2(id INT, Name VARCHAR(100), Class VARCHAR(20)," +
                    " Sec VARCHAR(10), Imageaddress VARCHAR(1000), Votes INT)");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM Post2)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query = "INSERT INTO Post2(id, Votes)" +
                        "VALUES(1, 0)," +
                        "(2, 0)," +
                        "(3, 0)," +
                        "(4, 0)," +
                        "(5, 0)," +
                        "(6, 0);";
                data.execute(query);
            }

            existence2.execute("CREATE TABLE IF NOT EXISTS Post22(id INT, Name VARCHAR(100), Class VARCHAR(20)," +
                    " Sec VARCHAR(10), Imageaddress VARCHAR(1000), Votes INT)");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM Post22)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query = "INSERT INTO Post22(id, Votes)" +
                        "VALUES(1, 0)," +
                        "(2, 0)," +
                        "(3, 0)," +
                        "(4, 0)," +
                        "(5, 0)," +
                        "(6, 0);";
                data.execute(query);
            }

            existence2.execute("CREATE TABLE IF NOT EXISTS Post32(id INT, Name VARCHAR(100), Class VARCHAR(20)," +
                    " Sec VARCHAR(10), Imageaddress VARCHAR(1000), Votes INT)");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM Post32)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query = "INSERT INTO Post32(id, Votes)" +
                        "VALUES(1, 0)," +
                        "(2, 0)," +
                        "(3, 0)," +
                        "(4, 0)," +
                        "(5, 0)," +
                        "(6, 0);";
                data.execute(query);
            }

            existence2.execute("CREATE TABLE IF NOT EXISTS Post42(id INT, Name VARCHAR(100), Class VARCHAR(20)," +
                    " Sec VARCHAR(10), Imageaddress VARCHAR(1000), Votes INT)");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM Post42)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query = "INSERT INTO Post42(id, Votes)" +
                        "VALUES(1, 0)," +
                        "(2, 0)," +
                        "(3, 0)," +
                        "(4, 0)," +
                        "(5, 0)," +
                        "(6, 0);";
                data.execute(query);
            }

            existence2.execute("CREATE TABLE IF NOT EXISTS Post3(id INT, Name VARCHAR(100), Class VARCHAR(20)," +
                    " Sec VARCHAR(10), Imageaddress VARCHAR(1000), Votes INT)");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM Post3)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query = "INSERT INTO Post3(id, Votes)" +
                        "VALUES(1, 0)," +
                        "(2, 0)," +
                        "(3, 0)," +
                        "(4, 0)," +
                        "(5, 0)," +
                        "(6, 0);";
                data.execute(query);
            }

            existence2.execute("CREATE TABLE IF NOT EXISTS Post23(id INT, Name VARCHAR(100), Class VARCHAR(20)," +
                    " Sec VARCHAR(10), Imageaddress VARCHAR(1000), Votes INT)");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM Post23)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query = "INSERT INTO Post23(id, Votes)" +
                        "VALUES(1, 0)," +
                        "(2, 0)," +
                        "(3, 0)," +
                        "(4, 0)," +
                        "(5, 0)," +
                        "(6, 0);";
                data.execute(query);
            }

            existence2.execute("CREATE TABLE IF NOT EXISTS Post33(id INT, Name VARCHAR(100), Class VARCHAR(20)," +
                    " Sec VARCHAR(10), Imageaddress VARCHAR(1000), Votes INT)");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM Post33)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query = "INSERT INTO Post33(id, Votes)" +
                        "VALUES(1, 0)," +
                        "(2, 0)," +
                        "(3, 0)," +
                        "(4, 0)," +
                        "(5, 0)," +
                        "(6, 0);";
                data.execute(query);
            }

            existence2.execute("CREATE TABLE IF NOT EXISTS Post43(id INT, Name VARCHAR(100), Class VARCHAR(20)," +
                    " Sec VARCHAR(10), Imageaddress VARCHAR(1000), Votes INT)");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM Post43)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query = "INSERT INTO Post43(id, Votes)" +
                        "VALUES(1, 0)," +
                        "(2, 0)," +
                        "(3, 0)," +
                        "(4, 0)," +
                        "(5, 0)," +
                        "(6, 0);";
                data.execute(query);
            }

            existence2.execute("CREATE TABLE IF NOT EXISTS Post4(id INT, Name VARCHAR(100), Class VARCHAR(20)," +
                    " Sec VARCHAR(10), Imageaddress VARCHAR(1000), Votes INT)");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM Post4)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query = "INSERT INTO Post4(id, Votes)" +
                        "VALUES(1, 0)," +
                        "(2, 0)," +
                        "(3, 0)," +
                        "(4, 0)," +
                        "(5, 0)," +
                        "(6, 0);";
                data.execute(query);
            }

            existence2.execute("CREATE TABLE IF NOT EXISTS Post24(id INT, Name VARCHAR(100), Class VARCHAR(20)," +
                    " Sec VARCHAR(10), Imageaddress VARCHAR(1000), Votes INT)");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM Post24)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query = "INSERT INTO Post24(id, Votes)" +
                        "VALUES(1, 0)," +
                        "(2, 0)," +
                        "(3, 0)," +
                        "(4, 0)," +
                        "(5, 0)," +
                        "(6, 0);";
                data.execute(query);
            }

            existence2.execute("CREATE TABLE IF NOT EXISTS Post34(id INT, Name VARCHAR(100), Class VARCHAR(20)," +
                    " Sec VARCHAR(10), Imageaddress VARCHAR(1000), Votes INT)");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM Post34)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0)
            {
                String query = "INSERT INTO Post34(id, Votes)" +
                        "VALUES(1, 0)," +
                        "(2, 0)," +
                        "(3, 0)," +
                        "(4, 0)," +
                        "(5, 0)," +
                        "(6, 0);";
                data.execute(query);
            }

            existence2.execute("CREATE TABLE IF NOT EXISTS Post44(id INT, Name VARCHAR(100), Class VARCHAR(20)," +
                    " Sec VARCHAR(10), Imageaddress VARCHAR(1000), Votes INT)");
            data1 = data.executeQuery("SELECT EXISTS(SELECT 1 FROM Post44)");
            data1.next();
            i = data1.getInt(1);
            if(i == 0) {
                String query = "INSERT INTO Post44(id, Votes)" +
                        "VALUES(1, 0)," +
                        "(2, 0)," +
                        "(3, 0)," +
                        "(4, 0)," +
                        "(5, 0)," +
                        "(6, 0);";
                data.execute(query);
            }
            con.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Try reinstalling Mysql"
                    , ButtonType.OK);
            alert.showAndWait();
        }

    }
}
