package ru.ncedu.logistics.api.exception;

public abstract class EntityExistsException extends RuntimeException{
    public EntityExistsException(String entity, String id){
        super(entity + " " + id + " already exists");
    }
}
