package ru.ncedu.logistics.service.factory;

import ru.ncedu.logistics.model.Office;
import ru.ncedu.logistics.model.Town;
import ru.ncedu.logistics.service.import_export.StringBasedImporter;
import ru.ncedu.logistics.service.DataStorage;
import ru.ncedu.logistics.model.Offering;
import ru.ncedu.logistics.model.Product;

import java.util.Scanner;

public class OfferingFactory implements StringBasedImporter {

    private DataStorage storage;

    public OfferingFactory(DataStorage storage){
        this.storage = storage;
    }

    public void importFromString(String string){
        String data[] = string.split(" ");
        Product product = storage.getProductByName(data[2]);
        storage.getOfficeByTownAndPhone(data[0],data[1]).addOffering(new Offering(product, Double.valueOf(data[3])));
    }

    public void addOfferingByUser(){
        System.out.println("\nMethod: addOffering");

        Scanner sc = new Scanner(System.in);

        System.out.println("List of towns:");
        int number = 0;
        for(Town element: storage.getAllTowns()){
            System.out.println(++number + ") " + element.getName());
        }

        System.out.println("Select town where product is for sale: ");
        int selectedTown = sc.nextInt();
        while (selectedTown < 1 || selectedTown > storage.getAllTowns().size()) {
            System.out.println("Invalid choice! Make a valid choice: ");
            selectedTown = sc.nextInt();
        }

        System.out.println("\nList of offices: ");
        int numberOffice = 0;
        for(Office element: storage.getAllTowns().get(selectedTown-1).getOffices()){
            System.out.println("\nOffice №"+ ++numberOffice);
            element.showOfficeInfo();
        }

        System.out.print("Select office where product is for sale: ");
        int selectedOffice = sc.nextInt();
        while (selectedOffice-1 < 0 || selectedOffice-1 > storage.getAllTowns().get(selectedTown-1).getOffices().size()) {
            System.out.print("Invalid choice! Make a valid choice: ");
            selectedOffice = sc.nextInt();
        }

        Product product = new Product();
        storage.addProduct(product);
        System.out.print("Enter price of product: ");
        double price = sc.nextDouble();

        storage.getAllTowns().get(selectedTown-1).getOffices().get(selectedOffice-1).addOffering(new Offering(product,price));
    }
}

