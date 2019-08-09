package ru.ncedu.logistics.api.exception;

public class RoadNotFoundException extends EntityNotFoundException {
    public RoadNotFoundException(String id){
        super("Road in town", id);
    }
}
