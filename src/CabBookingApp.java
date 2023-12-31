import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CabBookingApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cab Booking App");

        // Create UI components
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        Label locationLabel = new Label("Location:");
        TextField locationTextField = new TextField();

        Label typeLabel = new Label("Type:");
        ComboBox<String> typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("Standard", "Premium", "Luxury");

        Button searchButton = new Button("Search");
        TextArea resultTextArea = new TextArea();
        resultTextArea.setEditable(false);

        ListView<String> cabList = new ListView<>();
        cabList.getItems().addAll("Cab 1", "Cab 2", "Cab 3");

        Button bookButton = new Button("Book Selected Cab");

        // Handle Search Button Click
        searchButton.setOnAction(e -> {
            String location = locationTextField.getText();
            String selectedType = typeComboBox.getSelectionModel().getSelectedItem();

            // Perform backend search (not implemented in this example)
            // Simulate result
            StringBuilder searchResult = new StringBuilder("Available cabs near " + location + " of type " + selectedType + ":\n");
            for (String cab : cabList.getItems()) {
                searchResult.append(cab).append("\n");
            }
            resultTextArea.setText(searchResult.toString());
        });

        // Handle Book Button Click
        bookButton.setOnAction(e -> {
            String selectedCab = cabList.getSelectionModel().getSelectedItem();
            if (selectedCab != null) {
                // Perform backend booking (not implemented in this example)
                resultTextArea.setText("Booking confirmed for " + selectedCab);
                cabList.getItems().remove(selectedCab); // Mark as unavailable
            } else {
                resultTextArea.setText("Please select a cab to book.");
            }
        });

        // Add components to the grid
        grid.add(locationLabel, 0, 0);
        grid.add(locationTextField, 1, 0);
        grid.add(typeLabel, 0, 1);
        grid.add(typeComboBox, 1, 1);
        grid.add(searchButton, 2, 0);
        grid.add(resultTextArea, 0, 2, 3, 1);
        grid.add(cabList, 0, 3, 2, 1);
        grid.add(bookButton, 2, 3);

        // Create scene and set it to the stage
        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
