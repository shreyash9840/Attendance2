import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    public static java.sql.Connection connect(){
        java.sql.Connection conn = null;
        java.lang.String url= "jdbc:sqlite:src/main/resources/database/Attendance_MS_DB.db";

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static final String user_table = "User";
    public static final String class_table = "Classs";

    public static final String attendence_table = "Attendance";
    public static final String classname = "classname";
    public static final String id = "id";
    public static final String  username = "username";
    public static final String password = "password";

    public static void insert_user(Connection conn, User u) {

        String sql = "INSERT INTO " + user_table + " (username,password)" + "VALUES (?,?)";


        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, u.getUsername());
            statement.setString(2, u.getPassword());
            statement.execute();
            statement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
    public static void insert_class(Connection conn, Classs c) {

        String sql = "INSERT INTO " + class_table + " (classname)" + "VALUES (?)";


        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, c.getClassname());
            statement.execute();
            statement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
    public static void insert_attendence(Connection conn,User u ,Classs c){
        String sql = "INSERT INTO " + attendence_table + " (userid,classid)" + "VALUES (?,?)";


        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, u.getId());
            statement.setInt(2, c.getId());
            statement.execute();
            statement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<User> display_user(Connection conn) {

        String sql = "SELECT * FROM " + user_table;

        List<User> user_list = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int u_id = rs.getInt(id);
                String u_username = rs.getString(username);
                String u_password = rs.getString(password);
                User user_data = new User(u_id, u_username,u_password);
                user_list.add(user_data);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user_list;
    }

    public static List<Classs> display_class(Connection conn) {

        String sql = "SELECT * FROM " + class_table;

        List<Classs> class_list = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int c_id = rs.getInt(id);
                String c_classname = rs.getString(classname);
                Classs class_data = new Classs(c_id, c_classname);
                class_list.add(class_data);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return class_list;
    }
}
