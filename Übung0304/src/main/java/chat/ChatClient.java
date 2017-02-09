package chat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Leona on 05.02.2017.
 */

public class ChatClient extends UnicastRemoteObject implements ClientService {

    String name;
    ChatService chatServer;

    public ChatClient(String name) throws RemoteException, MalformedURLException, NotBoundException{
        super();
        this.name = name;
        Naming.rebind("//127.0.0.1:1099/" + name, this);
        chatServer = (ChatService) Naming.lookup("//127.0.0.1:1099/chatServer");
        chatServer.login(name);
    }

    @Override
    public void send(String s) throws RemoteException {
        chatServer.send("[" + getName() + "] " + s);
    }

    public void displayMessage(String s) throws  RemoteException{
        System.out.println(s);
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }
}
