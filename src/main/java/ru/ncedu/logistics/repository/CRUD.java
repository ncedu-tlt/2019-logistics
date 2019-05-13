package ru.ncedu.logistics.repository;

public interface CRUD<T, ID> {
    T create(T obj);
    T update(T obj);
    void delete(T obj);
    void deleteById(ID id);
    T getById(ID id);
}
