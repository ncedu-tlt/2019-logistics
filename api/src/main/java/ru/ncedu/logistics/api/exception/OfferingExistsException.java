package ru.ncedu.logistics.api.exception;

public class OfferingExistsException extends EntityExistsException {
    public OfferingExistsException(String officeId){
        super("Offering in office ", officeId);
    }
}
