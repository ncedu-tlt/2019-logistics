package ru.ncedu.logistics.service;

import ru.ncedu.logistics.model.Offering;

import java.util.Comparator;

public class SortByPrice implements Comparator<Offering> {

    public int compare(Offering first, Offering second){
        if(first.getPrice() < second.getPrice()){
            return -1;
        } else {
            if(first.getPrice() == second.getPrice()){
                return 0;
            } else {
                return 1;
            }
        }
    }
}
