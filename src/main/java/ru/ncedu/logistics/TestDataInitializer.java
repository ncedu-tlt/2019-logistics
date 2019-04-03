package ru.ncedu.logistics;

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
        importers.put("Products:", new ProductFactory(storage));
        importers.put("Towns:", new TownFactory(storage));
        importers.put("Office:", new OfficeFactory(storage));
        importers.put("Offering:", new OfferingFactory(storage));
        importers.put("Roads:", new RoadFactory(storage));
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

        //Offerings of first office TLT
        List<Offering> offerTlt1 = new LinkedList<>();
        offerTlt1.add(new Offering(cheese, 120));
        offerTlt1.add(new Offering(milk, 100));

        //Offerings of second office TLT
        List<Offering> offerTlt2 = new LinkedList<>();
        offerTlt2.add(new Offering(cheese, 130));
        offerTlt2.add(new Offering(milk, 90));

        //initialize offices TLT
        officeTLT.add(new Office(TLT, "204729", offerTlt1));
        officeTLT.add(new Office(TLT, "927402", offerTlt2));

        TLT.setOffices(officeTLT);
        storage.addTown(TLT);

        //Create town Samara
        Town SAMARA = new Town("Samara");
        List<Office> officeSAMARA = new LinkedList<>();

        //Offerings of the first office SAMARA
        List<Offering> offerSamara1 = new LinkedList<>();
        offerSamara1.add(new Offering(cheese, 140));
        offerSamara1.add(new Offering(milk, 120));
        offerSamara1.add(new Offering(feijoa, 75));

        //Offerings of the second office SAMARA
        List<Offering> offerSamara2 = new LinkedList<>();
        offerSamara2.add(new Offering(cheese, 130));
        offerSamara2.add(new Offering(milk, 90));

        //initialize offices SAMARA
        officeSAMARA.add(new Office(SAMARA, "880055", offerSamara1));
        officeSAMARA.add(new Office(SAMARA, "353517", offerSamara2));

        SAMARA.setOffices(officeSAMARA);
        storage.addTown(SAMARA);

        //Create town SARATOV
        Town SARATOV = new Town("Saratov");
        List<Office> officeSARATOV = new LinkedList<>();

        //Offering of office SARATOV
        List<Offering> offerSaratov = new LinkedList<>();
        offerSaratov.add(new Offering(cheese, 70));
        offerSaratov.add(new Offering(milk, 60));

        //initialize office SARATOV
        officeSARATOV.add(new Office(SARATOV, "167945", offerSaratov));

        SARATOV.setOffices(officeSARATOV);
        storage.addTown(SARATOV);

        //initialize roads
        storage.addRoad(Road.fromKilometers(TLT, SAMARA, 90));
        storage.addRoad(Road.fromKilometers(SAMARA, SARATOV, 485));

    }
}
