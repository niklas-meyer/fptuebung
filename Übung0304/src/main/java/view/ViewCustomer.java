package view;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.util.converter.IntegerStringConverter;
import model.Product;
import model.Order;

import java.io.IOException;
import java.net.*;

public class ViewCustomer extends BorderPane {

    private TableView<Product> table = new TableView<Product>();
    TableColumn<Product, Integer> buyColum;
    TableColumn<Product, String> nameColum;
    private Button button = new Button("Buy");
    private ListView<Order> liste = new ListView<Order>();
    private  Label timeLabel;

    public static void main(String[] args){

    }



    public ViewCustomer() {
        // Start a thread for the date
        InetAddress ia = null;
        try {
            ia = InetAddress.getByName("localhost");
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
        }
        new ViewCustomerThread(ia, 6667, this).start();
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

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(timeLabel,table, button);
        VBox.setVgrow(table, Priority.ALWAYS);

        setRight(vbox);

        liste.setPrefWidth(400);
        setCenter(liste);

    }

    public void setList(ObservableList<Product> liste) {
        table.setItems(liste);
    }

    public void setTime(String time){
        timeLabel.setText(time);
    }

    public void addEventHandler(
            EventHandler<CellEditEvent<Product, String>> eventHandler) {

        nameColum.setOnEditCommit(eventHandler);

    }

}


class ViewCustomerThread extends Thread {
    private InetAddress inetAddress;
    private int port;
    private ViewCustomer viewCustomer;

    public ViewCustomerThread(InetAddress inetAddress, int port, ViewCustomer viewCustomer) {
        this.inetAddress = inetAddress;
        this.port = port;
        this.viewCustomer = viewCustomer;
    }

    public void run() {
        System.out.println("Client started");

        // Socket für den Klienten anlegen
        try (DatagramSocket dSocket = new DatagramSocket(6666);) {
            while(true){

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
                    System.out.println("Answer: " + answr);


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
}

