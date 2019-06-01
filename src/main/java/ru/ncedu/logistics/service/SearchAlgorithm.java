package ru.ncedu.logistics.service;

import ru.ncedu.logistics.model.entity.OfferingEntity;
import ru.ncedu.logistics.model.entity.RoadEntity;
import ru.ncedu.logistics.model.entity.TownEntity;
import ru.ncedu.logistics.repository.*;

import java.sql.SQLException;
import java.util.*;
public class SearchAlgorithm {

    private static TownRepository townRepository = new TownRepository();
    private static OfficeRepository officeRepository = new OfficeRepository();
    private static RoadRepository roadRepository = new RoadRepository();
    private static OfferingRepository offeringRepository = new OfferingRepository();
    private static Map<Integer, Double> townsCost = new HashMap<>();
    private static Set<Integer> townsWithChangedCost = new HashSet<>();

    public static void search(){

        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter product name: ");
        String productName = sc.nextLine();
        System.out.print("Enter your town: ");
        String startingTownName = sc.nextLine();

        try{
            TownEntity initialTownId = townRepository.findByName(startingTownName);
            while(initialTownId.getId() == null){
                System.out.print("This town doesn't exist! Enter correct town: ");
                startingTownName = sc.nextLine();
                initialTownId = townRepository.findByName(startingTownName);
            }

            townsCost.put(initialTownId.getId(), 0.0);
            townsWithChangedCost.add(initialTownId.getId());

            while (!townsWithChangedCost.isEmpty()) {
                Set<Integer> townsWithChangedCostNew = new HashSet<>();

                for(int id : townsWithChangedCost) {
                    List<RoadEntity> roads = roadRepository.findRoads(id);
                    for (RoadEntity obj : roads) {
                        int nextId = (obj.getId().getLeftId().equals(id)) ? obj.getId().getRightId() : obj.getId().getLeftId();

                        double newCost = townsCost.get(id) + obj.getDistance();
                        Double nextTownCost = townsCost.get(nextId);

                        if(nextTownCost == null || newCost < nextTownCost){
                            townsCost.put(nextId, newCost);
                            townsWithChangedCostNew.add(nextId);
                        }
                    }
                }

                townsWithChangedCost = townsWithChangedCostNew;
            }

            //Find town with min cost
            Integer minTownId = null;
            Double minTownCost = null;
            OfferingEntity minOfferingEntity = new OfferingEntity();
            minOfferingEntity.setPrice(0);
            for(Map.Entry<Integer, Double> pair : townsCost.entrySet()){
                OfferingEntity offering = offeringRepository.findMinOffer(pair.getKey(), productName);
                if(offering != null){
                    double curSum = pair.getValue() + offering.getPrice();
                    if( minTownCost == null || curSum < minTownCost + minOfferingEntity.getPrice()){
                        minTownId = pair.getKey();
                        minTownCost = pair.getValue();
                        minOfferingEntity = offering;
                    }
                }
            }

            //Find path from min town
            List<Integer> pathToMinTown = new LinkedList<>();
            pathToMinTown.add(minTownId);
            double stepCost = minTownCost;
            while(stepCost > 0){
                for(Map.Entry<Integer, Double> pair : townsCost.entrySet()){
                    int nextTownId = pair.getKey();
                    List<RoadEntity> roads = roadRepository.findRoads(nextTownId);
                    for(RoadEntity obj : roads){
                        if(stepCost - obj.getDistance() == pair.getValue()) {
                            pathToMinTown.add(nextTownId);
                            stepCost -= obj.getDistance();
                            break;
                        }
                    }
                }
            }

            System.out.println("\nYour choice.");
            System.out.println("Town: " + townRepository.getTownNameById(minTownId));
            System.out.println("Distance: " + minTownCost + " km");
            int officeId = minOfferingEntity.getId().getOfficeId();
            System.out.print("Office #" + officeId);
            System.out.println(" Phone: " + officeRepository.getById(officeId).getPhone());
            double productPrice = minOfferingEntity.getPrice();
            System.out.println("Product price: " + productPrice);
            System.out.println("Total price: " + (minTownCost*0.5 + productPrice));
            System.out.print("Path: ");
            for(int id : pathToMinTown){
                System.out.println(townRepository.getTownNameById(id));
            }
            System.out.println();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

}
