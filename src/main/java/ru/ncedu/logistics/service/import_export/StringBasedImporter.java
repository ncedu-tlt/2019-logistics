package ru.ncedu.logistics.service.import_export;

public interface StringBasedImporter<T> {
    void importFromString(String string);
}

