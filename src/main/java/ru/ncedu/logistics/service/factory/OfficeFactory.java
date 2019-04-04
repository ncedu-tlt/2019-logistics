package ru.ncedu.logistics.service.factory;

import ru.ncedu.logistics.model.Town;
import ru.ncedu.logistics.service.import_export.StringBasedImporter;
import ru.ncedu.logistics.service.DataStorage;
import ru.ncedu.logistics.model.Office;

import java.util.Scanner;

public class OfficeFactory implements StringBasedImporter {

    private DataStorage storage;

    public OfficeFactory(DataStorage storage){
        this.storage = storage;
    }

    public void importFromString(String string) {
        String[] data = string.split(" ");
        storage.getTownByName(data[0]).addOffice(new Office(storage.getTownByName(data[0]), data[1]));
    }

    public void addOfficeByUser(){
        System.out.println("\nMethod: addOffice");
        System.out.println("List of towns:");
        int number = 0;
        for(Town element: storage.getAllTowns()){
            System.out.println(++number + ") " + element.getName());
        }

        //Selecting town
        System.out.print("Select town in which add office: ");
        Scanner sc = new Scanner(System.in);
        int selectedTown = sc.nextInt();
        while (selectedTown < 1 || selectedTown > storage.getAllTowns().size()) {
            System.out.println("Invalid choice! Make a valid choice: ");
            selectedTown = sc.nextInt();
        }

        //Adding office and initializing offerings of new office
        System.out.print("Enter office's phone number: ");
        sc.nextLine(); //skip '\n' after int
        storage.getAllTowns().get(selectedTown-1).addOffice(new Office(storage.getAllTowns().get(selectedTown-1), sc.nextLine())); //sc.nextLine - String Office::phone
    }
}
