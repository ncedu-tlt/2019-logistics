package ru.ncedu.logistics.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"ru.ncedu.logistics.api.entity", "ru.ncedu.logistics.api.repository"})
public class DataConfig {

}
