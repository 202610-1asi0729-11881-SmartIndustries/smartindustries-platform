package com.smartindustries.smartlock.platform.shared.infrastructure.persistence.jpa.converters;

import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.IdentityDocument;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class IdentityDocumentPersistenceConverter implements AttributeConverter<IdentityDocument, String> {

    @Override
    public String convertToDatabaseColumn(IdentityDocument attribute) {
        return attribute == null ? null : attribute.value();
    }

    @Override
    public IdentityDocument convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new IdentityDocument(dbData);
    }
}
