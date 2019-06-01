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
    private static final ProductRepository productRepository = new ProductRepository();

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
        if(resultSet.next()) {
            obj.setId(id);
            obj.setPrice(resultSet.getDouble("price"));
        }
        stm.close();
        return obj;
    }

    public void findOfferingsByProductId(Integer productId) throws SQLException{
        OfferingEntity obj = new OfferingEntity();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM offerings WHERE product_id = ? ORDER BY price ASC");
        stm.setInt(1, productId);

        ResultSet resultSet = stm.executeQuery();

        int count = 0;

        while(resultSet.next()) {
            System.out.println("Offering #"+ ++count);
            System.out.println("Town: " + townRepository.getById(officeRepository.getById(resultSet.getInt("office_id")).getTownId()).getName());
            System.out.println("Phone: " + officeRepository.getById(resultSet.getInt("office_id")).getPhone());
            System.out.println("Price: " + resultSet.getDouble("price") + '\n');
        }
        stm.close();
    }

    public OfferingEntity findMinOffer(int townId, String productName) throws SQLException{
        OfferingEntity obj = null;

        int productId = productRepository.findByName(productName).getId();
        OfferingId offeringId = new OfferingId();
        offeringId.setProductId(productId);

        PreparedStatement stm = connection.prepareStatement("SELECT DISTINCT office_id, price FROM offerings " +
                                                            "JOIN offices ON(offerings.office_id = offices.id) " +
                                                            "WHERE offerings.product_id = ? AND offices.town_id = ? " +
                                                            "ORDER BY price ASC LIMIT 1");

        stm.setInt(1, productId);
        stm.setInt(2, townId);

        ResultSet resultSet = stm.executeQuery();
        if(!resultSet.isBeforeFirst()){
            return obj;
        }

        obj = new OfferingEntity();
        while(resultSet.next()){
            offeringId.setOfficeId(resultSet.getInt("office_id"));
            obj.setPrice(resultSet.getDouble("price"));
        }
        stm.close();
        obj.setId(offeringId);
        return obj;
    }
}
