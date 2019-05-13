package ru.ncedu.logistics.repository;

import ru.ncedu.logistics.model.entity.ProductEntity;
import ru.ncedu.logistics.service.DatabaseConnection;

import java.sql.*;

public class ProductRepository implements CRUD<ProductEntity, Integer> {

    public ProductEntity create(ProductEntity obj){
        try{
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("INSERT INTO products(name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, obj.getName());

            stm.executeUpdate();

            ResultSet resultSet = stm.getGeneratedKeys();
            if(resultSet.next()){
                obj.setId(resultSet.getInt(1));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    public ProductEntity update(ProductEntity obj){
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("UPDATE products SET name = ? WHERE id = ?");
            stm.setString(1, obj.getName());
            stm.setInt(2, obj.getId());
            ResultSet resultSet = stm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public void delete(ProductEntity obj){
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("DELETE FROM products WHERE name = ?");
            stm.setString(1, obj.getName());
            ResultSet resultSet = stm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(Integer id){
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("DELETE FROM products WHERE id = ?");
            stm.setInt(1, id);
            ResultSet resultSet = stm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ProductEntity getById(Integer id){
        ProductEntity obj = new ProductEntity();
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM products WHERE id = ?");
            stm.setInt(1, id);
            ResultSet resultSet = stm.executeQuery();
            if(resultSet.next()){
                obj.setId(resultSet.getInt("id"));
                obj.setName(resultSet.getString("name"));
            } else {
                System.out.println("This product doesn't exist!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public ProductEntity findByName(String name){
        ProductEntity obj = new ProductEntity();
        obj.setName(name);

        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT id FROM products WHERE name = ?");
            stm.setString(1, name);
            ResultSet resultSet = stm.executeQuery();
            if(resultSet.next()){
                obj.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
