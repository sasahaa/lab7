import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class language {

    @FXML
    private TextArea textArea;

    @FXML
    private void setGD() {
        switchLanguage("gd");
    }

    @FXML
    private void setSR() {
        switchLanguage("sr");
    }

    @FXML
    private void setSK() {
        switchLanguage("sk");
    }

    private void switchLanguage(String lang) {
        try {
            Locale.setDefault(new Locale(lang));

            ResourceBundle bundle = ResourceBundle.getBundle("lang");

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("textRedactor.fxml"),
                    bundle
            );

            Parent root = loader.load();

            Stage stage = (Stage) textArea.getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}