package ru.ncedu.logistics.repository;

import ru.ncedu.logistics.model.entity.OfficeEntity;
import ru.ncedu.logistics.model.entity.TownEntity;
import ru.ncedu.logistics.service.DatabaseConnection;

import java.sql.*;

public class OfficeRepository implements CRUD<OfficeEntity, Integer> {

    public OfficeEntity create(OfficeEntity obj){
        try{
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("INSERT INTO offices(phone,town_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, obj.getPhone());
            stm.setInt(2, obj.getTownId());

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

    public OfficeEntity update(OfficeEntity obj){
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("UPDATE offices SET phone = ?, town_id = ? WHERE id = ?");
            stm.setInt(1, obj.getPhone());
            stm.setInt(2, obj.getTownId());
            stm.setInt(3, obj.getId());
            stm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public void delete(OfficeEntity obj){
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("DELETE FROM offices WHERE phone = ? AND town_id = ?");
            stm.setInt(1, obj.getPhone());
            stm.setInt(2, obj.getTownId());
            stm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(Integer id){
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("DELETE FROM offices WHERE id = ?");
            stm.setInt(1, id);
            stm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public OfficeEntity getById(Integer id){
        OfficeEntity obj = new OfficeEntity();
        obj.setId(id);
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM offices WHERE id = ?");
            stm.setInt(1, id);
            ResultSet resultSet = stm.executeQuery();
            if(resultSet.next()){
                obj.setPhone(resultSet.getInt("phone"));
                obj.setTownId(resultSet.getInt("town_id"));
            } else {
                System.out.println("This office doesn't exist!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public OfficeEntity findByTownNameAndPhone(String name, int phone){
        OfficeEntity obj = new OfficeEntity();

        TownRepository townRepository = new TownRepository();
        TownEntity town = townRepository.findByName(name);

        obj.setTownId(town.getId());
        obj.setPhone(phone);

        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT id FROM offices WHERE town_id = ? AND phone = ?");
            stm.setInt(1, obj.getTownId());
            stm.setInt(2, obj.getPhone());
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
