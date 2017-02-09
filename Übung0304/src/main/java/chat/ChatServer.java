package chat;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Leona on 05.02.2017.
 */

public class ChatServer extends UnicastRemoteObject implements ChatService {

    private List<String> userList = new ArrayList<>();
    private Map<String, ClientService> users = new HashMap<String, ClientService>();

    public ChatServer() throws RemoteException {
        super();
    }

    @Override
    public void login(String s) throws RemoteException{
        userList.add(s);
        try {
            ClientService chatClient = (ClientService) Naming.lookup("//127.0.0.1:1099/" + s);
            users.put(s, chatClient);
        } catch (Exception e){
            e.printStackTrace();
        }
         String msg = "User [" + s + "] joined. \n";
        msg += "Current Users: \n";
        for(String name : users.keySet()){
            msg += " [" + name + "] \n";
        }
        send(msg);

    }

    @Override
    public void logout(String s) throws RemoteException{
        userList.remove(s);
        users.remove(s);
        String msg = "User [" + s + "] disconnected. \n";
        msg += "Current Users: \n";
        for(String name : users.keySet()){
            msg += " [" + name + "] \n";
        }
        send(msg);
    }

    @Override
    public void send(String s)throws RemoteException {
        for(ClientService c : users.values()){
            c.displayMessage(s);
        }
    }

    @Override
    public List<String> getUserList()throws RemoteException {
        return userList;
    }

}
