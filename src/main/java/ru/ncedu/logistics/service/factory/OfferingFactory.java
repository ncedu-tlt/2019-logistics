package ru.ncedu.logistics.service.factory;

import ru.ncedu.logistics.model.entity.*;
import ru.ncedu.logistics.repository.OfferingRepository;
import ru.ncedu.logistics.repository.OfficeRepository;
import ru.ncedu.logistics.repository.ProductRepository;
import ru.ncedu.logistics.service.import_export.StringBasedImporter;

import java.sql.SQLException;
import java.util.Scanner;

public class OfferingFactory implements StringBasedImporter {

    private static final OfficeRepository officeRepository = new OfficeRepository();
    private static final ProductRepository productRepository = new ProductRepository();
    private static final OfferingRepository offeringRepository = new OfferingRepository();


    public void importFromString(String string){
        //data[0] - town name
        //data[1] - phone
        //data[2] - product name
        //data[3] - price
        String data[] = string.split(" ");

        OfficeRepository officeRepository = new OfficeRepository();
        ProductRepository productRepository = new ProductRepository();

        try {
            OfferingId offeringId = new OfferingId();
            offeringId.setOfficeId(officeRepository.findByTownNameAndPhone(data[0], Integer.valueOf(data[1])).getId());
            offeringId.setProductId(productRepository.findByName(data[2]).getId());

            OfferingEntity obj = new OfferingEntity();
            obj.setId(offeringId);
            obj.setPrice(Double.valueOf(data[3]));

            OfferingRepository offeringRepository = new OfferingRepository();
            offeringRepository.create(obj);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void createOffering(){
        System.out.println("\nMethod: createOffering");
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter town's name where office located: ");
        String townName = sc.nextLine();

        System.out.print("Enter office's phone number: ");
        int phone = sc.nextInt();
        sc.nextLine(); //skip '\n' after int


        System.out.print("Enter product's name: ");
        String productName = sc.nextLine();


        System.out.print("Enter product's price: ");
        double price = sc.nextDouble();
        if(price < 1) {
            System.out.print("Invalid price! Enter correct number: ");
            //sc.nextLine();
            price = sc.nextDouble();
        }

        try {
            OfferingId offeringId = new OfferingId();
            offeringId.setOfficeId(officeRepository.findByTownNameAndPhone(townName, phone).getId());
            offeringId.setProductId(productRepository.findByName(productName).getId());

            OfferingEntity obj = new OfferingEntity();
            obj.setId(offeringId);
            obj.setPrice(price);

            offeringRepository.create(obj);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void findProduct(){
        System.out.println("\nMethod: findProduct");
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter product's name: ");
        try {
            offeringRepository.findOfferingsByProductId(productRepository.findByName(sc.nextLine()).getId());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

