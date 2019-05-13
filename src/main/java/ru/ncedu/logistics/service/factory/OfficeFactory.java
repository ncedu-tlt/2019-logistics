package ru.ncedu.logistics.service.factory;

import ru.ncedu.logistics.model.entity.OfficeEntity;
import ru.ncedu.logistics.model.entity.TownEntity;
import ru.ncedu.logistics.repository.OfficeRepository;
import ru.ncedu.logistics.repository.TownRepository;
import ru.ncedu.logistics.service.DatabaseConnection;
import ru.ncedu.logistics.service.import_export.StringBasedImporter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class OfficeFactory implements StringBasedImporter {


    public void importFromString(String string) {
        String[] data = string.split(" ");

        OfficeEntity office = new OfficeEntity();

        TownRepository townRepository = new TownRepository();
        TownEntity town = townRepository.findByName(data[0]);

        office.setTownId(town.getId());
        office.setPhone(Integer.valueOf(data[1]));

        OfficeRepository officeRepository = new OfficeRepository();
        officeRepository.create(office);

    }

    public void createOffice(){
        System.out.println("\nMethod: createOffice");
        OfficeEntity office = new OfficeEntity();
        TownRepository townRepository = new TownRepository();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter town's name where office located: ");
        TownEntity town = townRepository.findByName(sc.nextLine());
        office.setTownId(town.getId());

        System.out.print("Enter office's phone number: ");
        office.setPhone(sc.nextInt());

        OfficeRepository officeRepository = new OfficeRepository();
        officeRepository.create(office);
    }

    public void showOfficeInfo(){
        System.out.println("\nMethod: showOfficeInfo");
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter town's name: ");
        String townName = sc.nextLine();

        TownRepository townRepository = new TownRepository();
        TownEntity town = townRepository.findByName(townName);

        if(town.getId() != null){
            try {
                DatabaseConnection dbc = new DatabaseConnection();
                Connection connection = dbc.getConnection();
                PreparedStatement stm = connection.prepareStatement("SELECT * FROM offices WHERE town_id = ?");
                stm.setInt(1, town.getId());
                ResultSet resultSet = stm.executeQuery();
                int count = 0;
                while(resultSet.next()){
                    System.out.println("Office #"+ ++count);
                    System.out.println("ID: " + resultSet.getInt("id"));
                    System.out.println("Phone: " + resultSet.getInt("phone") + '\n');
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Error! This town doesn't exist.");
            return;
        }
    }
}
