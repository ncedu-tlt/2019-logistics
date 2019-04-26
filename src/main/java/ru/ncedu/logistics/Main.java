package ru.ncedu.logistics;

import ru.ncedu.logistics.service.DataStorage;
import ru.ncedu.logistics.service.TestDataInitializer;
import ru.ncedu.logistics.service.factory.*;
import ru.ncedu.logistics.service.import_export.ExportedContent;
import ru.ncedu.logistics.service.import_export.SerializationService;
import ru.ncedu.logistics.ui.MenuAction;

import java.util.Scanner;

public class Main {

    private final static DataStorage STORAGE = new DataStorage();

    public static void main(String[] args) {
        System.out.println("\nLogistics 2019. You are welcome!\n");

        TestDataInitializer testDataInitializer = new TestDataInitializer(STORAGE);

        System.out.println("Would you like to import data from file?");
        System.out.println("1) Yes");
        System.out.println("2) No");

        Scanner sc = new Scanner(System.in);
        if(sc.nextInt() == 1){
            testDataInitializer.importData("testData.txt");
        } else {
            testDataInitializer.createTestData();
        }

        MenuAction[] actions = MenuAction.values();
        MenuAction action;

        do{
            //Menu
            System.out.println();
            for(int i = 0; i < actions.length; ++i) {
                System.out.println(i+1 + ") " + actions[i].getDescription());
            }

            System.out.print("Make a choice: ");
            int act = sc.nextInt();
            while(act < 1 || act > actions.length){
                System.out.print("Invalid choice! Make a valid choice: ");
                act = sc.nextInt();
            }

            action = actions[act-1];
            switch (action){
                case ADD_TOWN:
                    TownFactory townFactory = new TownFactory(STORAGE);
                    townFactory.addTownByUser(); break;
                case ADD_OFFICE:
                    OfficeFactory officeFactory = new OfficeFactory(STORAGE);
                    officeFactory.addOfficeByUser(); break;
                case ADD_OFFERING:
                    OfferingFactory offeringFactory = new OfferingFactory(STORAGE);
                    offeringFactory.addOfferingByUser(); break;
                case ADD_PRODUCT:
                    ProductFactory productFactory = new ProductFactory(STORAGE);
                    productFactory.addProductByUser(); break;
                case ADD_ROAD:
                    RoadFactory roadFactory = new RoadFactory(STORAGE);
                    roadFactory.addRoadByUser(); break;
                case SHOW_INFO: STORAGE.showOfficeInfo(); break;
                case FIND_PRODUCT: STORAGE.findProduct(); break;
                case EXPORT:
                    SerializationService.serializeDataStorageToFile(new ExportedContent(STORAGE)); break;
                case IMPORT:
                    SerializationService.deserializeDataStorageFromFile(STORAGE); break;
                }
            } while (action != MenuAction.EXIT);
            System.out.println("Program is halt...");
    }

}
