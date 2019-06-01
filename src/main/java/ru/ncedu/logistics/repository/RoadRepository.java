package ru.ncedu.logistics.repository;

import ru.ncedu.logistics.model.entity.RoadEntity;
import ru.ncedu.logistics.model.entity.RoadId;
import ru.ncedu.logistics.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RoadRepository implements CRUD<RoadEntity, RoadId> {

    private Connection connection = DatabaseConnection.getConnection();

    public RoadEntity create(RoadEntity obj) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO roads(left_town_id, right_town_id, distance) VALUES (?, ?, ?)");
        stm.setInt(1, obj.getId().getLeftId());
        stm.setInt(2, obj.getId().getRightId());
        stm.setDouble(3, obj.getDistance());
        stm.execute();
        stm.close();
        return obj;
    }

    public RoadEntity update(RoadEntity obj) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("UPDATE roads SET distance = ? WHERE left_town_id = ? AND right_town_id = ?");
        stm.setDouble(1, obj.getDistance());
        stm.setInt(2, obj.getId().getLeftId());
        stm.setInt(3, obj.getId().getRightId());
        stm.execute();
        stm.close();
        return obj;
    }

    public void delete(RoadEntity obj) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM roads WHERE left_town_id = ? AND right_town_id = ?");
        stm.setInt(1, obj.getId().getLeftId());
        stm.setInt(2, obj.getId().getRightId());
        stm.execute();
        stm.close();
    }

    public void deleteById(RoadId id) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM roads WHERE left_town_id = ? AND right_town_id = ?");
        stm.setInt(1, id.getLeftId());
        stm.setInt(2, id.getRightId());
        stm.execute();
        stm.close();
    }

    public RoadEntity getById(RoadId id) throws SQLException {
        RoadEntity obj = new RoadEntity();
        obj.setId(id);
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM roads WHERE left_town_id = ? AND right_town_id = ?");
        stm.setInt(1, id.getLeftId());
        stm.setInt(2, id.getRightId());
        ResultSet resultSet = stm.executeQuery();
        if(resultSet.next()){
            obj.setDistance(resultSet.getDouble("distance"));
        }
        stm.close();
        return obj;
    }

    public List<RoadEntity> findRoads(int currentTownId) throws SQLException {
        List<RoadEntity> newRoads = new LinkedList<>();

        PreparedStatement stm = connection.prepareStatement("SELECT * FROM roads WHERE left_town_id = ? OR right_town_id = ?");
        stm.setInt(1, currentTownId);
        stm.setInt(2, currentTownId);
        ResultSet resultSet = stm.executeQuery();

        while(resultSet.next()){
            RoadEntity obj = new RoadEntity();
            RoadId roadId = new RoadId();

            roadId.setLeftId(resultSet.getInt("left_town_id"));
            roadId.setRightId(resultSet.getInt("right_town_id"));

            obj.setId(roadId);
            obj.setDistance(resultSet.getDouble("distance"));
            newRoads.add(obj);
        }
        stm.close();
        return newRoads;
    }
}
