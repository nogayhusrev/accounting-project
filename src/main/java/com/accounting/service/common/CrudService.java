package com.accounting.service.common;

import java.util.List;

public interface CrudService<T, ID> {


    T findById(ID id);
    List<T> findAll();
    T save(T t);
    void delete(T t);
    void update(T t);

}

