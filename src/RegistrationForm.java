import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegistrationForm extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button registerButton = new Button("Register");

        registerButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            User newUser = new User(username, password);
            DatabaseManager.registerUser(newUser);
            System.out.println("User registered: " + newUser.getUsername());
        });

        VBox root = new VBox(20);
        root.getChildren().addAll(usernameField, passwordField, registerButton);

        Scene scene = new Scene(root, 500, 400);

        primaryStage.setTitle("User Registration");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

