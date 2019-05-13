package ru.ncedu.logistics.service.factory;

import ru.ncedu.logistics.model.entity.ProductEntity;
import ru.ncedu.logistics.repository.ProductRepository;
import ru.ncedu.logistics.service.DatabaseConnection;
import ru.ncedu.logistics.service.import_export.StringBasedImporter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductFactory implements StringBasedImporter {

    public void importFromString(String string){
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("INSERT INTO products(name) VALUES (?)");
            String[] newProducts = string.split(" ");
            for (String el : newProducts) {
                stm.setString(1, el);
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createProduct(){
        System.out.println("\nMethod: createProduct");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product's name: ");
        ProductEntity obj = new ProductEntity();
        ProductRepository productRepository = new ProductRepository();
        obj.setName(sc.nextLine());
        productRepository.create(obj);
    }

}
