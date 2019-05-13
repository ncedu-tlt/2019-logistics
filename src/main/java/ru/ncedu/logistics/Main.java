package ru.ncedu.logistics;

import ru.ncedu.logistics.service.DatabaseConnection;
import ru.ncedu.logistics.service.TestDataInitializer;
import ru.ncedu.logistics.service.factory.*;
import ru.ncedu.logistics.service.import_export.ExportedContent;
import ru.ncedu.logistics.service.import_export.SerializationService;
import ru.ncedu.logistics.ui.MenuAction;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("\nLogistics 2019. You are welcome!");
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found.");
            e.printStackTrace();
            return;
        }

        System.out.print("Would you like to clear database? Yes(1) No(0): ");
        if(sc.nextInt() == 1) {
            try{
                DatabaseConnection dbc = new DatabaseConnection();
                Connection cnt = dbc.getConnection();
                Statement stm = cnt.createStatement();
                stm.execute("SELECT clear()");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.print("Would you like to load testdata? Yes(1) No(0): ");
        if(sc.nextInt() == 1) {
            try{
                DatabaseConnection dbc = new DatabaseConnection();
                Connection cnt = dbc.getConnection();
                Statement stm = cnt.createStatement();
                stm.execute("SELECT init_test_data();");
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
                    TownFactory townFactory = new TownFactory();
                    townFactory.createTown(); break;
                case ADD_OFFICE:
                    OfficeFactory officeFactory = new OfficeFactory();
                    officeFactory.createOffice(); break;
                case ADD_OFFERING:
                    OfferingFactory offeringFactory = new OfferingFactory();
                    offeringFactory.createOffering(); break;
                case ADD_PRODUCT:
                    ProductFactory productFactory = new ProductFactory();
                    productFactory.createProduct(); break;
                case ADD_ROAD:
                    RoadFactory roadFactory = new RoadFactory();
                    roadFactory.createRoad(); break;
                case SHOW_INFO:
                    OfficeFactory officeFactory1 = new OfficeFactory();
                    officeFactory1.showOfficeInfo(); break;
                case FIND_PRODUCT:
                    OfferingFactory offeringFactory1 = new OfferingFactory();
                    offeringFactory1.findProduct(); break;
                case EXPORT:
                    SerializationService.serializeDataStorageToFile(); break;
                case IMPORT:
                    TestDataInitializer testDataInitializer = new TestDataInitializer();
                    testDataInitializer.importData(); break;
                }
            } while (action != MenuAction.EXIT);
            System.out.println("Program is halt...");

    }

}
