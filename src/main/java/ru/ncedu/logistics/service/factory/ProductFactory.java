package ru.ncedu.logistics.service.factory;

import ru.ncedu.logistics.model.entity.ProductEntity;
import ru.ncedu.logistics.repository.ProductRepository;
import ru.ncedu.logistics.service.import_export.StringBasedImporter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductFactory implements StringBasedImporter {

    private static final ProductRepository productRepository = new ProductRepository();

    public void importFromString(String string){
        try {
            productRepository.importFromFile(string);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createProduct(){
        System.out.println("\nMethod: createProduct");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product's name: ");
        ProductEntity obj = new ProductEntity();
        obj.setName(sc.nextLine());
        try {
            productRepository.create(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
