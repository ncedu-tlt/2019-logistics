package ru.ncedu.logistics.repository;

import ru.ncedu.logistics.model.entity.OfferingEntity;
import ru.ncedu.logistics.model.entity.OfferingId;
import ru.ncedu.logistics.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferingRepository implements CRUD<OfferingEntity, OfferingId> {

    public OfferingEntity create(OfferingEntity obj){
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("INSERT INTO offerings(office_id, product_id, price) VALUES (?, ?, ?)");
            stm.setInt(1, obj.getId().getOfficeId());
            stm.setInt(2, obj.getId().getProductId());
            stm.setDouble(3, obj.getPrice());
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public OfferingEntity update(OfferingEntity obj){
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("UPDATE offerings SET price = ? WHERE office_id = ? AND product_id = ?");
            stm.setDouble(1, obj.getPrice());
            stm.setInt(2, obj.getId().getOfficeId());
            stm.setInt(3, obj.getId().getProductId());
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public void delete(OfferingEntity obj){
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("DELETE FROM offerings WHERE office_id = ? AND product_id = ?");
            stm.setInt(1, obj.getId().getOfficeId());
            stm.setInt(2, obj.getId().getProductId());
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(OfferingId id){
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("DELETE FROM offerings WHERE office_id = ? AND product_id = ?");
            stm.setInt(1, id.getOfficeId());
            stm.setInt(2, id.getProductId());
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public OfferingEntity getById(OfferingId id){
        OfferingEntity obj = new OfferingEntity();
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM offerings WHERE office_id = ? AND product_id = ?");
            stm.setInt(1, id.getOfficeId());
            stm.setInt(2, id.getProductId());
            ResultSet resultSet = stm.executeQuery();
            if(resultSet.next()) {
                obj.setId(id);
                obj.setPrice(resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public void findOfferingsByProductId(Integer productId){
        OfferingEntity obj = new OfferingEntity();
        try {
            DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = dbc.getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM offerings WHERE product_id = ? ORDER BY price ASC");
            stm.setInt(1, productId);

            ResultSet resultSet = stm.executeQuery();
            TownRepository townRepository = new TownRepository();
            OfficeRepository officeRepository = new OfficeRepository();
            int count = 0;

            while(resultSet.next()) {
                System.out.println("Offering #"+ ++count);
                System.out.println("Town: " + townRepository.getById(officeRepository.getById(resultSet.getInt("office_id")).getTownId()).getName());
                System.out.println("Phone: " + officeRepository.getById(resultSet.getInt("office_id")).getPhone());
                System.out.println("Price: " + resultSet.getDouble("price") + '\n');
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
