package ru.ncedu.logistics.service.import_export;

import ru.ncedu.logistics.service.DataStorage;

import java.io.*;

public class SerializationService {

    public static void serializeDataStorageToFile(ExportedContent content){

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("export.txt"));
            out.writeObject(content);
            out.close();
            content.getStorage().clear();
            System.out.println("Content have written to file.");
            System.out.println("Date: " + content.getDate());
        } catch (IOException e){
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    public static void deserializeDataStorageFromFile(DataStorage storage){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("export.txt"));
            ExportedContent content = (ExportedContent)in.readObject();
            System.out.println("Date of session: " + content.getDate());
            storage.restore(content.getStorage());
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
