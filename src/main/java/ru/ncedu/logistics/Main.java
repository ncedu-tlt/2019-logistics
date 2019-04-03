package ru.ncedu.logistics;

import java.util.Scanner;

public class Main {

    private final static DataStorage STORAGE = new DataStorage();

    public static void addTown(){
        System.out.println("\nMethod: addTown");
        STORAGE.addTown(new Town());
    }


    public static void addOffice(){
        System.out.println("\nMethod: addOffice");
        System.out.println("List of towns:");
        int number = 0;
        for(Town element: STORAGE.getAllTowns()){
            System.out.println(++number + ") " + element.getName());
        }

        //Selecting town
        System.out.print("Select town in which add office: ");
        Scanner sc = new Scanner(System.in);
        int selectedTown = sc.nextInt();
        while (selectedTown < 1 || selectedTown > STORAGE.getAllTowns().size()) {
            System.out.println("Invalid choice! Make a valid choice: ");
            selectedTown = sc.nextInt();
        }

        //Adding office and initializing offerings of new office
        System.out.print("Enter office's phone number: ");
        sc.nextLine(); //skip '\n' after int
        STORAGE.getAllTowns().get(selectedTown-1).addOffice(new Office(STORAGE.getAllTowns().get(selectedTown-1), sc.nextLine())); //sc.nextLine - String Office::phone
    }

    public static void addOffering(){
        System.out.println("\nMethod: addOffering");

        Scanner sc = new Scanner(System.in);

        System.out.println("List of towns:");
        int number = 0;
        for(Town element: STORAGE.getAllTowns()){
            System.out.println(++number + ") " + element.getName());
        }

        System.out.println("Select town where product is for sale: ");
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

        System.out.println("Select office where product is for sale: ");
        int selectedOffice = sc.nextInt();
        while (selectedOffice < 1 || selectedOffice > STORAGE.getAllTowns().get(selectedTown).getOffices().size()) {
            System.out.println("Invalid choice! Make a valid choice: ");
            selectedOffice = sc.nextInt();
        }

        Product product = new Product();
        STORAGE.addProduct(product);
        System.out.println("Enter price of product: ");
        double price = sc.nextDouble();

        STORAGE.getAllTowns().get(selectedTown-1).getOffices().get(selectedOffice-1).addOffering(new Offering(product,price));

    }

    public static Product addProduct(){
        System.out.println("\nMethod: addProduct");
        Product product = new Product();
        STORAGE.addProduct(product);
        return product;
    }

    public static void addRoad(){
        System.out.println("\nMethod: addRoad");
        System.out.println("List of towns:");
        int number = 0;
        for(Town element: STORAGE.getAllTowns()){
            System.out.println(++number + ") " + element.getName());
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("Select first town: ");
        int first = sc.nextInt();
        while (first < 1 || first > STORAGE.getAllTowns().size()) {
            System.out.println("Invalid choice! Make a valid choice: ");
            first = sc.nextInt();
        }

        System.out.print("Select second town: ");
        int second = sc.nextInt();
        while (second < 1 || second > STORAGE.getAllTowns().size()) {
            System.out.println("Invalid choice! Make a valid choice: ");
            second = sc.nextInt();
        }

        System.out.print("Enter distance in kilometres: ");
        double distance = sc.nextDouble();
        while(distance < 1){
            System.out.println("Invalid distance! Enter valid distance: ");
            distance = sc.nextDouble();
        }

        try{
            Road newRoad = Road.fromKilometers(STORAGE.getAllTowns().get(first-1), STORAGE.getAllTowns().get(second-1), distance);

            for(Road element: STORAGE.getAllRoads()){
                if(element.equals(newRoad)){
                    System.out.println("This road already exists!");
                    return;
                }
            }

            STORAGE.addRoad(newRoad);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }


    }

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
                case ADD_TOWN: addTown(); break;
                case ADD_OFFICE: addOffice(); break;
                case ADD_OFFERING: addOffering(); break;
                case ADD_PRODUCT: addProduct(); break;
                case ADD_ROAD: addRoad(); break;
                case SHOW_INFO: showOfficeInfo(); break;
                case EXIT:
                    System.out.println("Programm is halt...");return;
            }
        }

    }

}
