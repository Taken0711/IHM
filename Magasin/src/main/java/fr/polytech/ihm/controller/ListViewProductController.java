package fr.polytech.ihm.controller;

import fr.polytech.ihm.model.ProductInListView;
import fr.polytech.ihm.model.ProductModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

/**
 * @author Jérémy LARA
 * @version 1.0
 *          Represents the main lower band (directionsCommon) controller class.
 *          It allows to go on the directions page and handle directions button.
 */
public class ListViewProductController {

    @FXML
    private Pane productListView;

    @FXML
    private Label productName;

    @FXML
    private ImageView productImage;

    @FXML
    private Label price;

    @FXML
    private Text oldPrice;

    @FXML
    private Label newPrice;

    @FXML
    private Label reduction;

    private int[] reduc = {15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70};

    @FXML
    public void initialize() {

    }

    private ProductInListView productTemp;

    @FXML
    void goProductPage(MouseEvent event) throws Exception {
        Stage stage = (Stage) productName.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Client/produitMain.fxml"));
        Parent root = loader.load();
        ((ProductMainController) loader.getController()).initProduct(productTemp);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        new CommonController(stage, scene);
        stage.show();
    }

    public void initializeProduct(ProductInListView product) {
        productTemp = product;
        productName.setText(product.getName());
        productImage = new ImageView(product.getImage());
        this.price.setText(Integer.toString(product.getPrice()) + "€");
    }

    public void initializeProductPromo(ProductInListView product) {
        productTemp = product;
        productName.setText(product.getName());
        productImage = new ImageView(product.getImage());
        Random r = new Random();
        double reductionNb = reduc[r.nextInt(reduc.length)];
        oldPrice.setText(Integer.toString(product.getPrice()) + "€");
        oldPrice.setStrikethrough(true);
        newPrice.setText(Integer.toString((int) ((1 - (reductionNb / 100)) * product.getPrice())));
        reduction.setText("-" + Double.toString(reductionNb) + "%");
    }

}
