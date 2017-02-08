package server;

import model.Order;
import fpt.com.Product;
import view.ViewCustomer;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Henry on 05.02.2017.
 */
public class Warehouse {


    final static String adminUsername = "admin";
    final static String adminPassword = "admin";


    Order mainOrder = new Order();


    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
    }

    public Warehouse (){
        new TimeThread().start();

        try (ServerSocket server = new ServerSocket(6666);) {
            int connections = 0;
            // Timeout nach 1 Minute
            // server.setSoTimeout(60000);
            while (true) {
                try {
                    Socket socket = server.accept();
                    connections++;
                    new ConnectionThread(connections, socket, this).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public synchronized void addOrder(Order order){
        for(Product newProduct : order){
            Product sProd = null;
            if(( sProd = mainOrder.findProductById(newProduct.getId())) != null){
                sProd.setQuantity(sProd.getQuantity() + newProduct.getQuantity());
                sProd.setPrice(sProd.getPrice() + newProduct.getPrice());
            } else {
                mainOrder.add(copyProduct(newProduct));
            }
        }


        String recOrder = "----- Order eingegangen ----- \n";
        for(Product product : order){
            recOrder += product.getName() + "      " + product.getQuantity() + "      " + product.getPrice() + "€ \n";
        }
        recOrder += "\n ----- Orders gesamt ----- \n";
        for(Product product : mainOrder){
            recOrder += product.getName() + "      " + product.getQuantity() + "      " + product.getPrice() + "€ \n";
        }
        recOrder += "Gesamtanzahl: " + mainOrder.getQuantity() + " \n";
        recOrder += "Gesamtwert: " + mainOrder.getSum() + " \n";

        System.out.println(recOrder);
    }

    public Product copyProduct(Product product){
        model.Product p = new model.Product();
        p.setId(product.getId());
        p.setQuantity(product.getQuantity());
        p.setPrice(product.getPrice());
        p.setName(product.getName());
        return p;
    }
}


class TimeThread extends Thread {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public void run(){
        try (DatagramSocket socket =  new DatagramSocket(6667);) {
            while (true) {

                byte[] data = new byte[5];
                DatagramPacket datagramPacket = new DatagramPacket(data, data.length);

                try {
                    socket.receive(datagramPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                InetAddress address = datagramPacket.getAddress();
                int port = datagramPacket.getPort();
                //System.out.println("IP:" + address);
               // System.out.println("Port:" + port);

                int len = datagramPacket.getLength();
                byte[] receivedData = datagramPacket.getData();
                String da = new String(datagramPacket.getData());

                try (Scanner sc = new Scanner(da).useDelimiter(":")) {
                    String keyword = sc.next();

                    if (keyword.trim().equals("time")) {
                        Date dt = new Date();

                        String strDate = simpleDateFormat.format(dt);
                        byte[] myDate = strDate.getBytes();

                        datagramPacket = new DatagramPacket(myDate, myDate.length,
                                address, port);

                        socket.send(datagramPacket);

                    } else {
                        byte[] myDate = null;
                        myDate = new String("Command unknown").getBytes();


                        datagramPacket = new DatagramPacket(myDate, myDate.length,
                                address, port);

                        socket.send(datagramPacket);
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

class ConnectionThread extends Thread {
    private int name;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Warehouse warehouse;
    private IncomingThread incomingThread;
    private OutgoingThread outgoingThread;

    public ConnectionThread(int name, Socket socket, Warehouse warehouse) {
        System.out.println("New Connection");
        this.name = name;
        this.socket = socket;
        this.warehouse = warehouse;
    }


    public void run() {
        incomingThread = new IncomingThread(socket,warehouse, this);
        outgoingThread = new OutgoingThread(socket,warehouse,this);
        outgoingThread.start();
        incomingThread.start();
    }

    public synchronized void sendMessage(String msg){
        outgoingThread.sendToClient(msg);
    }
}



class IncomingThread extends Thread {
    private ObjectInputStream inputStream;
    private Warehouse warehouse;
    private ConnectionThread connectionThread;

    public IncomingThread (Socket socket, Warehouse warehouse, ConnectionThread connectionThread){
        this.warehouse = warehouse;
        this.connectionThread = connectionThread;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            Object received;
            while ((received = inputStream.readObject()) != null) {
                List<Object> x = (ArrayList<Object>) received;

                String authentification = (String)x.get(0);
                if(authentification.equals("AUTH:" + Warehouse.adminUsername + ":" + Warehouse.adminPassword)){
                    Order receivedOrder = (Order)x.get(1);
                    warehouse.addOrder(receivedOrder);
                    connectionThread.sendMessage(createOrderMessage(receivedOrder));
                } else {
                    connectionThread.sendMessage("Falscher Benutzername oder falsches Passwort");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            inputStream.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private String createOrderMessage(Order order){
        String msg = "------ Order eingegangen ------- \n";
        for(Product product : order){
            msg += product.getName() + "      " + product.getQuantity() + "      " + product.getPrice() + "€ \n";
        }
        return msg;
    }

}

class OutgoingThread extends Thread {
    private ObjectOutputStream outputStream;
    private Warehouse warehouse;
    private ConnectionThread connectionThread;

    public OutgoingThread (Socket socket, Warehouse warehouse,ConnectionThread connectionThread){
        this.connectionThread = connectionThread;
        this.warehouse = warehouse;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        while(true){

        }
    }

    public synchronized void sendToClient(Object message){
        try{
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
