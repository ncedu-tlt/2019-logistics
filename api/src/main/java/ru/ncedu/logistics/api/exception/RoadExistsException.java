package ru.ncedu.logistics.api.exception;

public class RoadExistsException extends EntityExistsException {
    public RoadExistsException(String id){
        super("Road in town ", id);
    }
}
