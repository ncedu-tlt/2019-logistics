package ru.ncedu.logistics.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.ncedu.logistics.api.entity.OfficeEntity;

public interface OfficeRepository extends MongoRepository<OfficeEntity, String> {
}
