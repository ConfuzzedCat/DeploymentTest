package dk.lyngby.dao;

import dk.lyngby.exception.ApiException;

import java.util.List;

public interface IDao<T, D> {

    T read(D d) throws ApiException;
    List<T> readAll() throws ApiException;
    T create(T t) throws ApiException;
    T update(D d, T t) throws ApiException;
    void delete(D d) throws ApiException;

}
