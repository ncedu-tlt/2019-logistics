package ru.ncedu.logistics.service.import_export;

import ru.ncedu.logistics.model.Offering;
import ru.ncedu.logistics.model.entity.*;
import ru.ncedu.logistics.repository.*;
import ru.ncedu.logistics.service.DataStorage;
import ru.ncedu.logistics.service.DatabaseConnection;

import java.io.*;
import java.sql.*;
import java.util.Date;

public class SerializationService {

    public static void serializeDataStorageToFile(){

        Date date = new Date(System.currentTimeMillis());

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("export.txt"));
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();

            out.writeObject(date);

            //serialize towns
            TownRepository townRepository = new TownRepository();
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT id FROM towns ORDER BY id ASC");
            while(resultSet.next()){
                out.writeObject(townRepository.getById(resultSet.getInt(1)));
            }

            //serialize products
            ProductRepository productRepository = new ProductRepository();
            resultSet = stm.executeQuery("SELECT id FROM products ORDER BY id ASC");
            while(resultSet.next()){
                out.writeObject(productRepository.getById(resultSet.getInt(1)));
            }

            //serialize offices
            OfficeRepository officeRepository = new OfficeRepository();
            resultSet = stm.executeQuery("SELECT id FROM products ORDER BY id ASC");
            while(resultSet.next()){
                out.writeObject(officeRepository.getById(resultSet.getInt(1)));
            }

            //serialize offerings
            OfferingRepository offeringRepository = new OfferingRepository();
            resultSet = stm.executeQuery("SELECT office_id, product_id FROM offerings ORDER BY office_id, product_id ASC");
            while(resultSet.next()){
                OfferingId offeringId = new OfferingId();
                offeringId.setOfficeId(resultSet.getInt("office_id"));
                offeringId.setProductId(resultSet.getInt("product_id"));
                out.writeObject(offeringRepository.getById(offeringId));
            }

            //serialize roads
            RoadRepository roadRepository = new RoadRepository();
            resultSet = stm.executeQuery("SELECT left_town_id, right_town_id FROM roads ORDER BY left_town_id, right_town_id ASC");
            while(resultSet.next()){
                RoadId roadId = new RoadId();
                roadId.setLeftId(resultSet.getInt("left_town_id"));
                roadId.setRightId(resultSet.getInt("right_town_id"));
                out.writeObject(roadRepository.getById(roadId));
            }

            out.close();
            System.out.println("Content have written to file.");
            System.out.println("Date: " + date);
        } catch (IOException e){
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deserializeDataStorageFromFile(DataStorage storage){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("export.txt"));
            ExportedContent content = (ExportedContent)in.readObject();
            System.out.println("Date of session: " + content.getDate());
//            storage.restore(content.getStorage());
        } catch (IOException e){
            System.out.println("IOException error. Deserialize. ");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            System.out.println("ClassNotFoundException error. Deserialize. ");
            e.printStackTrace();
        }

    }

}
