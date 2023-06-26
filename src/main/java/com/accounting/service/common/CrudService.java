package com.accounting.service.common;

import java.util.List;

public interface CrudService<T, ID> {


    T findById(ID id);
    List<T> findAll();
    void save(T t);
    void delete(ID id);
    void update(T t, ID id);
    boolean isExist(T t);

}

