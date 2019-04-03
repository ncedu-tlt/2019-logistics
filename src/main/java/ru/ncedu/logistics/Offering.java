package ru.ncedu.logistics;

import java.util.Scanner;

public class Offering {
    private Product product;
    private double price;

    public Offering() {
        this.product = Main.addProduct();
        System.out.print("Enter product's price: ");
        Scanner sc = new Scanner(System.in);
        this.price = sc.nextDouble();
    }

    public Offering(Product product, double price) {
        this.product = product;
        this.price = price;
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
