package ru.ncedu.logistics.service.import_export;

import ru.ncedu.logistics.repository.*;
import ru.ncedu.logistics.service.DatabaseConnection;

import java.io.*;
import java.sql.*;
import java.util.Date;

public class SerializationService {

    public static void serializeDataToFile(){

        Date date = new Date(System.currentTimeMillis());

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("export.txt"));
            Connection connection = DatabaseConnection.getConnection();
            ProductRepository productRepository = new ProductRepository();
            out.writeObject(date);

            //serialize products
            PreparedStatement stm = connection.prepareStatement("SELECT COUNT(*) FROM products");
            ResultSet resultSet = stm.executeQuery();
            int rows = 0;
            if(resultSet.next()){
                rows = resultSet.getInt(1);
            }
            int page, size = 3;
            for(page = 1; page < (int) Math.ceil(rows/size); page++){
                out.writeObject(productRepository.getPageSortByIdAsc(new PageRequest(page, size)));
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

//    public static void deserializeDataStorageFromFile(DataStorage storage){
//        try{
//            ObjectInputStream in = new ObjectInputStream(new FileInputStream("export.txt"));
//            ExportedContent content = (ExportedContent)in.readObject();
//            System.out.println("Date of session: " + content.getDate());
//            storage.restore(content.getStorage());
//        } catch (IOException e){
//            System.out.println("IOException error. Deserialize. ");
//            e.printStackTrace();
//        }
//        catch (ClassNotFoundException e){
//            System.out.println("ClassNotFoundException error. Deserialize. ");
//            e.printStackTrace();
//        }
//
//    }

}
