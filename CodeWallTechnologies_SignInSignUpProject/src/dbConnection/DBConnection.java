package dbConnection;
import java.sql.*;
import java.util.*;

public class DBConnection {
    private static String url = "jdbc:mysql://localhost:3306/loginuserdata";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String username = "root";
    private static String password = "";
    private static Connection con;
    String query1;
    ResultSet rs;
    Statement stmt;

    //Connection with Database
    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);
                //System.out.println("Connection Successful");
            } catch (SQLException ex) {
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found.");
        }
        return con;
    }


    //Check Email and Password from loginscreen with Database
    public boolean CheckLoginData(String email, String password){
        rs  = null ;
        try{
            stmt =getConnection().createStatement(); //getConnection method call, Statement create
            rs = stmt.executeQuery("select * from userdata where email='"+email+"' AND password='"+password+"'");
            if(rs.next())
               return true; //If Login data is correct, return true
            else
                return false;
        }catch(SQLException ex){
            return false;
        }
    }


    //Check Duplicate with email
    public boolean IsDuplicate(String[] email){
        query1="select * from userdata where email='"+email[2]+"'";
        try{
            Statement stmt=getConnection().createStatement();
            rs=stmt.executeQuery(query1);
            if(rs.next())
                return true;
            else
                return false;
        }catch(SQLException e){
            return false;
        }
    }


    //Create New Data in Database
    public boolean InsertData(String[] fulldata){
        query1="insert into userdata(username,phone,email,password) values('"+fulldata[0]+"','"+fulldata[1]+"','"+fulldata[2]+"','"+fulldata[3]+"')";
        try {
            stmt=getConnection().createStatement();
            if(stmt.executeUpdate(query1)==1)
                return true;
            else
                return false;
        } catch (SQLException e) {
            return false;
        }
    }

    //Update New Password
    public boolean updatePassword(String confirmpassword, String email ){
        query1="update userdata set password='"+confirmpassword+"' WHERE email='"+email+"'";
        try {
            stmt=getConnection().createStatement();
            if(stmt.executeUpdate(query1)==1)
                return true;
            else
                return false;
        } catch (SQLException e) {
            return false;
           //throw new RuntimeException(e);
        }
    }
}
