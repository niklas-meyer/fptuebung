package chat;

/**
 * Created by Henry on 09.02.2017.
 */

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by Henry on 09.02.2017.
 */
public class StartClient {

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Name eingeben");

        String x = scanner.nextLine();
        ClientService chatClient = new ChatClient(x);

        while(true){
            x = scanner.nextLine();
            chatClient.send(x);
        }

    }
}