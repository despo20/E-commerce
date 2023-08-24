package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UserInterface {


 GridPane loginPage;
 HBox headerBar;
 HBox footerBar;
 Customer logincustmor;
VBox body;
Button signInButton;
Label welcomeLabel;
ObservableList<Product>itemsInCart= FXCollections.observableArrayList();
Button placeOrderButton=new Button("Place Order");

    public UserInterface(){
        createLoginPage();
        createHeaderBar();
        createFooterBar();
    }

ProductList pr=new ProductList();
    VBox product;


    public BorderPane createContent(){

        BorderPane root=new BorderPane();
        root.setPrefSize(400,400);


        root.setTop(headerBar);
       // root.setCenter(loginPage);
        body=new VBox();
        body.setPadding(new Insets(10));
    body.setAlignment(Pos.CENTER);
      product=pr.getAllProduct();
      root.setCenter(body);
     body.getChildren().add(product);
     root.setBottom(footerBar);
        return root;
    }
    private void createLoginPage(){
         Text usernameText=new Text("Username");
         Text passwordText=new Text("password");
        TextField username=new TextField();
        username.setPromptText( "Enter you username/Email id");
        PasswordField password=new PasswordField();
        password.setPromptText("Enter your password here");
        Button button=new Button("Login");
        Label messagaeLabel=new Label("Hii");
        loginPage=new GridPane();
        loginPage.setStyle("-fx-background-color:grey");
        loginPage.setAlignment(Pos.CENTER);
        loginPage.setHgap(10);
        loginPage.setVgap(10);
        loginPage.add(usernameText,0,0);
        loginPage.add(username,1,0);
        loginPage.add(passwordText,0,1 );
        loginPage.add(password,1,1);
        loginPage.add(button,1,2);
        loginPage.add(messagaeLabel,0,2);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name=username.getText();
                String pass=password.getText();
                Login login=new Login();
                logincustmor=login.custmorLogin(name,pass);
                 if(logincustmor!=null){
                     messagaeLabel.setText("Welcome "+" "+logincustmor.getName());
                     welcomeLabel.setText("welcome"+logincustmor.getName());
                     headerBar.getChildren().add(welcomeLabel);
                     body.getChildren().clear();
                     body.getChildren().add(product);
                 }else{
                     messagaeLabel.setText("failed");
                 }
            }
        });


    }

    private void createFooterBar(){

       Button buyNow=new Button("BuyNow");
       signInButton=new Button("Sign In");
       Button addToCart=new Button("Add To Cart");
       welcomeLabel=new Label();
       footerBar=new HBox();
       footerBar.setStyle("-fx-background-color:grey");
       footerBar.setPadding(new Insets(10));
       footerBar.setAlignment(Pos.CENTER);
      footerBar.setSpacing(10);
       footerBar.getChildren().addAll(buyNow,addToCart);

       buyNow.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               Product product= pr.getSelectedProduct();
               if(product==null){
                 showDialog("please select a product");
                 return;
               }
               if(logincustmor==null){
                   showDialog("Please login first");
                   return;
               }
               boolean status=Order.placeOrder(logincustmor,product);
               if(status){
                   showDialog("Order placed succesfully !!");
               }else{
                   showDialog("Order failed");
               }

           }
       });

       addToCart.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               Product product= pr.getSelectedProduct();
               if(product==null){
                   showDialog("please Select a product First To Add In Cart");
                   return;
               }
               itemsInCart.add(product);
               showDialog("Item added sucessfully in cart");
           }
       });

    }
private void showDialog(String msg){
        Alert alert =new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.setTitle("Message");
        alert.showAndWait();
}


    private void createHeaderBar(){
        TextField searchbar=new TextField();
        searchbar.setPromptText("search");
        searchbar.setPrefWidth(200);
        Button button=new Button("search");


        signInButton=new Button("Sign In");

        welcomeLabel=new Label();

        Button  cartButton=new Button("Cart");
        headerBar=new HBox();
        headerBar.setStyle("-fx-background-color:grey");
        headerBar.setPadding(new Insets(10));
        headerBar.setAlignment(Pos.CENTER);
        headerBar.setSpacing(10);
        headerBar.getChildren().addAll(searchbar,button,signInButton,cartButton);
        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(loginPage);
                headerBar.getChildren().remove(signInButton);
            }
        });

        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                VBox prodPage=pr.productInCart(itemsInCart);
                prodPage.setAlignment(Pos.CENTER);
                prodPage.getChildren().add(placeOrderButton);
                body.getChildren().add(prodPage);
                footerBar.setVisible(false);//all foot will hide
            }
        });

        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //need list of product and a custmor

                if(itemsInCart==null){
                    showDialog("please add some product");
                    return;
                }
                if(logincustmor==null){
                    showDialog("Please login first");
                    return;
                }
               int count=Order.placeMultipleOrder(logincustmor,itemsInCart);
                if(count!=0){
                    showDialog("Order for"+count+" placed succesfully !!");
                }else{
                    showDialog("Order failed");
                }
            }
        });
    }
}
