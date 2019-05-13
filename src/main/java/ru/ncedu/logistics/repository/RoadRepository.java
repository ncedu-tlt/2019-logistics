package ru.ncedu.logistics.repository;

import ru.ncedu.logistics.model.entity.RoadEntity;
import ru.ncedu.logistics.model.entity.RoadId;
import ru.ncedu.logistics.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoadRepository implements CRUD<RoadEntity, RoadId> {

    public RoadEntity create(RoadEntity obj){
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("INSERT INTO roads(left_town_id, right_town_id, distance) VALUES (?, ?, ?)");
            stm.setInt(1, obj.getId().getLeftId());
            stm.setInt(2, obj.getId().getRightId());
            stm.setDouble(3, obj.getDistance());
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public RoadEntity update(RoadEntity obj){
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("UPDATE roads SET distance = ? WHERE left_town_id = ? AND right_town_id = ?");
            stm.setDouble(1, obj.getDistance());
            stm.setInt(2, obj.getId().getLeftId());
            stm.setInt(3, obj.getId().getRightId());
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public void delete(RoadEntity obj){
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("DELETE FROM roads WHERE left_town_id = ? AND right_town_id = ?");
            stm.setInt(1, obj.getId().getLeftId());
            stm.setInt(2, obj.getId().getRightId());
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(RoadId id){
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("DELETE FROM roads WHERE left_town_id = ? AND right_town_id = ?");
            stm.setInt(1, id.getLeftId());
            stm.setInt(2, id.getRightId());
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RoadEntity getById(RoadId id){
        RoadEntity obj = new RoadEntity();
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM roads WHERE left_town_id = ? AND right_town_id = ?");
            stm.setInt(1, id.getLeftId());
            stm.setInt(2, id.getRightId());
            ResultSet resultSet = stm.executeQuery();
            if(resultSet.next()){
                obj.setId(id);
                obj.setDistance(resultSet.getDouble("distance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
