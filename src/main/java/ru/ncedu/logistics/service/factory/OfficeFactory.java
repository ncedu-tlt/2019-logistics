package ru.ncedu.logistics.service.factory;

import ru.ncedu.logistics.model.entity.OfficeEntity;
import ru.ncedu.logistics.model.entity.TownEntity;
import ru.ncedu.logistics.repository.OfficeRepository;
import ru.ncedu.logistics.repository.TownRepository;
import ru.ncedu.logistics.service.import_export.StringBasedImporter;

import java.sql.SQLException;
import java.util.Scanner;

public class OfficeFactory implements StringBasedImporter {

    private static final TownRepository townRepository = new TownRepository();
    private static final OfficeRepository officeRepository = new OfficeRepository();


    public void importFromString(String string) {
        String[] data = string.split(" ");

        OfficeEntity office = new OfficeEntity();

        try {
            TownEntity town = townRepository.findByName(data[0]);

            office.setTownId(town.getId());
            office.setPhone(Integer.valueOf(data[1]));

            officeRepository.create(office);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void createOffice(){
        System.out.println("\nMethod: createOffice");
        OfficeEntity office = new OfficeEntity();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter town's name where office located: ");
        try {
            TownEntity town = townRepository.findByName(sc.nextLine());
            office.setTownId(town.getId());

            System.out.print("Enter office's phone number: ");
            office.setPhone(sc.nextInt());

            officeRepository.create(office);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void showOfficeInfo(){
        System.out.println("\nMethod: showOfficeInfo");
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter town's name: ");
        String townName = sc.nextLine();

        try {
            officeRepository.showOfficeInfo(townName);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
