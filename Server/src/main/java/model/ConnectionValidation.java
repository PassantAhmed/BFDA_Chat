package model;


import client.interfaces.ClientObj;

import java.rmi.RemoteException;
import java.util.Vector;

/**
 * Created by Muhammed Fawzy on 2/10/2018.
 */
public class ConnectionValidation {

    Vector<String> inActiveUsers = new Vector<>();

    public synchronized void checkActiveUsers()
    {


            try {
                Thread.sleep(1000);
                for(String key : ClientServerRegisterImp.clientObjHashMap.keySet())
                {
                    try {
                        ClientServerRegisterImp.clientObjHashMap.get(key).getChatHandler().updateConnection();
                    } catch (RemoteException e) {

                        inActiveUsers.add(key);
                        System.out.println("Adding to be Removed   "+e.toString());
                    }
                }
                removeInactiveFromList();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public synchronized void removeInactiveFromList()
    {
        for(String clientObj : inActiveUsers)
        {
            System.out.println("Removing Inactives");
            ClientServerRegisterImp.clientObjHashMap.remove(clientObj);
        }
        inActiveUsers.clear();
    }

    public synchronized void sendCloseNotify() throws RemoteException {
        for(String chatMember :  ClientServerRegisterImp.clientObjHashMap.keySet())
        {
            ClientServerRegisterImp.clientObjHashMap.get(chatMember).getConnectionValidation().closeRequest();
        }
    }

}
