import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginForm extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            boolean loginSuccessful = DatabaseManager.loginUser(username, password);
            if (loginSuccessful) {
                System.out.println("Login successful for user: " + username);
            } else {
                System.out.println("Invalid username or password");
            }
        });

        VBox root = new VBox(20);
        root.getChildren().addAll(usernameField, passwordField, loginButton);

        Scene scene = new Scene(root, 500, 400);

        primaryStage.setTitle("User Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

