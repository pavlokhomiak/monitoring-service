package com.project.monitoringservice.service;

import com.project.monitoringservice.model.BaseEntity;
import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseEntity> {

    List<T> findAll();

    T save(T entity);

    Optional<T> findById(Integer id);

    Optional<T> update(Integer id, T source);

    void deleteById(Integer id);
}
