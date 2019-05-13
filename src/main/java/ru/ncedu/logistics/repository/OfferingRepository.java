package ru.ncedu.logistics.repository;

import ru.ncedu.logistics.model.entity.OfferingEntity;
import ru.ncedu.logistics.model.entity.OfferingId;
import ru.ncedu.logistics.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferingRepository implements CRUD<OfferingEntity, OfferingId> {

    private Connection connection = DatabaseConnection.getConnection();
    private static final TownRepository townRepository = new TownRepository();
    private static final OfficeRepository officeRepository = new OfficeRepository();

    public OfferingEntity create(OfferingEntity obj) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO offerings(office_id, product_id, price) VALUES (?, ?, ?)");
        stm.setInt(1, obj.getId().getOfficeId());
        stm.setInt(2, obj.getId().getProductId());
        stm.setDouble(3, obj.getPrice());
        stm.execute();
        stm.close();
        return obj;
    }

    public OfferingEntity update(OfferingEntity obj) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("UPDATE offerings SET price = ? WHERE office_id = ? AND product_id = ?");
        stm.setDouble(1, obj.getPrice());
        stm.setInt(2, obj.getId().getOfficeId());
        stm.setInt(3, obj.getId().getProductId());
        stm.execute();
        stm.close();
        return obj;
    }

    public void delete(OfferingEntity obj) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM offerings WHERE office_id = ? AND product_id = ?");
        stm.setInt(1, obj.getId().getOfficeId());
        stm.setInt(2, obj.getId().getProductId());
        stm.execute();
        stm.close();
    }

    public void deleteById(OfferingId id) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM offerings WHERE office_id = ? AND product_id = ?");
        stm.setInt(1, id.getOfficeId());
        stm.setInt(2, id.getProductId());
        stm.execute();
        stm.close();
    }

    public OfferingEntity getById(OfferingId id) throws SQLException {
        OfferingEntity obj = new OfferingEntity();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM offerings WHERE office_id = ? AND product_id = ?");
        stm.setInt(1, id.getOfficeId());
        stm.setInt(2, id.getProductId());
        ResultSet resultSet = stm.executeQuery();
        stm.close();
        if(resultSet.next()) {
             obj.setId(id);
             obj.setPrice(resultSet.getDouble("price"));
        }
        return obj;
    }

    public void findOfferingsByProductId(Integer productId) throws SQLException{
        OfferingEntity obj = new OfferingEntity();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM offerings WHERE product_id = ? ORDER BY price ASC");
        stm.setInt(1, productId);

        ResultSet resultSet = stm.executeQuery();
        stm.close();

        int count = 0;

        while(resultSet.next()) {
            System.out.println("Offering #"+ ++count);
            System.out.println("Town: " + townRepository.getById(officeRepository.getById(resultSet.getInt("office_id")).getTownId()).getName());
            System.out.println("Phone: " + officeRepository.getById(resultSet.getInt("office_id")).getPhone());
            System.out.println("Price: " + resultSet.getDouble("price") + '\n');
        }
    }
}
