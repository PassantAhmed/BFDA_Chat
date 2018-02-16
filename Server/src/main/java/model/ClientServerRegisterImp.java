package model;

import beans.User;
import client.interfaces.ClientObj;
import server.interfaces.ClientServerRegister;
import model.database.DatabaseUserOperation;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientServerRegisterImp extends UnicastRemoteObject implements ClientServerRegister, Serializable {

//    public static Vector<ClientObj> clientObject = new Vector<ClientObj>();
    public static HashMap<String , ClientObj> clientObjHashMap = new HashMap<>();

    protected ClientServerRegisterImp() throws RemoteException {

    }

    @Override
    public void registerUser(String username , ClientObj clientObject) {
        System.out.print("Registered");
        ClientServerRegisterImp.clientObjHashMap.put(username , clientObject);
    }

    @Override
    public void unRegisterUser(String username ) {
        ClientServerRegisterImp.clientObjHashMap.remove(username);
    }


    @Override
    public User userLogin(String username, String password) throws RemoteException {
        UserLogin login = new UserLogin(username , password);
        User user = null;
        try {
            if(login.login())
            {
                user = login.getResultUser();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }

    @Override
    public Boolean newUserRegisteration(User user) throws RemoteException {
        boolean registered= false;
        try {
            
            String username = user.getUsername();
            DatabaseUserOperation DBO = new DatabaseUserOperation();  
            String  email = user.getEmail();
            
            if(DBO.isEmailExist(email) == true && DBO.isUsernameExist(username)== true){
                // email or username is exists
                registered = false;
            }else{
                // new user then register
                if(DBO.clientRegister(user)==true)
                    registered = true;
                
            }
            
            return registered;
        } catch (SQLException ex) {
            Logger.getLogger(ClientServerRegisterImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
