package model;

import clientInterfaces.ClientObj;

import java.rmi.RemoteException;
import java.util.Vector;

/**
 * Created by Muhammed Fawzy on 2/10/2018.
 */
public class ConnectionValidation {

    Vector<ClientObj> inActiveUsers = new Vector<>();

    public void checkActiveUsers()
    {


            try {
                Thread.sleep(1000);
                for(ClientObj obj : ClientServerRegisterImp.clientObject)
                {
                    try {
                        obj.getChatHandler().updateConnection();
                    } catch (RemoteException e) {

                        inActiveUsers.add(obj);
                        System.out.println("Adding to be Removed   "+e.toString());
                    }
                }
                removeInactiveFromList();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }





    }

    public void removeInactiveFromList()
    {
        for(ClientObj clientObj : inActiveUsers)
        {
            System.out.println("Removing Inactives");
            ClientServerRegisterImp.clientObject.remove(clientObj);
        }
        inActiveUsers.clear();
    }

}
