package example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private ObservableList<Pet> data = FXCollections.observableArrayList(
            new Pet("Dog", "Billy", 3, 2, "Jimmy"),
            new Pet("Cat", "Musya", 8, 3, "Sanya"),
            new Pet("Parrot", "Duda", 1, 8, "Joel"),
            new Pet("Rat", "Larisa", 2, 0, "Varya")
    );

    @Override
    public void start(Stage stage){
        try {
            stage.setTitle("Lab12 FXML");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("MainWindow.fxml"));
            Parent root = loader.load();
            MainWindowController controller = loader.getController();
            controller.setData(data);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("mainstyle.css").toExternalForm());
            stage.setTitle("Table");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            System.out.println("Loader error");
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
