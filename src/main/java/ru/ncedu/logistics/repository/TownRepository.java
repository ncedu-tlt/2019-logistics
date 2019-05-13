package ru.ncedu.logistics.repository;

import ru.ncedu.logistics.model.entity.TownEntity;
import ru.ncedu.logistics.service.DatabaseConnection;

import java.sql.*;

public class TownRepository implements CRUD<TownEntity, Integer> {

    public TownEntity create(TownEntity obj){
        try{
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("INSERT INTO towns(name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, obj.getName());

            stm.execute();

            ResultSet resultSet = stm.getGeneratedKeys();
            if(resultSet.next()){
                obj.setId(resultSet.getInt(1));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    public TownEntity update(TownEntity obj){
        try{
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("UPDATE towns SET name = ? WHERE id = ?");
            stm.setString(1, obj.getName());
            stm.setInt(2, obj.getId());
            stm.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    public void delete(TownEntity obj){
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("DELETE FROM towns WHERE name = ?");
            stm.setString(1, obj.getName());
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(Integer id){
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("DELETE FROM towns WHERE id = ?");
            stm.setInt(1, id);
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TownEntity getById(Integer id){
        TownEntity obj = new TownEntity();
        try{
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM towns WHERE id = ?");
            stm.setInt(1, id);
            ResultSet resultSet = stm.executeQuery();
            if(resultSet.next()){
                obj.setName(resultSet.getString("name"));
                obj.setId(resultSet.getInt("id"));
            } else {
                System.out.println("This town doesn't exist!");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    public TownEntity findByName(String name) {
        TownEntity obj = new TownEntity();
        obj.setName(name);

        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT id FROM towns WHERE name = ?");
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
