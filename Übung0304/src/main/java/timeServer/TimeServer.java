package timeServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Henry on 05.02.2017.
 */
public class TimeServer {

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    public  static void main(String[] args){
        System.out.println("Server started");
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
                System.out.println("IP:" + address);
                System.out.println("Port:" + port);

                int len = datagramPacket.getLength();
                byte[] receivedData = datagramPacket.getData();
                String da = new String(datagramPacket.getData());
                System.out.println("Received:" + da);

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
