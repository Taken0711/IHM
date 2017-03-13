/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.polytech.ihm.kernel;

import fr.polytech.ihm.data.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Olivier
 */
public class MainProducts {
    
    private List<Product> products;

    private int currentIndex = 0;

    public MainProducts() {
        products = new ArrayList<>();

        extractProducts();
    }

    private void extractProducts(){
        products.clear();

        try {

            Class.forName("org.sqlite.JDBC").newInstance();
            System.out.println("Chargement du Driver Réussie");

            Connection cnx = DriverManager.getConnection("jdbc:sqlite:magasin.sqlite");
            System.out.println("Connexion Réussie");

            Statement lien = cnx.createStatement();
            System.out.println("Lien Créé");

            ResultSet rs = lien.executeQuery("select * from products ");

            System.out.println("Requête Effectuée");

            while (rs.next()) {

                products.add(new Product(500,
                        rs.getString("productName"),
                        rs.getString("picture"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getInt("idMarque"),
                        rs.getInt("nbSell"),
                        rs.getInt("idProduct"),
                        (rs.getInt("produitPhare") == 1),
                        (rs.getInt("enVente") == 1),
                        rs.getInt("promo")));
                System.out.println("RES : " + rs.getString("category"));

            }

            rs.close();
            lien.close();
            cnx.close();

        } catch (Exception e) {

            System.out.println("Le Programme a Echoué :/ \n" + e.getMessage());

        }
    }

    public int getNbMainProds() {

        return products.size();

    }

    public Product getCurrentProduct() {

        return products.get(currentIndex);

    }

    public Product nextProduct() {

        if (currentIndex == products.size() - 1) {

            currentIndex = 0;

        } else {

            currentIndex++;

        }

        return products.get(currentIndex);

    }

    public Product prevProduct() {

        if (currentIndex == 0) {

            currentIndex = products.size() - 1;

        } else {

            currentIndex--;

        }

        return products.get(currentIndex);

    }

    public List<Product> getProducts(){
        extractProducts();
        return products;
    }
    
}
