package ru.ncedu.logistics.api.exception;

public class TownNotFoundException extends EntityNotFoundException {
    public TownNotFoundException(String id) {
        super("Town", id);
    }
}
