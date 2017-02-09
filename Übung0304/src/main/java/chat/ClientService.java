package chat;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Leona on 05.02.2017.
 */

public interface ClientService extends Remote {

    void send(String s) throws RemoteException;

    void displayMessage(String s) throws RemoteException;

    String getName() throws RemoteException;



}
