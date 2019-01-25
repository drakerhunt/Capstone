package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    ArrayList<Double> expensesList = new ArrayList<>();
    HashMap<String, Double> expenseMap = new HashMap<>();
    double monthlyIncome = 800;
    double total = monthlyIncome;

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Drake Hunt");
        Label expenseLB = new Label("Expense: $");
        Label expenseNameLB = new Label("Expense Name: ");
        Label monthlyIncomeLB = new Label("Monthly Income: $" + monthlyIncome);
        Label totalsAfterExpenseLB = new Label("Total After Expenses:     $" + total);

        label.setStyle("-fx-font-size: 20;");
        expenseLB.setStyle("-fx-font-size: 18;");
        expenseNameLB.setStyle("-fx-font-size: 18;");

        TextArea totalsTA = new TextArea();
        TextArea totalsNamesTA = new TextArea();
        TextField expenseTFAmount = new TextField("0.00");
        TextField expenseNameTF = new TextField("Here");
        TextField totalTF = new TextField("$" + total);

        totalsNamesTA.setMaxWidth(175);
        totalsTA.setMaxWidth(128);
        totalsAfterExpenseLB.setMaxWidth(303);
        totalsNamesTA.setEditable(false);
        totalsNamesTA.setMouseTransparent(true);
        totalsNamesTA.setFocusTraversable(false);
        totalsTA.setEditable(false);
        totalsTA.setMouseTransparent(true);
        totalsTA.setFocusTraversable(false);

        VBox vBox = new VBox();
        HBox expenseHB = new HBox(43);
        HBox totalsHB = new HBox();
        HBox expenseNameHB = new HBox();

        for (int i = 0; i < expensesList.size(); i++) {
            totalsTA.appendText(expensesList.get(i) + "\n");
        }

        Button addBt = new Button("Add");
        totalsHB.getChildren().addAll(totalsNamesTA, totalsTA);
        expenseHB.getChildren().addAll(expenseLB, expenseTFAmount);
        expenseNameHB.getChildren().addAll(expenseNameLB, expenseNameTF);
        vBox.getChildren().addAll(label, monthlyIncomeLB, totalsHB, totalsAfterExpenseLB, expenseHB, expenseNameHB, addBt);

        addBt.setOnAction(e -> {
            double expense = -Double.parseDouble(expenseTFAmount.getText());
            expensesList.add(expense);
            totalsTA.appendText("$" + expense + "\n");
            expenseTFAmount.setText("0.00");

            expenseMap.put(expenseNameTF.getText(), expense);

            total += expense;
            totalsNamesTA.appendText(expenseNameTF.getText() + "\n");
            expenseNameTF.setText("");
            totalsAfterExpenseLB.setText("Total After Expenses:     $" + total);
        });

        EntryPane entryPane = new EntryPane();
        NewUserPane newUserPane = new NewUserPane();

        Scene scene2 = new Scene(newUserPane, 320, 275);
        Scene scene1 = new Scene(entryPane, 320, 275);
        Scene scene = new Scene(vBox, 320, 275);
        primaryStage.setScene(scene1);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Client");
        primaryStage.show();

        entryPane.submitBT.setOnAction(e -> {
            primaryStage.setScene(scene);
        });

        new Thread(() -> {
            try {
                Socket socket = new Socket("localhost", 8000);
                DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream inStream = new DataInputStream(socket.getInputStream());
                
                outStream.writeUTF("Connected");

                entryPane.newUserBT.setOnAction(o -> {
                    primaryStage.setScene(scene2);
                });
                newUserPane.submitBT.setOnAction(e -> {
                    if (newUserPane.passwordTF.getText().equals(newUserPane.verifyPasswordPF.getText())) {
                        try {
                            outStream.writeUTF(newUserPane.nameTF.getText());
                            outStream.writeUTF(newUserPane.passwordTF.getText());
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                });
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }).start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
