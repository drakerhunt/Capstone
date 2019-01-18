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
import java.sql.*;

public class BudgetServer extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextArea log = new TextArea();
        BorderPane pane = new BorderPane();
        pane.setCenter(log);


        Scene scene = new Scene(pane, 200, 200);
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

                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("10.120.72.40", "csuser1", "csuser1");
                try {
                    Statement stmt = conn.createStatement();
                    stmt.executeQuery("show budgetTable;");
                    conn.commit();
                } catch (Exception e) {
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
