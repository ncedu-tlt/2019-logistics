package ru.ncedu.logistics;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Town{
    private String name;
    private List<Office> offices = new LinkedList<>();

    public Town(){
        System.out.print("\nEnter town's name: ");
        Scanner sc = new Scanner(System.in);
        this.name = sc.nextLine();
        setOffices();
    }

    public Town(String name){
        this.name = name;
    }

    public Town(String name, List<Office> office){
        this.name = name;
        this.offices = office;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

    public void setOffices() {
        System.out.print("\nInitializing "+getName()+"'s offices. Go(1) Stop(0) : ");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        sc.nextLine(); //skip '\n' after int
        while(x == 1) {
            System.out.print("Enter office's phone number: ");
            offices.add(new Office(this, sc.nextLine())); //sc.nextLine - String Office::phone
            System.out.print("Offices. Go(1) Stop(0) : ");
            x = sc.nextInt();
        }
    }

    public void addOffice(Office office){
        offices.add(office);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Town town = (Town) o;
        return getName().equals(town.getName()) &&
                offices.equals(town.offices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), offices);
    }
}

