package ru.ncedu.logistics.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class Product implements Serializable {
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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getName().equals(product.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
