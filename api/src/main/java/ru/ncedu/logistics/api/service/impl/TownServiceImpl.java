package ru.ncedu.logistics.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ru.ncedu.logistics.api.dto.TownDTO;
import ru.ncedu.logistics.api.entity.TownEntity;
import ru.ncedu.logistics.api.exception.TownNotFoundException;
import ru.ncedu.logistics.api.repository.TownRepository;
import ru.ncedu.logistics.api.service.TownService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public TownDTO create(TownDTO town) {
        return new TownDTO(townRepository.save(new TownEntity(town)));
    }

    @Override
    public TownDTO update(String id, TownDTO town) throws TownNotFoundException {
        if(!townRepository.existsById(id)) {
            throw new TownNotFoundException(id);
        }
        town.setId(id);
        return new TownDTO(townRepository.save(new TownEntity(town)));
    }

    @Override
    public void delete(String id) {
        townRepository.deleteById(id);
    }

    private Query buildQuery(String nameRegex) {
        Query query = new Query();
        if(!StringUtils.isEmpty(nameRegex)) {
            query.addCriteria(Criteria.where("name").regex(nameRegex, "i"));
        }
        return query;
    }

    @Override
    public List<TownDTO> find(String nameRegex, Pageable pageable, List<String> fields) {
        Query query = buildQuery(nameRegex).with(pageable);
        if(!CollectionUtils.isEmpty(fields)) {
            fields.forEach(query.fields()::include);
        }
        return mongoTemplate.find(query, TownEntity.class)
                .stream().map(TownDTO::new).collect(Collectors.toList());
    }

    @Override
    public long count(String nameRegex) {
        return mongoTemplate.count(buildQuery(nameRegex), TownEntity.class);
    }

    @Override
    public TownDTO getById(String id) throws TownNotFoundException {
        return townRepository.findById(id).map(TownDTO::new).orElseThrow(() -> new TownNotFoundException(id));
    }
}
