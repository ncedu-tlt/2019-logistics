package ru.ncedu.logistics.api.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ru.ncedu.logistics.api.dto.OfficeDTO;
import ru.ncedu.logistics.api.entity.OfficeEntity;
import ru.ncedu.logistics.api.exception.OfferingExistsException;
import ru.ncedu.logistics.api.exception.OfferingNotFoundException;
import ru.ncedu.logistics.api.exception.OfficeNotFoundException;
import ru.ncedu.logistics.api.repository.OfficeRepository;
import ru.ncedu.logistics.api.service.OfficeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.lookup;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;
    private final MongoTemplate mongoTemplate;

    private static class Count {
        private long total;
    }

    @Override
    public OfficeDTO create(OfficeDTO office) {
        return new OfficeDTO(officeRepository.save(new OfficeEntity(office)));
    }

    @Override
    public OfficeDTO update(String id, OfficeDTO office) throws OfficeNotFoundException {
        if(!officeRepository.existsById(id)){
            throw new OfficeNotFoundException(id);
        }
        office.setId(id);
        return new OfficeDTO(officeRepository.save(new OfficeEntity(office)));
    }

    @Override
    public void delete(String id) {
        officeRepository.deleteById(id);
    }

    private Query buildQuery(String nameRegex){
        Query query = new Query();
        if(!StringUtils.isEmpty(nameRegex)){
            query.addCriteria(Criteria.where("name").regex(nameRegex, "i"));
        }
        return query;
    }

    @Override
    public List<OfficeDTO> find(String nameRegex, Pageable pageable, List<String> fields) {
        Query query = buildQuery(nameRegex).with(pageable);
        if(CollectionUtils.isEmpty(fields)){
            fields.forEach(query.fields()::include);
        }
        return mongoTemplate.find(query, OfficeEntity.class).stream().map(OfficeDTO::new).collect(Collectors.toList());
    }

    @Override
    public long count(String nameRegex) {
        return mongoTemplate.count(buildQuery(nameRegex), OfficeEntity.class);
    }

    @Override
    public long countOfficesInTown(String name) {
        Aggregation agg = Aggregation.newAggregation(
                lookup("towns", "townId", "_id", "foundTown"),
                match(Criteria.where("foundTown.name").is(name)),
                Aggregation.count().as("total")
        );
        List<Count> result = mongoTemplate.aggregate(agg, "offices", Count.class).getMappedResults();
        if (!CollectionUtils.isEmpty(result)){
            return result.get(0).total;
        }
        return 0;
    }

    @Override
    public OfficeDTO getById(String id) {
        return officeRepository.findById(id).map(OfficeDTO::new).orElseThrow(() -> new OfficeNotFoundException(id));
    }

    public boolean offeringExists(String officeId, OfficeEntity.Offering offering){
        return Optional.ofNullable(getById(officeId).getOfferings()).
                map(offerings -> offerings.contains(offering)).
                orElse(false);
    }

    @Override
    public OfficeDTO addOffering(String officeId, OfficeEntity.Offering offering) throws OfferingExistsException{
        if(offeringExists(officeId, offering)){
            throw new OfferingExistsException(officeId);
        }
        OfficeDTO office = getById(officeId);
        office.getOfferings().add(offering);
        return update(officeId, office);
    }

    @Override
    public OfficeDTO updateOffering(String officeId, OfficeEntity.Offering offering) throws OfferingNotFoundException{
        if(!offeringExists(officeId, offering)){
            throw new OfferingNotFoundException(officeId);
        }

        OfficeDTO office = getById(officeId);
        for(OfficeEntity.Offering offer : office.getOfferings()){
            if(offer.equals(offering)){
                offer.setPrice(offering.getPrice());
                break;
            }
        }

        return update(officeId, office);
    }

    @Override
    public OfficeDTO removeOffering(String officeId, OfficeEntity.Offering offering){
        OfficeDTO office = getById(officeId);
        if(!offeringExists(officeId, offering)){
            office.getOfferings().remove(offering);
        }
        return update(officeId, office);
    }
}
