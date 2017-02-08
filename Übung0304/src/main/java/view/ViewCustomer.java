package view;

import controller.ControllerCustomer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.util.converter.IntegerStringConverter;
import model.Product;
import model.Order;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ViewCustomer extends BorderPane {

    private ControllerCustomer controllerCustomer;
    private TableView<Product> table = new TableView<Product>();
    TableColumn<Product, Integer> buyColum;
    TableColumn<Product, String> nameColum;
    private Button buyButton = new Button("Buy");
    private Button orderButton = new Button("Add To Order");
    private Button deleteOrder = new Button("Delete Order");
    private ListView<Product> orderList = new ListView<Product>();
    private  Label timeLabel;
    private TextArea infoBox;

    private OutgoingThread outgoingThread;
    private IncomingThread incomingThread;
    private TimeThread timeThread;
    private Socket tcpSocket = null;

    public static void main(String[] args){

    }

    public ViewCustomer(ControllerCustomer controllerCustomer) {
        this.controllerCustomer = controllerCustomer;
        // Start a thread for the date
        InetAddress ia = null;
        try {
            ia = InetAddress.getByName("localhost");
            tcpSocket = new Socket(ia, 6666);
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }


        timeThread = new TimeThread(ia, 6667, this);
        timeThread.start();

        outgoingThread = new OutgoingThread(tcpSocket, this);
        outgoingThread.start();
        incomingThread = new IncomingThread(tcpSocket, controllerCustomer);
        incomingThread.start();

        //-----

        nameColum = new TableColumn<Product, String>("Name");
        nameColum.setMinWidth(100);

        timeLabel = new Label("TIME");


        table.setEditable(true);
       
        nameColum.setCellValueFactory(data -> data.getValue().nameProperty());

        nameColum.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColum.setOnEditCommit(new EventHandler<CellEditEvent<Product, String>>() {
            @Override
            public void handle(CellEditEvent<Product, String> t) {
                ((Product) t.getTableView().getItems()
                        .get(t.getTablePosition().getRow())).setName(t
                        .getNewValue());
            }
        });

        TableColumn<Product, Number> priceColum = new TableColumn<Product, Number>("Price"); 
        priceColum.setMinWidth(80);

        
        priceColum.setCellValueFactory(data -> data.getValue().priceProperty());

        TableColumn<Product, Number> quantityColum = new TableColumn<Product, Number>("Quantity");
        quantityColum.setMinWidth(80);
       
        //hier wird, die Liste in der Customer übersicht aktualsiert
        
       quantityColum.setCellValueFactory(data -> data.getValue().quantityProperty());

        buyColum = new TableColumn<Product, Integer>("Buy Count");
        buyColum.setMinWidth(80);


        //hier wird, die Liste in der Customer übersicht aktualsiert

        buyColum.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


        table.getColumns().addAll(nameColum, priceColum, quantityColum, buyColum);

        infoBox = new TextArea();
        infoBox.setEditable(false);
        infoBox.setText("");

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(timeLabel,table,
                                    buyButton, orderButton,deleteOrder,
                                    infoBox);

        VBox.setVgrow(table, Priority.ALWAYS);

        setRight(vbox);

        orderList.setPrefWidth(400);
        setCenter(orderList);

    }

    public void setList(ObservableList<Product> liste) {
        table.setItems(liste);
    }

    public void setTime(String time){
        timeLabel.setText(time);
    }

    public void addEventHandler(EventHandler<ActionEvent> eventHandler) {
        buyButton.addEventHandler(ActionEvent.ACTION, eventHandler);
        orderButton.addEventHandler(ActionEvent.ACTION, eventHandler);
        deleteOrder.addEventHandler(ActionEvent.ACTION, eventHandler);
    }

    public Product getSelectedProduct(){
        return table.getSelectionModel().getSelectedItem();
    }



    public void createDialogueBox(){
        PasswordDialogue passwordDialogue = new PasswordDialogue(this, controllerCustomer);
        passwordDialogue.sizeToScene();
        passwordDialogue.show();
    }

    public void addToOrderList(Product product){
        orderList.getItems().add(product);
    }

    public void deleteOrderList(){
        orderList.getItems().clear();
    }

    public Order getOrder(){
        Order order = new Order();
        order.addAll(orderList.getItems());
        return order;
    }

    public void sendMessageToServer(String authentification, Object message){
        List<Object> msg = new ArrayList<>();
        msg.add(authentification);
        msg.add(message);
        outgoingThread.sendToServer(msg);
    }


    public void setInfoText(String infoText){
        String t = infoBox.getText() + infoText;
        infoBox.setText(t);
    }

    public void onWindowClose(){
        incomingThread.setViewExists(false);
        outgoingThread.setViewExists(false);
        timeThread.setViewExists(false);
        try{
            incomingThread.join();
            outgoingThread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        try{
            tcpSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }


    }
}

class IncomingThread extends Thread {

    ObjectInputStream inputStream;

    ControllerCustomer controllerCustomer;

    private boolean viewExists = true;


    public IncomingThread (Socket socket, ControllerCustomer controllerCustomer){
        System.out.println("IncomingThread");

        this.controllerCustomer = controllerCustomer;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        while (viewExists){
            Object received = null;
            try {
                received = inputStream.readObject();
            } catch (IOException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }

            if(received != null){
                String rec = (String) received;
                controllerCustomer.displayInfo(rec);
            }

        }
        try {
            inputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }


    public void setViewExists(boolean viewExists){
        this.viewExists = viewExists;
    }
}

class OutgoingThread extends Thread {

    ObjectOutputStream outputStream;
    ViewCustomer viewCustomer;
    private boolean viewExists = true;


    public OutgoingThread (Socket socket,ViewCustomer viewCustomer){
        System.out.println("OutgoingThread");
        this.viewCustomer =viewCustomer;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void run(){
        while (viewExists){
        }
        try {
            outputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public synchronized void sendToServer(Object message){
        try{
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setViewExists(boolean viewExists){
        this.viewExists = viewExists;
    }

}


class TimeThread extends Thread {
    private InetAddress inetAddress;
    private int port;
    private ViewCustomer viewCustomer;
    private boolean viewExists = true;

    public TimeThread(InetAddress inetAddress, int port, ViewCustomer viewCustomer) {
        this.inetAddress = inetAddress;
        this.port = port;
        this.viewCustomer = viewCustomer;
    }

    public void run() {
        System.out.println("Client started");

        // Socket für den Klienten anlegen
        try (DatagramSocket dSocket = new DatagramSocket(6668);) {
            while(viewExists){

                try{


                    String command = "time";
                    byte buffer[] = null;
                    buffer = command.getBytes();

                    DatagramPacket packet = new DatagramPacket(buffer,
                            buffer.length, inetAddress, port);
                    dSocket.send(packet);

                    byte answer[] = new byte[1024];
                    // Paket für die Antwort vorbereiten
                    packet = new DatagramPacket(answer, answer.length);
                    // Auf die Antwort warten
                    dSocket.receive(packet);


                    String answr = new String(packet.getData());

                    Platform.runLater(new Runnable() {
                        @Override public void run() {
                            viewCustomer.setTime(answr);
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }



                }catch (IOException e1) {
                    e1.printStackTrace();
                }

            }


        } catch (SocketException e1) {
            e1.printStackTrace();
        }
    }

    public void setViewExists(boolean viewExists){
        this.viewExists = viewExists;
    }
}

