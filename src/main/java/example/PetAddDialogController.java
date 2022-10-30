package example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PetAddDialogController {
    @FXML
    private TextField addKind;
    @FXML
    private TextField addName;
    @FXML
    private TextField addOwnerName;
    @FXML
    private TextField addYear;
    @FXML
    private TextField addMonth;

    private Stage dialogStage;
    private Pet pet;
    private ButtonType result = ButtonType.CANCEL;
    String errorMessage = "";

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public ButtonType getResult() {
        return result;
    }

    private boolean isInputValid() {

        if (addKind.getText() == null || addKind.getText().length() == 0) {
            errorMessage += "No valid pet kind! ";
        }

        if (addName.getText() == null || addName.getText().length() == 0) {
            errorMessage += "No valid pet name! ";
        }
        if (addOwnerName.getText() == null || addOwnerName.getText().length() == 0) {
            errorMessage += "No valid OwnerName! ";
        }
        if (addYear.getText() == null || addYear.getText().length() == 0) {
            errorMessage += "No valid year! ";
        } else {
            try {
                Integer.parseInt(addYear.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid year (must be an integer)! ";
            }
        }
        if (addMonth.getText() == null || addMonth.getText().length() == 0) {
            errorMessage += "No valid month! ";
        } else {
            try {
                Integer.parseInt(addYear.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid month (must be an integer)! ";
            }
        }
        if (errorMessage.length() == 0)
            return true;
        else
            return false;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            pet.setKind(addKind.getText());
            pet.setName(addName.getText());
            pet.setOwnerName(addOwnerName.getText());
            pet.setYear(Integer.parseInt(addYear.getText()));
            pet.setMonth(Integer.parseInt(addMonth.getText()));
            result = ButtonType.OK;
            dialogStage.close();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("alertstyle.css").toExternalForm());
            alert.showAndWait();
            Label label = new Label();
        }
    }

    @FXML
    private void handleCancel() {
        result = ButtonType.CANCEL;
        dialogStage.close();
    }
}
