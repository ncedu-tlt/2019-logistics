package ru.ncedu.logistics.service;

import ru.ncedu.logistics.service.import_export.StringBasedImporter;
import ru.ncedu.logistics.service.factory.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TestDataInitializer {

    private static Map<String, StringBasedImporter> importers = new HashMap<>();

    public TestDataInitializer(){
        importers.put("Products", new ProductFactory());
        importers.put("Towns", new TownFactory());
        importers.put("Office", new OfficeFactory());
        importers.put("Offering", new OfferingFactory());
        importers.put("Road", new RoadFactory());
    }

    public static void importData(){

        System.out.print("Enter input file: ");
        Scanner sc = new Scanner(System.in);

        try{
            List<String> lines = Files.readAllLines(Paths.get(sc.nextLine()), Charset.defaultCharset());

            for(String line: lines){

                String[] divided = {line.substring(0,line.indexOf(':')),
                                    line.substring(line.indexOf(':')+2)};
                importers.get(divided[0]).importFromString(divided[1]);

            }

        } catch (IOException e) {}
    }

}
