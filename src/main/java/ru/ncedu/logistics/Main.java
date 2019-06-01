package ru.ncedu.logistics;

import ru.ncedu.logistics.service.DatabaseConnection;
import ru.ncedu.logistics.service.SearchAlgorithm;
import ru.ncedu.logistics.service.TestDataInitializer;
import ru.ncedu.logistics.service.factory.*;
import ru.ncedu.logistics.service.import_export.SerializationService;
import ru.ncedu.logistics.ui.MenuAction;

import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final TownFactory townFactory = new TownFactory();
    private static final ProductFactory productFactory = new ProductFactory();
    private static final OfficeFactory officeFactory = new OfficeFactory();
    private static final OfferingFactory offeringFactory = new OfferingFactory();
    private static final RoadFactory roadFactory = new RoadFactory();

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

        System.out.print("Would you like to clear database? (Y)es (N)o: ");
        String clear = sc.nextLine();
        if(clear.startsWith("Y") || clear.startsWith("y")){
           try{
                Connection cnt = DatabaseConnection.getConnection();
                Statement stm = cnt.createStatement();
                stm.execute("SELECT clear()");
                stm.close();
                cnt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.print("Would you like to load testdata? (Y)es (N)o: ");
        String load = sc.nextLine();
        if(load.startsWith("Y") || load.startsWith("y")){
            try{
                Connection cnt = DatabaseConnection.getConnection();
                Statement stm = cnt.createStatement();
                stm.execute("SELECT init_test_data();");
                stm.close();
                cnt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        MenuAction[] actions = MenuAction.values();
        MenuAction action;

        Connection cnt = DatabaseConnection.getConnection();

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
                    townFactory.createTown(); break;
                case ADD_OFFICE:
                    officeFactory.createOffice(); break;
                case ADD_OFFERING:
                    offeringFactory.createOffering(); break;
                case ADD_PRODUCT:
                    productFactory.createProduct(); break;
                case ADD_ROAD:
                    roadFactory.createRoad(); break;
                case SHOW_INFO:
                    officeFactory.showOfficeInfo(); break;
                case FIND_PRODUCT:
                    offeringFactory.findProduct(); break;
                case SEARCH:
                    SearchAlgorithm.search(); break;
                case EXPORT:
                    SerializationService.serializeDataToFile(); break;
                case IMPORT:
                    TestDataInitializer.importData(); break;
                }
            } while (action != MenuAction.EXIT);
            System.out.println("Program is halt...");


    }

}
