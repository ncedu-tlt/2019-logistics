package ru.ncedu.logistics.repository;

import ru.ncedu.logistics.model.Product;
import ru.ncedu.logistics.model.entity.ProductEntity;
import ru.ncedu.logistics.service.DatabaseConnection;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ProductRepository implements CRUD<ProductEntity, Integer> {

    private Connection connection = DatabaseConnection.getConnection();

    public ProductEntity create(ProductEntity obj) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO products(name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, obj.getName());
        stm.execute();
        ResultSet resultSet = stm.getGeneratedKeys();
        if(resultSet.next()){
            obj.setId(resultSet.getInt(1));
        }
        stm.close();
        return obj;
    }

    public ProductEntity update(ProductEntity obj) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("UPDATE products SET name = ? WHERE id = ?");
        stm.setString(1, obj.getName());
        stm.setInt(2, obj.getId());
        stm.execute();
        stm.close();
        return obj;
    }

    public void delete(ProductEntity obj) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM products WHERE name = ?");
        stm.setString(1, obj.getName());
        stm.execute();
        stm.close();
    }

    public void deleteById(Integer id) throws SQLException {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM products WHERE id = ?");
            stm.setInt(1, id);
            stm.execute();
            stm.close();
    }

    public ProductEntity getById(Integer id) throws SQLException {
        ProductEntity obj = new ProductEntity();
        obj.setId(id);
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM products WHERE id = ?");
        stm.setInt(1, id);
        ResultSet resultSet = stm.executeQuery();
        if(resultSet.next()){
            obj.setName(resultSet.getString("name"));
        }
        stm.close();
        return obj;
    }

    public ProductEntity findByName(String name) throws SQLException {
        ProductEntity obj = new ProductEntity();
        obj.setName(name);
        PreparedStatement stm = connection.prepareStatement("SELECT id FROM products WHERE name = ?");
        stm.setString(1, name);
        ResultSet resultSet = stm.executeQuery();
        if(resultSet.next()){
            obj.setId(resultSet.getInt(1));
        }
        stm.close();
        return obj;
    }

    public void importFromFile(String string) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO products(name) VALUES (?)");
        String[] newProducts = string.split(" ");
        for (String el : newProducts) {
            stm.setString(1, el);
            stm.addBatch();
        }
        stm.executeBatch();
        stm.close();
    }

    public List<Product> getPageSortByIdAsc(PageRequest page) throws SQLException {
        List<Product> obj = new LinkedList<>();
        PreparedStatement stm = connection.prepareStatement("SELECT name FROM products ORDER BY id ASC LIMIT ? OFFSET ?");
        stm.setInt(1, page.getLimit());
        stm.setInt(2, page.getOffset());
        ResultSet resultSet = stm.executeQuery();
        while(resultSet.next()){
            obj.add(new Product(resultSet.getString(1)));
        }
        stm.close();
        return obj;
    }
}
