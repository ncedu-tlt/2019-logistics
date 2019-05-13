package ru.ncedu.logistics.repository;

import ru.ncedu.logistics.model.entity.TownEntity;
import ru.ncedu.logistics.service.DatabaseConnection;

import java.sql.*;

public class TownRepository implements CRUD<TownEntity, Integer> {

    private Connection connection = DatabaseConnection.getConnection();


    public TownEntity create(TownEntity obj) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO towns(name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, obj.getName());
        stm.execute();
        ResultSet resultSet = stm.getGeneratedKeys();
        stm.close();
        if(resultSet.next()){
            obj.setId(resultSet.getInt(1));
        }
        return obj;
    }

    public TownEntity update(TownEntity obj) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("UPDATE towns SET name = ? WHERE id = ?");
        stm.setString(1, obj.getName());
        stm.setInt(2, obj.getId());
        stm.execute();
        stm.close();
        return obj;
    }

    public void delete(TownEntity obj) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM towns WHERE name = ?");
        stm.setString(1, obj.getName());
        stm.execute();
        stm.close();
    }

    public void deleteById(Integer id) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM towns WHERE id = ?");
        stm.setInt(1, id);
        stm.execute();
        stm.close();
    }

    public TownEntity getById(Integer id) throws SQLException {
        TownEntity obj = new TownEntity();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM towns WHERE id = ?");
        stm.setInt(1, id);
        ResultSet resultSet = stm.executeQuery();
        stm.close();
        if(resultSet.next()){
                obj.setName(resultSet.getString("name"));
                obj.setId(resultSet.getInt("id"));
        }
        return obj;
    }

    public TownEntity findByName(String name) throws SQLException {
        TownEntity obj = new TownEntity();
        obj.setName(name);
        PreparedStatement stm = connection.prepareStatement("SELECT id FROM towns WHERE name = ?");
        stm.setString(1, name);
        ResultSet resultSet = stm.executeQuery();
        stm.close();
        if(resultSet.next()){
            obj.setId(resultSet.getInt(1));
        }
        return obj;
    }

    public void importFromFile(String string) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO towns(name) VALUES (?)");
        String[] newTowns = string.split(" ");
        for (String el : newTowns) {
            stm.setString(1, el);
            stm.addBatch();
        }
        stm.executeBatch();
        stm.close();
    }
}
