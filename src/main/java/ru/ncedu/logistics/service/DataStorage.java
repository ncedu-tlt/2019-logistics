package ru.ncedu.logistics.service;

import ru.ncedu.logistics.model.*;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DataStorage implements Serializable{

    private List<Town> towns = new LinkedList<>();
    private List<Product> products = new LinkedList<>();
    private List<Road> roads = new LinkedList<>();
    private List<Offering> offerings = new LinkedList<>();

    public DataStorage(){}

    public void clear(){
        towns.clear();
        products.clear();
        roads.clear();
        offerings.clear();
    }

    public void restore(DataStorage dump){
        this.towns = dump.getAllTowns();
        this.products = dump.getAllProducts();
        this.roads = dump.getAllRoads();
        this.offerings = dump.getAllOfferings();
    }

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
        for(Product el: products){
            if(el.getName().equals(name)){
                return el;
            }
        }
        return null;
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

    public List<Offering> getAllOfferings(){
        return offerings;
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

    public void addOffering(Offering obj){
        offerings.add(obj);
    }

    public void showOfficeInfo(){
        System.out.println("\nMethod: showOfficeInfo");
        System.out.println("List of towns:");
        int number = 0;
        for(Town element: getAllTowns()){
            System.out.println(++number + ") " + element.getName());
        }

        //Selecting town
        System.out.print("Select town: ");
        Scanner sc = new Scanner(System.in);

        int selectedTown = sc.nextInt();
        while (selectedTown < 1 || selectedTown > getAllTowns().size()) {
            System.out.println("Invalid choice! Make a valid choice: ");
            selectedTown = sc.nextInt();
        }


        System.out.println("\nList of offices: ");
        int numberOffice = 0;
        for(Office element: getAllTowns().get(selectedTown-1).getOffices()){
            System.out.println("\nOffice #"+ ++numberOffice);
            element.showOfficeInfo();
        }
    }

    public void findProduct(){
        System.out.println("\nSearching product by name.");
        System.out.print("Enter product's name: ");
        Scanner sc = new Scanner(System.in);
        Product selectedProduct = getProductByName(sc.nextLine());

        if(selectedProduct != null){
            List<Offering> findedOfferings = new LinkedList<>();

            for(Offering el: getAllOfferings()){
                if(el.getProduct().equals(selectedProduct)){
                    findedOfferings.add(el);
                }
            }

            Collections.sort(findedOfferings, new SortByPrice());

            System.out.println("Found offerings:");
            for (Offering el: findedOfferings){
                System.out.println("Town: " + el.getOffice().getTown().getName());
                System.out.println("Phone: " + el.getOffice().getPhone());
                System.out.println("Price: " + el.getPrice() + "\n");
            }

        } else {
            System.out.println("Product doesn't exists!");
            return;
        }


    }
}
