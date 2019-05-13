package ru.ncedu.logistics.service.factory;

import ru.ncedu.logistics.model.entity.RoadEntity;
import ru.ncedu.logistics.model.entity.RoadId;
import ru.ncedu.logistics.repository.RoadRepository;
import ru.ncedu.logistics.repository.TownRepository;
import ru.ncedu.logistics.service.import_export.StringBasedImporter;

import java.util.Scanner;

public class RoadFactory implements StringBasedImporter {

    public void importFromString(String string){
        //data[0] - left town
        //data[1] - right town
        //data[2] - distance
        String[] data = string.split(" ");
        TownRepository townRepository = new TownRepository();

        RoadId roadId = new RoadId();
        roadId.setLeftId(townRepository.findByName(data[0]).getId());
        roadId.setRightId(townRepository.findByName(data[1]).getId());

        RoadEntity obj = new RoadEntity();
        obj.setId(roadId);

        if(Double.valueOf(data[2])>1){
            obj.setDistance(Double.valueOf(data[2]));
            RoadRepository roadRepository = new RoadRepository();
            roadRepository.create(obj);
        }

    }

    public void createRoad(){
        System.out.println("\nMethod: createRoad");
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter left town's name: ");
        String leftTown = sc.nextLine();

        System.out.print("Enter right town's name: ");
        String rightTown = sc.nextLine();
        while(leftTown.equals(rightTown)){
            System.out.print("Can't create road from town to same town. Enter another town: ");
            rightTown = sc.nextLine();
        }


        System.out.print("Enter distance(km) between towns: ");
        double distance = sc.nextDouble();
        while(distance < 1) {
            System.out.print("Invalid distance. Enter correct distance: ");
            sc.nextLine(); //skip '\n' after double
            distance = sc.nextDouble();
        }

        TownRepository townRepository = new TownRepository();

        RoadId roadId = new RoadId();
        roadId.setLeftId(townRepository.findByName(leftTown).getId());
        roadId.setRightId(townRepository.findByName(rightTown).getId());

        RoadEntity obj = new RoadEntity();
        obj.setId(roadId);
        obj.setDistance(distance);
        RoadRepository roadRepository = new RoadRepository();
        roadRepository.create(obj);
    }
}
