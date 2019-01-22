package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

//import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.*;

public class BudgetServer extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextArea log = new TextArea();
        log.setEditable(false);
        log.setMouseTransparent(true);
        log.setFocusTraversable(false);
        log.setWrapText(true);
        BorderPane pane = new BorderPane();
        pane.setCenter(log);


        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8000);
                Socket socket = serverSocket.accept();
                DataInputStream inStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
                Platform.runLater(() -> log.appendText("Server started at: " + new Date() + "\n"));

//                MysqlDataSource ds = new MysqlDataSource();
//                ds.setUser("csuser1");
//                ds.setPassword("csuser1");
//                ds.setDatabaseName("budgetDB");
               
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://10.120.72.40:3306/budgetDB", "root", "root");
                try {
                    Statement stmt = conn.createStatement();
                    stmt.executeQuery("show budgetTable;");
                    
                } catch (SQLException e) {
                    System.out.println("Failed to send");
                }
                System.out.println("Here I am!!!");
                while (true) {
                    try {
                        log.appendText(inStream.readUTF() + "\n");
                    } catch (Exception ex) {
                        log.appendText(ex.getMessage() +"\n");
                    }
                }
            } catch (Exception e) {
                Platform.runLater(() -> log.appendText(e.getMessage()));
            }
        }).start();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
