package ru.ncedu.logistics.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.time.LocalDate;

@Stateless
@LocalBean
public class TestBean {

    private LocalDate date = LocalDate.now();

    public LocalDate getDate() {
        return date;
    }

}
