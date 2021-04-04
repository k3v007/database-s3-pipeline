package com.k3v007.databaseS3Pipeline.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * The type Csv util.
 *
 * @author Vivek
 */
public class CsvUtil {

    /**
     * Build csv schema csv schema.
     *
     * @param klass the klass
     * @return the csv schema
     */
    public static CsvSchema buildCsvSchema(Class klass) {
        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        for (Field declaredField : klass.getDeclaredFields()) {
            JsonProperty header = declaredField.getDeclaredAnnotation(JsonProperty.class);
            if (Objects.nonNull(header)) {
                csvSchemaBuilder.addColumn(header.value());
            }
        }
        return csvSchemaBuilder.build().withHeader();
    }
}
