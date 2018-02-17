/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database;

import beans.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;


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
    public ArrayList<User> searchForUser(String usrInformation)

    {
        String selectStatement = "select id,name,username,email,password,gender,country,birthdate,userPic,status,mode from user where id= '"+usrInformation+"' or username= '"+usrInformation+"'";
        return (ArrayList<User>) friendsCrud.select(selectStatement);
    }




    /********************** send to user a friend request  **********************************/
    @Override
    public boolean sendFriendRequest(int myId,int userId)
    {

        String sqlStatm= "INSERT INTO Friend (userid,friendid,requestflag) VALUES ('"+myId+",'"+userId+"',0)";
        boolean checkOperation = friendsCrud.insert(sqlStatm);
        return checkOperation;
    }


    /******************************* accept a friend request ******************************/
    @Override
    public boolean approveFriendRequset(int myId,int userId)
    {
        //user update for the flag to true where id
        String sqlStatm = "update Friend set requestflag=true where (userid,friendid) VALUES ('"+myId+"','"+userId+"')";
        boolean checkOperation = friendsCrud.update(sqlStatm);
        sqlStatm= "INSERT INTO Friend (userid,friendid,requestflag) VALUES ('"+userId+",'"+myId+"',1)";
        checkOperation = friendsCrud.update(sqlStatm);

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
    public ArrayList<User> retrieveAllFriends(int myId)
    {
        //as select but from friend table
        String selectStatement = "select User.id , name , username , email , password , gender , country , birthdate , userPicture , statusFlag , statusMode " +
                "from User , Friend " +
                "where Friend.User_id = "+myId+" and Friend.RequestFlag = 1 " +
                "and User.id = Friend.Friend_id";
        return (ArrayList<User>) friendsCrud.select(selectStatement);
    }

    @Override
    public ArrayList<User> getAllFriendRequests(String myId) throws RemoteException {
//
        String selectStatement = "select User.id , name , username , email , password , gender , country , " +
                "birthdate , userPicture , statusFlag , statusMode \n" +
                "from User , Friend " +
                "where Friend.User_id = "+myId+" and Friend.RequestFlag = 0 " +
                "and User.id = Friend.Friend_id";
        return (ArrayList<User>) friendsCrud.select(selectStatement);
    }

    @Override
    public boolean isExist(int myId, int userId) throws RemoteException 
    {
            String selectStatment="select  user_id from friend whrere  (user_id,friend_id) VALUES ('"+myId+"','"+userId+"')";
            
            return friendsCrud.select(selectStatment,"");
            
    }

        /*********************check if user isExist as a friend **********************************/
    


}
