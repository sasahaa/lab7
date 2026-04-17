import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class textRedactor {

    @FXML
    private Button exitButton;

    @FXML
    private MenuItem openItem;

    @FXML
    private MenuItem saveItem;

    @FXML
    private TextArea textArea;

    @FXML
    void initialize() {
        exitButton.setOnAction(event -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });

        openItem.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Відкрити текстовий файл");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

            File file = fileChooser.showOpenDialog(exitButton.getScene().getWindow());
            if (file != null) {
                loadFile(file);
            }
        });

        saveItem.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Зберегти файл");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

            File file = fileChooser.showSaveDialog(exitButton.getScene().getWindow());
            if (file != null) {
                saveFile(file);
            }
        });
    }

    private void loadFile(File file) {
        try (Scanner scanner = new Scanner(file)) {
            textArea.clear();
            while (scanner.hasNextLine()) {
                textArea.appendText(scanner.nextLine() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Помилка при читанні: " + e.getMessage());
        }
    }

    @FXML
    private void setGD() { updateLanguage("gd"); }

    @FXML
    private void setSK() { updateLanguage("sk"); }

    @FXML
    private void setSR() { updateLanguage("sr"); }

    @FXML
    private void setUK() {
        updateLanguage("uk");
    }

    private void updateLanguage(String lang) {
        try {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            ResourceBundle bundle = ResourceBundle.getBundle("lang", locale);

            String currentText = textArea.getText();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("textRedactor.fxml"), bundle);
            Parent root = loader.load();

            textRedactor nextController = loader.getController();
            nextController.textArea.setText(currentText);

            Stage stage = (Stage) textArea.getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveFile(File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(textArea.getText());
        } catch (Exception e) {
            System.out.println("Помилка при збереженні: " + e.getMessage());
        }
    }
}
