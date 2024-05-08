package com.example.management_service.shared.services;

import org.modelmapper.Converter;

import com.example.management_service.shared.entities.BaseEntity;
import com.google.common.base.Supplier;

public class ConverterService {
    public static <ENTITY extends BaseEntity> Converter<Long, ENTITY> idToEntity(Supplier<ENTITY> entitySupplier) {
        return mappingContext -> {
            Long id = mappingContext.getSource();
            ENTITY entity = entitySupplier.get();
            if (id != null) {
                entity.setId(id);
                return entity;
            }
            return null;
        };
    }
}