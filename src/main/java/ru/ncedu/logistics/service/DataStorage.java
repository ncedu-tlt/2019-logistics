package ru.ncedu.logistics.service;

import ru.ncedu.logistics.model.Office;
import ru.ncedu.logistics.model.Product;
import ru.ncedu.logistics.model.Road;
import ru.ncedu.logistics.model.Town;

import java.util.LinkedList;
import java.util.List;

public class DataStorage {

    private List<Town> towns = new LinkedList<>();
    private List<Product> products = new LinkedList<>();
    private List<Road> roads = new LinkedList<>();

    public DataStorage(){}

    public List<Town> getAllTowns() {
        return towns;
    }

    public Town getTownByName(String name){
        Town temp = new Town("");
        for(Town el: towns){
            if(el.getName().equals(name)){
                temp = el;
            }
        }
        return temp;
    }

    public Product getProductByName(String name){
        Product temp = new Product("");
        for(Product el: products){
            if(el.getName().equals(name)){
                temp = el;
            }
        }
        return temp;
    }

    public Office getOfficeByTownAndPhone(String townName, String phone){
        Office temp = new Office();
        for(Town selectedTown: towns){
            if(selectedTown.getName().equals(townName)){
                for(Office selectedOffice: selectedTown.getOffices()){
                    if(selectedOffice.getPhone().equals(phone)){
                        temp = selectedOffice; break;
                    }
                }
            }
        }
        return temp;
    }

    public List<Road> getAllRoads() {
        return roads;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void addTown(Town obj){
        towns.add(obj);
    }

    public void addRoad(Road obj){
        roads.add(obj);
    }

    public void addProduct(Product obj){
        products.add(obj);
    }
}
