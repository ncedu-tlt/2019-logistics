package ru.ncedu.logistics.servlet.testdata;

import ru.ncedu.logistics.data.entity.RoadId;
import ru.ncedu.logistics.dto.*;
import ru.ncedu.logistics.service.*;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

public class InitTestDataServlet extends HttpServlet {

    @Inject
    private TownService townService;

    @Inject
    private OfficeService officeService;

    @Inject
    private ProductService productService;

    @Inject
    private OfferingService offeringService;

    @Inject
    private RoadService roadService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Random rm = new Random();

        //Creating 10 new products
        //If they already exist then skip
        productService.initTestProducts();

        //Creating 10 new towns
        for(int i = 0; i < 10; ++i) {
            int townsCount = townService.findAll().size();
            int maxTownAttempts = townsCount * townsCount;
            TownDTO town = new TownDTO();

            String townName = townService.getRandomName();

            int attemptTown = 0;
            while (townService.existsByName(townName) && attemptTown++ < maxTownAttempts) {
                townName = townService.getRandomName();
            }

            //If randomize townName was successful
            if (attemptTown != maxTownAttempts) {
                town.setName(townName);
                town = townService.create(town);

                //Creating offices in new town
                for (int ii = 0; ii < 2; ++ii) {
                    OfficeDTO office = new OfficeDTO();

                    office.setTown(town);
                    office.setPhone(100000 + rm.nextInt(899999));

                    office = officeService.create(office);

                    //Creating offerings in new office
                    for (int iii = 0; iii < 2; ++iii) {
                        int productCount = productService.findAll().size();
                        int maxProductAttempts = productCount*productCount;

                        OfferingDTO offering = new OfferingDTO();
                        ProductDTO product = productService.getRandomProduct();

                        int attemptProduct = 0;
                        while (offeringService.existsById(product.getId(), office.getId()) && attemptProduct++ < maxProductAttempts) {
                            product = productService.getRandomProduct();
                        }

                        //If random product found and offer with this product doesn't exist
                        if(attemptProduct != maxProductAttempts) {
                            offering.setOffice(office);
                            offering.setProduct(product);
                            offering.setPrice(700 + rm.nextInt(1000));

                            offeringService.create(offering);
                        }
                    }

                }

                //Creating road between new town and one of the existing
                RoadId roadId = new RoadId();
                int townRoad = townService.getRandomTownId();

                if (town.getId() != townRoad) {
                    if (town.getId() < townRoad) {
                        roadId.setLeftId(town.getId());
                        roadId.setRightId(townRoad);
                    } else {
                        roadId.setLeftId(townRoad);
                        roadId.setRightId(town.getId());
                    }
                }

                //Randomize until get different towns
                int attemptRoad = 0;
                while (roadService.existsById(roadId) && attemptRoad < maxTownAttempts) {
                    townRoad = townService.getRandomTownId();

                    if (town.getId() != townRoad) {
                        if (town.getId() < townRoad) {
                            roadId.setLeftId(town.getId());
                            roadId.setRightId(townRoad);
                        } else {
                            roadId.setLeftId(townRoad);
                            roadId.setRightId(town.getId());
                        }
                    }
                }

                //If get different towns
                if(attemptRoad != maxTownAttempts) {
                    RoadDTO road = new RoadDTO();
                    road.setLeftTown(townService.findById(roadId.getLeftId()));
                    road.setRightTown(townService.findById(roadId.getRightId()));
                    road.setDistance(100 + rm.nextInt(700));
                    roadService.create(road);
                }
            }
        }
    }
}
