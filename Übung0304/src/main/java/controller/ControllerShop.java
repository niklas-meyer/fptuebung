package controller;


import java.io.IOException;
import java.util.Iterator;

import fpt.com.SerializableStrategy;
import fpt.com.db.AbstractDatabaseStrategy;
import javafx.event.ActionEvent;
import model.*;
import generator.IDGenerator;
import view.*;
import saveStrategies.*;

public class ControllerShop {

    ModelShop model;
    IDGenerator idGeneartor;
    SerializableStrategy strat;

    public void link(ModelShop model, ViewShop view) {

        // Serialization Strategies

        SerializableStrategy bin = new BinaryStrategy();
        SerializableStrategy xml = new XMLStrategy();
        SerializableStrategy xstream = new XStreamStrategy();

        // Database Strategies

        SerializableStrategy jdbc = new JDBCStrategy();
        SerializableStrategy openJPA = new OpenJPAStrategy();

        strat = bin;

        idGeneartor = new IDGenerator();
        this.model = model;

        view.setList(model);

        view.addEventHandler((ActionEvent e) -> {
            if (e.toString().contains("Add")) {
                if (!view.getNameInput().isEmpty()
                        && !view.getPriceInput().isEmpty()
                        && !view.getCountInput().isEmpty()) {

                    Product p = new Product();
                    p.setName(view.getNameInput());
                    p.setPrice(Double.parseDouble(view.getPriceInput()));
                    p.setQuantity(Integer.parseInt(view.getCountInput()));
                    try {
                        p.setId(idGeneartor.generateId());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    model.add(p);
                }

            }
            if (e.toString().contains("Delete")) {
                if (view.getSelectedIndex() != -1)
                    model.remove(view.getSelectedIndex());

            }
            if (e.toString().contains("Load")) {
                load();
            }

            if (e.toString().contains("Save")) {
                try {
                    save();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

            if (e.toString().contains("ChoiceBox")) {
                int choice = view.getChoice();
                switch (choice) {
                    case 0:
                        strat = bin;
                        break;
                    case 1:
                        strat = xml;
                        break;
                    case 2:
                        strat = xstream;
                        break;
                    case 3:
                        strat = openJPA;
                        break;
                    case 4:
                        strat = jdbc;
                        break;
                }
                ;
            }
        });

    }

    public void load() {

        model.clear();
        idGeneartor.clear();

        fpt.com.Product product;
        try {
            while ((product = strat.readObject()) != null) {
            	idGeneartor.addId(product.getId());
                model.add((Product) product);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            strat.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void save() throws Exception {

        Iterator<Product> iteratorp = model.iterator();

        if (model.size() < 2)
            throw new Exception(
                    "Die Warenliste sollte mindestens 2 Objekte enthalten");
        while (iteratorp.hasNext()) {
            try {
                strat.writeObject(iteratorp.next());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            strat.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
