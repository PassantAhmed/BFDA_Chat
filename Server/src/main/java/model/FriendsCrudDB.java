/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.database.Database;
import model.database.DatabaseUserOperation;

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
    public List<beans.User> select(String strStatement)
    {
              ArrayList<beans.User> clients = new ArrayList<beans.User>();                    
            try
            {

                    stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(strStatement);
                    while (rs.next()) 
                    {
                        
                        
                        //int id,String name,String username,String email,String password,boolean gender,String country,LocalDate birthdate,String userPic,boolean status,String mode
                                  clients.add(new beans.User(rs.getInt("id"),rs.getString("name"),rs.getString("username"),rs.getString("email"),rs.getString("password"),rs.getBoolean("gender"),rs.getString("country"),rs.getDate(utilities.SqlParser.fromSqlToLocal("birthdate")),rs.getString("userPic"),rs.getBoolean("status"),rs.getString("mode")));
                                    //fawzy:hat3ml fill l kol el data enta lesa btkmlha sa7 ?
                                    //sobhy:kont h3mlha kolha bassant 2alt malhash lzma lma a3ml search tgbly 2l datakolha
                                    //fawzy: howa el fekra eny mesh ha3ml customize .. ana ma3rfsh emta hast5dm anhy data 
                                    //sobhy: ok ngeb 2l data kolha m4 ht2sar
                                    //fawzy: tamam 
                                    //sobhy: anma k strucure eh r2yk akml 3la kda
                                    //fawzy: la helw gedan 3aash 
                                    //sobhy: 7biby tslm y kber
                                    //fawzy: :D seeb el comments de b2a nb2a nslmha m3 el mashro3 hatb2a lazeza:D
                                     //sobhy: tmam :D 
                                    //fawzy: yala salam :D
                                     //sobhy: :D  2st2znk t2fl 2l team viewer :D salam
                    }
            }
            catch (SQLException ex)
            {
                        Logger.getLogger(DatabaseUserOperation.class.getName()).log(Level.SEVERE, null, ex);
            }
     
             return clients;
    }
    
}
