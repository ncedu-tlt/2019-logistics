package ru.ncedu.logistics.service.factory;

import ru.ncedu.logistics.service.import_export.StringBasedImporter;
import ru.ncedu.logistics.service.DataStorage;
import ru.ncedu.logistics.model.Product;

public class ProductFactory implements StringBasedImporter {

    private DataStorage storage;

    public ProductFactory(DataStorage storage){
        this.storage = storage;
    }

    public void importFromString(String string){
        String[] newProducts = string.split(" ");
        for(String el: newProducts){
            storage.addProduct(new Product(el));
        }
    }

    public void addProductByUser(){
        System.out.println("\nMethod: addProduct");
        storage.addProduct(new Product());
    }

}
