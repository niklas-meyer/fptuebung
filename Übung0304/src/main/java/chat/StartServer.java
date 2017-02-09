package chat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

/**
 * Created by Henry on 09.02.2017.
 */
public class StartServer {

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new NullSecurityManager());
        }

        LocateRegistry.createRegistry(1099);

        ChatServer chatServer = new ChatServer();
        Naming.rebind("//127.0.0.1:1099/chatServer", chatServer);


        System.out.println("Registry started");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        chatServer.send("Hallo");
    }

    public static void startRegistry(){
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new NullSecurityManager());
            }

            LocateRegistry.createRegistry(1099);

            ChatServer chatServer = new ChatServer();
            Naming.rebind("//127.0.0.1:1099/chatServer", chatServer);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
