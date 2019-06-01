package ru.ncedu.logistics.repository;

import ru.ncedu.logistics.model.entity.OfficeEntity;
import ru.ncedu.logistics.model.entity.TownEntity;
import ru.ncedu.logistics.service.DatabaseConnection;

import java.sql.*;

public class OfficeRepository implements CRUD<OfficeEntity, Integer> {

    private Connection connection = DatabaseConnection.getConnection();
    private static final TownRepository townRepository = new TownRepository();


    public OfficeEntity create(OfficeEntity obj) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO offices(phone,town_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        stm.setInt(1, obj.getPhone());
        stm.setInt(2, obj.getTownId());
        stm.execute();
        ResultSet resultSet = stm.getGeneratedKeys();
        if(resultSet.next()){
            obj.setId(resultSet.getInt(1));
        }
        stm.close();
        return obj;
    }

    public OfficeEntity update(OfficeEntity obj) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("UPDATE offices SET phone = ?, town_id = ? WHERE id = ?");
        stm.setInt(1, obj.getPhone());
        stm.setInt(2, obj.getTownId());
        stm.setInt(3, obj.getId());
        stm.execute();
        stm.close();
        return obj;
    }

    public void delete(OfficeEntity obj) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM offices WHERE phone = ? AND town_id = ?");
        stm.setInt(1, obj.getPhone());
        stm.setInt(2, obj.getTownId());
        stm.execute();
        stm.close();
    }

    public void deleteById(Integer id) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM offices WHERE id = ?");
        stm.setInt(1, id);
        stm.execute();
        stm.close();
    }

    public OfficeEntity getById(Integer id) throws SQLException {
        OfficeEntity obj = new OfficeEntity();
        obj.setId(id);
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM offices WHERE id = ?");
        stm.setInt(1, id);
        ResultSet resultSet = stm.executeQuery();
        if(resultSet.next()){
            obj.setPhone(resultSet.getInt("phone"));
            obj.setTownId(resultSet.getInt("town_id"));
        }
        stm.close();
        return obj;
    }

    public OfficeEntity findByTownNameAndPhone(String name, int phone) throws SQLException {
        OfficeEntity obj = new OfficeEntity();

        obj.setTownId(townRepository.findByName(name).getId());
        obj.setPhone(phone);

        PreparedStatement stm = connection.prepareStatement("SELECT id FROM offices WHERE town_id = ? AND phone = ?");
        stm.setInt(1, obj.getTownId());
        stm.setInt(2, obj.getPhone());
        ResultSet resultSet = stm.executeQuery();
        if(resultSet.next()){
            obj.setId(resultSet.getInt(1));
        }
        stm.close();
        return obj;
    }

    public void showOfficeInfo(String townName) throws SQLException {
        TownEntity town = townRepository.findByName(townName);

        if (town.getId() != null) {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM offices WHERE town_id = ?");
            stm.setInt(1, town.getId());
            ResultSet resultSet = stm.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                System.out.println("Office #" + ++count);
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Phone: " + resultSet.getInt("phone") + '\n');
            }
            stm.close();
        } else {
            System.out.println("Error! This town doesn't exist.");
            return;
        }
    }
}
