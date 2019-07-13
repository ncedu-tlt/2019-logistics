package ru.ncedu.logistics.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.ncedu.logistics.api.entity.TownEntity;

public interface TownRepository extends MongoRepository<TownEntity, String> {
}
