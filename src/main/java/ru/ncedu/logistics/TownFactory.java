package ru.ncedu.logistics;

public class TownFactory implements StringBasedImporter {

    private DataStorage storage;

    public TownFactory(DataStorage storage){
        this.storage = storage;
    }

    public void importFromString(String string)
    {
        String[] newTowns = string.split(" ");
        for(String el: newTowns){
            storage.addTown(new Town(el));
        }
    }
}
