package com.vnsd.business.service.mapper;

import com.vnsd.business.domain.Company;
import com.vnsd.business.domain.User;
import com.vnsd.business.service.dto.CompanyDTO;

import java.time.Instant;
import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */

public interface EntityMapper <D, E> {

   // E toEntity(D dto);

    D toDto(E entity);

    //List <E> toEntity(List<D> dtoList);

    List <D> toDto(List<E> entityList);

    void update(E e, D d, User updatedBy, Instant updatedAt);
}
