package org.example.tubes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class SaveController {

    @FXML
    private Label messageLabel;

    @FXML
    private void handleSave() {
        boolean saveSuccessful = performSaveOperation();

        if (saveSuccessful) {
            messageLabel.setText("Saved Successfully");
            messageLabel.setTextFill(Color.GREEN);
        } else {
            messageLabel.setText("Failed to save");
            messageLabel.setTextFill(Color.RED);
        }
    }

    private boolean performSaveOperation() {
        // Placeholder for the actual save logic
        // Return true if save is successful, false otherwise
        return true; // or false, based on your logic
    }
}
