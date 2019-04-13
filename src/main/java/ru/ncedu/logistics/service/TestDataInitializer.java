package ru.ncedu.logistics.service;

import ru.ncedu.logistics.model.*;
import ru.ncedu.logistics.service.import_export.StringBasedImporter;
import ru.ncedu.logistics.service.factory.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TestDataInitializer {

    private DataStorage storage;
    private Map<String, StringBasedImporter> importers = new HashMap<>();

    public TestDataInitializer(DataStorage storage){
        this.storage = storage;
        importers.put("Products", new ProductFactory(storage));
        importers.put("Towns", new TownFactory(storage));
        importers.put("Office", new OfficeFactory(storage));
        importers.put("Offering", new OfferingFactory(storage));
        importers.put("Road", new RoadFactory(storage));
    }

    public void importData(String fileName){

        try{
            List<String> lines = Files.readAllLines(Paths.get(fileName), Charset.defaultCharset());

            for(String line: lines){

                String[] divided = {line.substring(0,line.indexOf(':')),
                                    line.substring(line.indexOf(':')+2)};
                importers.get(divided[0]).importFromString(divided[1]);

            }

        } catch (IOException e) {}
    }

    public void createTestData(){
        //Common products
        Product cheese = new Product("Cheese");
        Product milk = new Product("Milk");
        Product feijoa = new Product("Feijoa");
        storage.addProduct(cheese);
        storage.addProduct(milk);
        storage.addProduct(feijoa);

        //Create town Togliatti
        Town TLT = new Town("Togliatti");
        List<Office> officeTLT = new LinkedList<>();

        Office tlt1 = new Office(TLT, "204729");
        Office tlt2 = new Office(TLT, "927402");
        //Offerings of first office TLT
        List<Offering> offerTlt1 = new LinkedList<>();
        offerTlt1.add(new Offering(tlt1, cheese, 120));
        offerTlt1.add(new Offering(tlt1, milk, 100));

        //Offerings of second office TLT
        List<Offering> offerTlt2 = new LinkedList<>();
        offerTlt2.add(new Offering(tlt2, cheese, 130));
        offerTlt2.add(new Offering(tlt2, milk, 90));

        //initialize offices TLT
        tlt1.setOfferings(offerTlt1);
        tlt2.setOfferings(offerTlt2);
        officeTLT.add(tlt1);
        officeTLT.add(tlt2);

        TLT.setOffices(officeTLT);
        storage.addTown(TLT);

        //Create town Samara
        Town SAMARA = new Town("Samara");
        List<Office> officeSAMARA = new LinkedList<>();

        Office samara1 = new Office(SAMARA, "880055");
        Office samara2 = new Office(SAMARA, "353517");

        //Offerings of the first office SAMARA
        List<Offering> offerSamara1 = new LinkedList<>();
        offerSamara1.add(new Offering(samara1, cheese, 140));
        offerSamara1.add(new Offering(samara1, milk, 120));
        offerSamara1.add(new Offering(samara1, feijoa, 75));

        //Offerings of the second office SAMARA
        List<Offering> offerSamara2 = new LinkedList<>();
        offerSamara2.add(new Offering(samara2, cheese, 130));
        offerSamara2.add(new Offering(samara2, milk, 90));


        //initialize offices SAMARA
        samara1.setOfferings(offerSamara1);
        samara2.setOfferings(offerSamara2);
        officeSAMARA.add(samara1);
        officeSAMARA.add(samara2);

        SAMARA.setOffices(officeSAMARA);
        storage.addTown(SAMARA);

        //Create town SARATOV
        Town SARATOV = new Town("Saratov");
        List<Office> officeSARATOV = new LinkedList<>();

        Office saratov = new Office(SARATOV, "167945");
        //Offering of office SARATOV
        List<Offering> offerSaratov = new LinkedList<>();
        offerSaratov.add(new Offering(saratov, cheese, 70));
        offerSaratov.add(new Offering(saratov, milk, 60));

        //initialize office SARATOV
        saratov.setOfferings(offerSaratov);
        officeSARATOV.add(saratov);

        SARATOV.setOffices(officeSARATOV);
        storage.addTown(SARATOV);

        //initialize roads
        storage.addRoad(Road.fromKilometers(TLT, SAMARA, 90));
        storage.addRoad(Road.fromKilometers(SAMARA, SARATOV, 485));

    }
}
