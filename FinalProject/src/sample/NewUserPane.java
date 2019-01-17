package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NewUserPane extends VBox {
    BorderPane mainVB = new BorderPane();
    HBox nameHB = new HBox();
    HBox passwordHB = new HBox();
    HBox verifyHB;
    Label nameLB = new Label("Username: ");
    Label passwordLB = new Label("Password: ");
    Label verifyPasswordLB;
    TextField nameTF = new TextField();
    PasswordField passwordTF;
    PasswordField verifyPasswordPF;
    Button submitBT;

    public NewUserPane() {
        this.mainVB = new BorderPane();
        this.nameHB = new HBox();
        this.passwordHB = new HBox();
        this.verifyHB = new HBox();
        this.nameLB = new Label("Username: ");
        this.passwordLB = new Label("Password: ");
        this.verifyPasswordLB = new Label("Verify Password: ");
        this.nameTF = new TextField();
        this.passwordTF = new PasswordField();
        this.verifyPasswordPF = new PasswordField();
        this.submitBT = new Button("Submit");

        nameHB.getChildren().addAll(nameLB, nameTF);
        passwordHB.getChildren().addAll(passwordLB, passwordTF);
        verifyHB.getChildren().addAll(verifyPasswordLB, verifyPasswordPF);
        this.getChildren().addAll(nameLB, nameTF, passwordLB, passwordTF, verifyPasswordLB, verifyPasswordPF, submitBT);
        this.setPadding(new Insets(40));
    }
}
