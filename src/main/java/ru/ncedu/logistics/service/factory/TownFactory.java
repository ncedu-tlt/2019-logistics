package ru.ncedu.logistics.service.factory;

import ru.ncedu.logistics.model.entity.TownEntity;
import ru.ncedu.logistics.repository.TownRepository;
import ru.ncedu.logistics.service.DatabaseConnection;
import ru.ncedu.logistics.service.import_export.StringBasedImporter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TownFactory implements StringBasedImporter {

//    private Connection connection;
//
//    public TownFactory(Connection c){
//        this.connection = c;
//    }

    public void importFromString(String string) {
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("INSERT INTO towns(name) VALUES (?)");
            String[] newTowns = string.split(" ");
            for (String el : newTowns) {
                stm.setString(1, el);
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTown(){
        System.out.println("\nMethod: createTown");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter town's name: ");
        TownEntity obj = new TownEntity();
        TownRepository townRepository = new TownRepository();
        obj.setName(sc.nextLine());
        townRepository.create(obj);
    }

}
