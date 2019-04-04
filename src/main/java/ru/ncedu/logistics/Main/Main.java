package ru.ncedu.logistics.Main;

import ru.ncedu.logistics.model.*;
import ru.ncedu.logistics.service.DataStorage;
import ru.ncedu.logistics.service.TestDataInitializer;
import ru.ncedu.logistics.service.factory.*;
import ru.ncedu.logistics.ui.MenuAction;

import java.util.Scanner;

public class Main {

    private final static DataStorage STORAGE = new DataStorage();

    public static void showOfficeInfo(){
        System.out.println("\nMethod: showOfficeInfo");
        System.out.println("List of towns:");
        int number = 0;
        for(Town element: STORAGE.getAllTowns()){
            System.out.println(++number + ") " + element.getName());
        }

        //Selecting town
        System.out.print("Select town: ");
        Scanner sc = new Scanner(System.in);

        int selectedTown = sc.nextInt();
        while (selectedTown < 1 || selectedTown > STORAGE.getAllTowns().size()) {
            System.out.println("Invalid choice! Make a valid choice: ");
            selectedTown = sc.nextInt();
        }


        System.out.println("\nList of offices: ");
        int numberOffice = 0;
        for(Office element: STORAGE.getAllTowns().get(selectedTown-1).getOffices()){
            System.out.println("\nOffice №"+ ++numberOffice);
            element.showOfficeInfo();
        }
    }

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

        while(true){
            //Menu
            MenuAction[] actions = MenuAction.values();
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

            MenuAction action = actions[act-1];
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
                case SHOW_INFO: showOfficeInfo(); break;
                case EXIT:
                    System.out.println("Programm is halt...");return;
            }
        }

    }

}
