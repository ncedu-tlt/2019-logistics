package ru.ncedu.logistics.api.exception;

public class OfferingNotFoundException extends EntityNotFoundException {
    public OfferingNotFoundException(String officeId){
        super("Offering in office", officeId);
    }
}
