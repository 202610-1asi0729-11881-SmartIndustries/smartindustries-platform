package com.smartindustries.smartlock.platform.shared.interfaces.rest.converters;

import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.Email;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class EmailPersistenceConverter implements AttributeConverter<Email, String> {

    @Override
    public String convertToDatabaseColumn(Email email) {
        return email == null ? null : email.address();
    }

    @Override
    public Email convertToEntityAttribute(String s) {
        return s == null ? null : new Email(s);
    }
}
