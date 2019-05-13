package ru.ncedu.logistics.service.factory;

import ru.ncedu.logistics.model.entity.TownEntity;
import ru.ncedu.logistics.repository.TownRepository;
import ru.ncedu.logistics.service.import_export.StringBasedImporter;

import java.sql.SQLException;
import java.util.Scanner;

public class TownFactory implements StringBasedImporter {

    private static final TownRepository townRepository = new TownRepository();

    public void importFromString(String string) {
        try {
            townRepository.importFromFile(string);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTown(){
        System.out.println("\nMethod: createTown");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter town's name: ");
        TownEntity obj = new TownEntity();
        obj.setName(sc.nextLine());
        try {
            townRepository.create(obj);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
