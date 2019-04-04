package ru.ncedu.logistics.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Office {
    private Town town;
    private String phone;
    private List<Offering> offerings = new LinkedList<>();

    public Office(){}

    public Office(Town t, String phone) {
        this.town = t;
        this.phone = phone;
    }

    public Office(Town t, String phone, List<Offering> offerings){
        this.town = t;
        this.phone = phone;
        this.offerings = offerings;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Offering> getOfferings() {
        return offerings;
    }

    public void setOfferings(List<Offering> offerings) {
        this.offerings = offerings;
    }

    public void setOfferings(){
        System.out.print("\nOffice of town "+town.getName()+".\n" +
                "Initializing of offerings. Go(1) Stop(0) : ");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        while(x == 1) {
            offerings.add(new Offering());
            System.out.print("\nOfferings. Go(1) Stop(0) : ");
            x = sc.nextInt();
        }
    }

    public void addOffering(Offering of){
        offerings.add(of);
    }

    public void showOfficeInfo(){
        System.out.println("Town: " + town.getName());
        System.out.println("Phone number: " + getPhone());

        int numberOffering = 0;
        for(Offering element: getOfferings()){
            System.out.println("Offering №" + ++numberOffering);
            System.out.println("Product: " + element.getProduct().getName());
            System.out.println("Price: " + element.getPrice());
        }
    }

}
