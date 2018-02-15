/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database;

import beans.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import server.interfaces.FriendsDbOperations;

/**
 *
 * @author ahmedelgawesh
 */
public class FriendsDbOperationsImp extends UnicastRemoteObject implements FriendsDbOperations
{

    FriendsCrudDB friendsCrud;

    public  FriendsDbOperationsImp() throws RemoteException, SQLException
    {
        friendsCrud = new FriendsCrudDB();

    }

    /********************  search for user to add *******************************/
    @Override
    public ArrayList<beans.User> searchForUser(String usrInformation)

    {
        String selectStatement = "select id,name,username,email,password,gender,country,birthdate,userPic,status,mode from user where id= '"+usrInformation+"' or username= '"+usrInformation+"'";
        return (ArrayList<User>) friendsCrud.select(selectStatement);
    }




    /********************** send to user a friend request  **********************************/
    @Override
    public boolean sendFriendRequest(int myId,int userId)
    {

        String sqlStatm= "INSERT INTO Friend (userid,friendid,requestflag) VALUES ('"+myId+",'"+userId+"',false)";
        boolean checkOperation = friendsCrud.insert(sqlStatm);
        return checkOperation;
    }


    /******************************* accept a friend request ******************************/
    @Override
    public boolean approveFriendRequset(int myId,int userId)
    {
        //user update for the flag to true where id
        String sqlStatm = "update Friend set requestflag=true where (userid,friendid) VALUES ('"+myId+"','"+userId+"')";
        boolean checkOperation=friendsCrud.update(sqlStatm);
        return checkOperation;
    }

    /****************** don't accept friend request  *******************************/
    @Override
    public boolean refuseFriendRequest(int myId,int userId)
    {
        //use delete statment
        String sqlStatm = "delete * from Friend  where (userid,friendid) VALUES ('"+myId+"','"+userId+"')";
        boolean checkOperation= friendsCrud.delete(sqlStatm);
        return checkOperation;


    }


    /********************* select all your friends **********************************/
    @Override
    public ArrayList<beans.User> retrieveAllFriends(int myId)
    {
        //as select but from friend table

        String selectStatement = "select id,name,username,email,password,gender,country,birthdate,userPic,status,mode from user where id='"+myId+"' and friend.user_id=user.id";
        return (ArrayList<User>) friendsCrud.select(selectStatement);


    }


}
