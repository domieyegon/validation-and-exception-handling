package com.unify.validation.service.mapper;

import java.util.List;

public interface EntityMapper<D, E> {

    D toDto(E entity);
    E toEntity(D dto);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);
}
