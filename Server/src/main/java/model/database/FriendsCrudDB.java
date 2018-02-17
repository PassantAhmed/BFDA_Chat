/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database;

import beans.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmedelgawesh
 */
public class FriendsCrudDB
{
    Database dbClass;
    Connection conn;
    Statement stmt = null;

    public  FriendsCrudDB() throws SQLException
    {

        try
        {
            dbClass = Database.getInstance();
            conn = dbClass.getConnection();
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(DatabaseUserOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    /***********************************  insert **********************************************************/

    public  boolean insert (String sqlStatm)
    {

        try
        {
            //Create statement
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStatm);
            System.out.println("Records inserted");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }

        try
        {
            stmt.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(FriendsCrudDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }






    /***********************************  delete **********************************************************/

    public  boolean delete (String sqlStatm)
    {

        try
        {
            //Create statement
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStatm);
            System.out.println("Records deleted");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }

        try
        {
            stmt.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(FriendsCrudDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }



    /***********************************  update **********************************************************/

    public  boolean update (String sqlStatm)
    {

        try
        {
            //Create statement
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStatm);
            System.out.println("Records updated");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }

        try
        {
            stmt.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(FriendsCrudDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }





    /************************************ select *******************************************************/
    public List<User> select(String strStatement)
    {
        ArrayList<User> clients = new ArrayList<beans.User>();
        try
        {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(strStatement);
            while (rs.next())
            {


                //int id,String name,String username,String email,String password,boolean gender,String country,LocalDate birthdate,String userPic,boolean status,String mode
                clients.add(new beans.User(rs.getInt("id"),rs.getString("name"),rs.getString("username"),
                        rs.getString("email"),rs.getString("password"),rs.getBoolean("gender"),
                        rs.getString("country"),utilities.SqlParser.fromSqlToLocalDate(rs.getDate("birthdate")),
                        rs.getString("userPicture"),rs.getBoolean("statusFlag"),rs.getString("statusMode")));


            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseUserOperation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clients;
    }

        /************************************ select one user  to check is exist  in your friends list*******************************************************/

    public boolean select(String strStatement,String x)
    {
        int count=0;
        try
        {
            
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(strStatement);
                while (rs.next())
                {
                        count++;
                }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseUserOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(count == 0)
            return false;
         else
             return true;
    
    }

    
}