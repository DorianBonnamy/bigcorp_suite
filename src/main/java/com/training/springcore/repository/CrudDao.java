package com.training.springcore.repository;

import java.util.List;

//CRUD = Create Read Update Delete
public interface CrudDao<T,ID> {
    // Create
    void persist(T element);
    // Read
    T findById(ID id);
    List<T> findAll();
    // Delete
    void delete(T id);





}
