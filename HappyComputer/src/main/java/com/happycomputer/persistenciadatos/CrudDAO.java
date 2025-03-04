package com.happycomputer.persistenciadatos;

import java.sql.SQLException;
import java.util.List;

public abstract class CrudDAO<T> {
    public abstract T insert(T entity) throws SQLException;
    public abstract void update(T entity) throws SQLException;
    public abstract void delete(Integer id) throws SQLException;
    public abstract T findById(Integer id) throws SQLException;
    public abstract List<T> findAll() throws SQLException;
}
