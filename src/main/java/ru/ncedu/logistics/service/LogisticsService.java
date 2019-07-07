package ru.ncedu.logistics.service;

import ru.ncedu.logistics.dto.RoadDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class LogisticsService {


    @Inject
    private RoadService roadService;

    public Map<Integer, Double> getTownsCost(int initialTownId){
        Map<Integer, Double> townsCost = new HashMap<>();
        Set<Integer> townsWithChangedCost = new HashSet<>();

        townsCost.put(initialTownId, 0.0);
        townsWithChangedCost.add(initialTownId);

        while(!townsWithChangedCost.isEmpty()){
            Set<Integer> townsWithChangedCostNew = new HashSet<>();

            for(int id : townsWithChangedCost){
                if(roadService.existsByTownId(id)) {
                    List<RoadDTO> roads = roadService.findByTownId(id);

                    for (RoadDTO road : roads) {
                        int nextId = (road.getLeftTown().getId().equals(id)) ? road.getRightTown().getId() : road.getLeftTown().getId();

                        double newCost = townsCost.get(id) + road.getDistance();
                        Double nextTownCost = townsCost.get(nextId);

                        if(nextTownCost == null || newCost < nextTownCost){
                            townsCost.put(nextId, newCost);
                            townsWithChangedCostNew.add(nextId);
                        }
                    }
                }
            }

            townsWithChangedCost = townsWithChangedCostNew;
        }

        return townsCost;
    }

    public List<RoadDTO> findRoads(double minTownCost, Map<Integer, Double> townsCost){
        List<RoadDTO> pathRoad = new LinkedList<>();
        double stepCost = minTownCost;

        while (stepCost > 0) {
            for (Map.Entry<Integer, Double> pair : townsCost.entrySet()) {
                int nextTownId = pair.getKey();
                List<RoadDTO> roads = roadService.findByTownId(nextTownId);
                for (RoadDTO road : roads) {
                    if (stepCost - road.getDistance() == pair.getValue()) {
                        pathRoad.add(road);
                        stepCost -= road.getDistance();
                        break;
                    }
                }
            }
        }

        return pathRoad;
    }
}
