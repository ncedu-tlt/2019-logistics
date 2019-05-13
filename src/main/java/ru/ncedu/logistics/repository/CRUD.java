package ru.ncedu.logistics.repository;

import java.sql.SQLException;

public interface CRUD<T, ID> {
    T create(T obj) throws SQLException;
    T update(T obj) throws SQLException;
    void delete(T obj) throws SQLException;
    void deleteById(ID id) throws SQLException;
    T getById(ID id) throws SQLException;
}
