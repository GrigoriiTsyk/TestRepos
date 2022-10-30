package example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainWindowController implements Initializable{
    @FXML
    private TableView<Pet> table;
    @FXML
    private TableColumn<Pet, String> kindCol;
    @FXML
    private TableColumn<Pet, String> nameCol;
    @FXML
    private TableColumn<Pet, String> yearCol;
    @FXML
    private TableColumn<Pet, String> monthCol;
    @FXML
    private TableColumn<Pet, String> OwnerNameCol;

    @FXML
    private void saveAction(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Data File");
            File file = fileChooser.showSaveDialog(null);
            if (file == null) {
                return;
            }
            FileWriter out = new FileWriter(file);
            for (Pet pet : table.getItems()) {
                out.write(pet.getKind() + " " + pet.getName() + " " + pet.getYear() + " "
                        + pet.getMonth() + " " + pet.getOwnerName() + "\n");
            }
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exitAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void addAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("PetAddDialog.fxml"));
            Parent root = loader.load();
            PetAddDialogController controller = loader.getController();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Pet");
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            controller.setDialogStage(dialogStage);
            Pet pet = new Pet("", "", 0, 0, "");
            controller.setPet(pet);
            dialogStage.showAndWait();
            if (controller.getResult() == ButtonType.OK) {
                table.getItems().add(pet);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void editAction(ActionEvent event){
        try{
            int seletedIndex = table.getSelectionModel().getSelectedIndex();
            if(seletedIndex >= 0)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("PetAddDialog.fxml"));
                Parent root = loader.load();
                PetAddDialogController controller = loader.getController();
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Edit Pet");
                Scene scene = new Scene(root);
                dialogStage.setScene(scene);
                controller.setDialogStage(dialogStage);
                Pet pet = new Pet("", "", 0, 0, "");
                controller.setPet(pet);
                dialogStage.showAndWait();
                if (controller.getResult() == ButtonType.OK) {
                    table.getItems().set(seletedIndex, pet);
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No edited Item");
                alert.setHeaderText("Please select item");
                alert.setContentText("No edited item");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                        getClass().getResource("alertstyle.css").toExternalForm());
                alert.showAndWait();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            table.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No deleted item!");
            alert.setHeaderText("Please select item!");
            alert.setContentText("No deleted item!");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("alertstyle.css").toExternalForm());
            alert.showAndWait();
        }

    }

    @FXML
    private void aboutAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("About");
        alert.setContentText("Example JavaFXML application");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("alertstyle.css").toExternalForm());
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        kindCol.setCellValueFactory(new PropertyValueFactory<>("kind"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        OwnerNameCol.setCellValueFactory(new PropertyValueFactory<>("OwnerName"));
    }

    public void setData(ObservableList<Pet> data) {
        table.setItems(data);
    }
}
