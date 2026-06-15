package com.smartindustries.smartlock.platform.shared.infrastructure.persistence.jpa.converters;

import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.GenericName;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class GenericNamePersistenceConverter implements AttributeConverter<GenericName, String> {

    @Override
    public String convertToDatabaseColumn(GenericName attribute) {
        return attribute == null ? null : attribute.value();
    }

    @Override
    public GenericName convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new GenericName(dbData);
    }
}
