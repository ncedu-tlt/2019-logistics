package ru.ncedu.logistics.api.exception;

public class OfficeNotFoundException extends EntityNotFoundException{
    public OfficeNotFoundException(String id) {
        super("Office", id);
    }
}
