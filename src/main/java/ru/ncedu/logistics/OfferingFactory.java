package ru.ncedu.logistics;

public class OfferingFactory implements StringBasedImporter {

    private DataStorage storage;

    public OfferingFactory(DataStorage storage){
        this.storage = storage;
    }

    public void importFromString(String string){
        String data[] = string.split(" ");
        Product product = storage.getProductByName(data[2]);
        storage.getOfficeByTownAndPhone(data[0],data[1]).addOffering(new Offering(product, Double.valueOf(data[3])));
    }
}

