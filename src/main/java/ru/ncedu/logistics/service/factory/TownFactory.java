package ru.ncedu.logistics.service.factory;

import ru.ncedu.logistics.service.import_export.StringBasedImporter;
import ru.ncedu.logistics.service.DataStorage;
import ru.ncedu.logistics.model.Town;

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

    public void addTownByUser(){
        System.out.println("\nMethod: addTown");
        storage.addTown(new Town());
    }
}
