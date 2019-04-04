package ru.ncedu.logistics.service.factory;

import ru.ncedu.logistics.service.import_export.StringBasedImporter;
import ru.ncedu.logistics.service.DataStorage;
import ru.ncedu.logistics.model.Road;
import ru.ncedu.logistics.model.Town;

import java.util.Scanner;

public class RoadFactory implements StringBasedImporter {

    private DataStorage storage;

    public RoadFactory(DataStorage storage){
        this.storage = storage;
    }

    public void importFromString(String string){
        Town first = new Town("");
        Town second = new Town("");
        String[] data = string.split(" ");
        for(Town el: storage.getAllTowns()){
            if(data[0].equals(el.getName())){
                first = el;
            }

            if(data[1].equals(el.getName())){
                second = el;
            }
        }
        storage.addRoad(Road.fromKilometers(first, second, Double.valueOf(data[2])));
    }

    public void addRoadByUser(){
        System.out.println("\nMethod: addRoad");
        System.out.println("List of towns:");
        int number = 0;
        for(Town element: storage.getAllTowns()){
            System.out.println(++number + ") " + element.getName());
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("Select first town: ");
        int first = sc.nextInt();
        while (first < 1 || first > storage.getAllTowns().size()) {
            System.out.println("Invalid choice! Make a valid choice: ");
            first = sc.nextInt();
        }

        System.out.print("Select second town: ");
        int second = sc.nextInt();
        while (second < 1 || second > storage.getAllTowns().size()) {
            System.out.println("Invalid choice! Make a valid choice: ");
            second = sc.nextInt();
        }

        System.out.print("Enter distance in kilometres: ");
        double distance = sc.nextDouble();
        while(distance < 1){
            System.out.println("Invalid distance! Enter valid distance: ");
            distance = sc.nextDouble();
        }

        try{
            Road newRoad = Road.fromKilometers(storage.getAllTowns().get(first-1), storage.getAllTowns().get(second-1), distance);

            for(Road element: storage.getAllRoads()){
                if(element.equals(newRoad)){
                    System.out.println("This road already exists!");
                    return;
                }
            }

            storage.addRoad(newRoad);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }
    }
}
