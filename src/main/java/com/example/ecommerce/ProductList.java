package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ProductList {


    private TableView<Product> productTable;

    public VBox createTable(ObservableList<Product> data){
        //col
        TableColumn Id=new TableColumn("Id");
        Id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn Name=new TableColumn("NAME");
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn Price=new TableColumn("Price");
        Price.setCellValueFactory(new PropertyValueFactory<>("price"));

        //data-dummy data


        productTable=new TableView<>();
        productTable.setItems(data);
        productTable.getColumns().addAll(Id,Name,Price);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vbox=new VBox();
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(productTable);


        return vbox;


    }

//    public VBox getdummytable(){
//        ObservableList<Product> data= FXCollections.observableArrayList();
//
//        data.add(new Product(2,"LENOVO",1));
//        data.add(new Product(3,"LENOVO",6));
//        return createTable(data);
//    }

    public VBox getAllProduct(){
    ObservableList<Product>data=Product.getAllProducts();
    return  createTable(data);
    }


    public Product getSelectedProduct(){
        return  productTable.getSelectionModel().getSelectedItem();
    }

    public VBox productInCart(ObservableList<Product>data){
        return createTable(data);
    }
}
