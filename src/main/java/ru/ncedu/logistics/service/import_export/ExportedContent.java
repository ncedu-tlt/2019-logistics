package ru.ncedu.logistics.service.import_export;

import ru.ncedu.logistics.service.DataStorage;

import java.io.Serializable;
import java.util.Date;

public class ExportedContent implements Serializable {
    private Date date;
    private DataStorage storage;

    public ExportedContent(DataStorage storage){
        this.storage = storage;
        this.date = new Date(System.currentTimeMillis());
    }

    public Date getDate(){
        return date;
    }

    public DataStorage getStorage() {
        return storage;
    }
}
