package ru.ncedu.logistics;

public class OfficeFactory implements StringBasedImporter {

    private DataStorage storage;

    public OfficeFactory(DataStorage storage){
        this.storage = storage;
    }

    public void importFromString(String string) {
        String[] data = string.split(" ");
        storage.getTownByName(data[0]).addOffice(new Office(storage.getTownByName(data[0]), data[1]));
    }
}
