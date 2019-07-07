package ru.ncedu.logistics.service;

import ru.ncedu.logistics.data.dao.TownDAO;
import ru.ncedu.logistics.data.entity.TownEntity;
import ru.ncedu.logistics.dto.TownDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Stateless
public class TownService {


    private static final String[] PREFIX = {"Amsterdam", "Barselona", "Brugge", "Washington", "Vena",
                                            "Babol", "Dabgram", "Dakar", "Fargo", "Fasa",
                                            "Gagnoa", "Garissa", "Habra", "Havana", "Jabalpur",
                                            "Jammu", "Jena", "Kaduna", "Kaesong", "Karaj",
                                            "Kikwit", "Koga", "Madison", "Magway", "Manama"};

    private static final String[] ROOT = {"Abakan", "Abadan", "Aden", "Aleppo", "Amagasaki",
                                          "Qom", "Quchan", "Rabat", "Reno", "Fairfield",
                                          "Flint", "Foshan", "Gaya", "Getafe", "Gifu",
                                          "Herne", "Ichihara", "Riga", "Rostock", "Rubtsovsk",
                                          "Ufa", "Uji", "Ulm", "Rzeszow", "Rudny"};


    @Inject
    private TownDAO townDAO;

    public TownDTO create(TownDTO town) {
        TownEntity townEntity = toTownEntity(town);
        townDAO.create(townEntity);
        return toTownDTO(townEntity);
    }

    public TownDTO update(TownDTO town) {
        TownEntity townEntity = toTownEntity(town);
        townEntity = townDAO.update(townEntity);
        return toTownDTO(townEntity);
    }

    public void delete(TownDTO town) {
        TownEntity townEntity = toTownEntity(town);
        townDAO.delete(townEntity);
    }

    public void deleteById(int townId){
        townDAO.deleteById(townId);
    }

    public List<TownDTO> findAll() {
        return townDAO.findAll().stream().map(this::toTownDTO).collect(Collectors.toList());
    }

    public TownDTO findById(int townId){
        return toTownDTO(townDAO.findById(townId));
    }

    public TownDTO findByName(String name){
        return toTownDTO(townDAO.findByName(name));
    }

    public boolean existsByName(String name){
        return townDAO.existsByName(name);
    }

    public boolean existsById(int id){
        return townDAO.existById(id);
    }

    public TownDTO toTownDTO(TownEntity townEntity) {
        TownDTO townDTO = new TownDTO();
        townDTO.setId(townEntity.getId());
        townDTO.setName(townEntity.getName());
        return townDTO;
    }

    public TownEntity toTownEntity(TownDTO townDTO) {
        TownEntity townEntity = new TownEntity();
        townEntity.setId(townDTO.getId());
        townEntity.setName(townDTO.getName());
        return townEntity;
    }

    public String getRandomName(){
        Random rm = new Random();
        int pos1 = rm.nextInt(PREFIX.length);
        int pos2 = rm.nextInt(ROOT.length);

        return PREFIX[pos1] + "-" + ROOT[pos2];
    }

    public int getRandomTownId(){
        Random rm = new Random();
        List<TownDTO> towns = findAll();
        int pos = rm.nextInt(towns.size());
        return towns.get(pos).getId();
    }
}
