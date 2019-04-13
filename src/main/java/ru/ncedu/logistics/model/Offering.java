package ru.ncedu.logistics.model;

import java.io.Serializable;
import java.util.Scanner;

public class Offering implements Serializable {
    private Office office;
    private Product product;
    private double price;

    public Offering() {
        this.product = new Product();
        System.out.print("Enter product's price: ");
        Scanner sc = new Scanner(System.in);
        this.price = sc.nextDouble();
    }

    public Offering(Office office, Product product, double price) {
        this.office = office;
        this.product = product;
        this.price = price;
    }

    public void setOffice(Office office){
        this.office = office;
    }

    public Office getOffice(){
        return office;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
