package ru.ncedu.logistics.model;

import java.util.Scanner;

public class Product {
    private String name;

    public Product() {
        System.out.print("Enter name of product: ");
        Scanner sc = new Scanner(System.in);
        this.name = sc.nextLine();

    }

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setProduct(String product) {
        this.name = name;
    }


}
