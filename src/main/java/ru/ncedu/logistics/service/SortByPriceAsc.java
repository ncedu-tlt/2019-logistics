package ru.ncedu.logistics.service;

import ru.ncedu.logistics.model.Offering;

import java.util.Comparator;

public class SortByPriceAsc implements Comparator<Offering> {

    public int compare(Offering first, Offering second){
        return Double.compare(first.getPrice(), second.getPrice());
    }
}
