package ru.ncedu.logistics.api.exception;

public abstract class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entity, String id) {
        super(entity + " " + id + " not found");
    }
}
