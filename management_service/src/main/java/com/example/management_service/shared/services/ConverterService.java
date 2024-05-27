package com.example.management_service.shared.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

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

    public static <ENTITY extends BaseEntity> Converter<Set<Long>, Set<ENTITY>> idsToEntities(
            Supplier<ENTITY> entitySupplier) {
        return new Converter<Set<Long>, Set<ENTITY>>() {
            @Override
            public Set<ENTITY> convert(MappingContext<Set<Long>, Set<ENTITY>> context) {
                return context.getSource().stream()
                        .map(id -> {
                            ENTITY entity = entitySupplier.get();
                            entity.setId(id);
                            return entity;
                        })
                        .collect(Collectors.toSet());
            }
        };
    }

    public static <ENTITY extends BaseEntity> Converter<Set<ENTITY>, Set<Long>> carEntitiesToIds() {
        return new Converter<Set<ENTITY>, Set<Long>>() {
            @Override
            public Set<Long> convert(MappingContext<Set<ENTITY>, Set<Long>> context) {
                return context.getSource().stream()
                        .map(ENTITY::getId)
                        .collect(Collectors.toSet());
            }
        };
    }

}