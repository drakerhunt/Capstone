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
                ServerSocket serverSocket = new ServerSocket(5000);
                Socket socket = serverSocket.accept();
                DataInputStream inStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());

                Platform.runLater(() -> log.appendText("Server started at: " + new Date() + "\n"));
            } catch (Exception e) {
                Platform.runLater(() -> log.appendText(e.getMessage()));
            }
        }).start();
    }
}
