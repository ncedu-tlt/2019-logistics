package ru.ncedu.logistics;

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
}
