package fr.polytech.ihm.ihm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        launch(args);
        /*
        InsertApp app = new InsertApp();
        
        app.insertMagasin("Ville 5", "Adresse 5", 95, 56, 
                "0505050505", "m5@yopmail.com", 66666, 14, 
                2048, "www.m5.com", 3);
        */
    }

    public void start(Stage stage) throws Exception {

        log.info("Starting Hello JavaFX and Maven demonstration application");

        String fxmlFile = "/fxml/AdminPanel.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));

        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode, 800, 533);
        scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("To Be Or To Have - Admin panel");
        stage.setScene(scene);
        stage.show();
    }
}
