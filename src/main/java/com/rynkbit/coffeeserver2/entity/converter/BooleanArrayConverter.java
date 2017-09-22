package com.rynkbit.coffeeserver2.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanArrayConverter implements AttributeConverter<Boolean[], String>{
    public String convertToDatabaseColumn(Boolean[] attribute) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < attribute.length; i++){
            builder.append(String.valueOf(attribute[i]));

            if(i != attribute.length - 1)
                builder.append(";");
        }

        return builder.toString();
    }

    public Boolean[] convertToEntityAttribute(String dbData) {
        String[] rawData = dbData.split(";");
        Boolean[] result = new Boolean[rawData.length];

        for (int i = 0; i < rawData.length; i++){
            result[i] = Boolean.parseBoolean(rawData[i]);
        }

        return result;
    }
}
