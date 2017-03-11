package fr.unice.polytech.a.ihm.g2c.controller.admin;

/**
 * Created by Jeremy on 10/03/2017.
 */
import fr.unice.polytech.a.ihm.g2c.common.AdminScene;
import fr.unice.polytech.a.ihm.g2c.common.Category;
import fr.unice.polytech.a.ihm.g2c.controller.AdminSceneController;
import fr.unice.polytech.a.ihm.g2c.model.DataModel;
import fr.unice.polytech.a.ihm.g2c.model.Store;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;

public class AddStoreController extends AdminSceneController {

    @FXML
    private TextField name;

    @FXML
    private TextArea description;

    @FXML
    private TextField imgPath;

    @FXML
    private ChoiceBox<Category> category;

    @FXML
    void initialize() {
        category.getItems().addAll(Category.values());
    }

    @FXML
    void browse(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File f = fileChooser.showOpenDialog(imgPath.getScene().getWindow());
        imgPath.setText(f.getAbsolutePath());
    }

    @FXML
    void submit(MouseEvent event) {
        try {
            Store store = new Store(name.getText(), description.getText(), imgPath.getText(), category.getValue());
            DataModel.getInstance().addStore(store);
            adminController.setAdminScene(AdminScene.MENU);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}