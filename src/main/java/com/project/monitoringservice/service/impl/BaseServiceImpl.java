package com.project.monitoringservice.service.impl;

import com.project.monitoringservice.mapper.BaseMapper;
import com.project.monitoringservice.model.BaseEntity;
import com.project.monitoringservice.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class BaseServiceImpl<T extends BaseEntity, E extends JpaRepository<T, Integer>, M extends BaseMapper<T>>
    implements BaseService<T> {

    private final E repository;
    private final M mapper;

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Optional<T> update(Integer id, T source) {
        return repository.findById(id).map(target -> update(source, target));
    }

    private T update(T source, T target) {
        T updateResult = mapper.updateMap(source, target);
        return repository.save(updateResult);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
