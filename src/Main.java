import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Locale.setDefault(new Locale("uk"));

        URL fxmlLocation = getClass().getResource("textRedactor.fxml");

        if (fxmlLocation == null) {
            throw new RuntimeException("Помилка: Файл textRedactor.fxml не знайдено!");
        }

        ResourceBundle bundle = ResourceBundle.getBundle("lang");

        FXMLLoader loader = new FXMLLoader(fxmlLocation, bundle);
        Parent root = loader.load();

        primaryStage.setTitle(bundle.getString("title"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}