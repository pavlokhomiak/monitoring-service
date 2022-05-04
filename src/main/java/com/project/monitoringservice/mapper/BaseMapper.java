package com.project.monitoringservice.mapper;

public abstract class BaseMapper<T> {

    public abstract T updateMap(T source, T target);
}
