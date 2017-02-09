package chat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Leona on 05.02.2017.
 */

public interface ChatService extends Remote {

    void login(String s)throws RemoteException;

    void logout(String s) throws RemoteException;

    void send(String s) throws RemoteException;

    List<String> getUserList() throws RemoteException;

}
