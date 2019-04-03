package ru.ncedu.logistics;

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

}
