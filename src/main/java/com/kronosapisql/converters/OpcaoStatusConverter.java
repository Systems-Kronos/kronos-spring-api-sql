package com.kronosapisql.converters;

import com.kronosapisql.enums.OpcaoStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OpcaoStatusConverter implements AttributeConverter<OpcaoStatus, String> {
    public String convertToDatabaseColumn(OpcaoStatus attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValorBanco();
    }

    public OpcaoStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return OpcaoStatus.fromValorBanco(dbData);
    }
}
