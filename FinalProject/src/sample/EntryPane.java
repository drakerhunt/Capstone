package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class EntryPane extends BorderPane {
    BorderPane mainVB = new BorderPane();
    HBox nameHB = new HBox();
    HBox passwordHB = new HBox();
    Label nameLB = new Label("Username: ");
    Label passwordLB = new Label("Password: ");
    TextField nameTF = new TextField();
    PasswordField passwordTF;
    Button submitBT = new Button("Submit");

    public EntryPane() {
        this.mainVB = new BorderPane();
        this.nameHB = new HBox();
        this.passwordHB = new HBox();
        this.nameLB = new Label("Username: ");
        this.passwordLB = new Label("Password: ");
        this.nameTF = new TextField();
        this.passwordTF = new PasswordField();
        this.submitBT = new Button("Submit");

        nameHB.getChildren().addAll(nameLB, nameTF);
        passwordHB.getChildren().addAll(passwordLB, passwordTF);
        this.setTop(nameHB);
        this.setCenter(passwordHB);
        this.setBottom(submitBT);
        this.setPadding(new Insets(40));
    }

}
