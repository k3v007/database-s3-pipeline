package com.k3v007.databaseS3Pipeline.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Csv util.
 *
 * @author Vivek
 */
public class CsvUtil {

    /**
     * Gets csv headers.
     *
     * @param klass the klass
     * @return the csv headers
     */
    public static List<String> getCsvHeaders(Class klass) {
        List<String> csvHeaders = new ArrayList<>();
        for (Field declaredField : klass.getDeclaredFields()) {
            JsonProperty header = declaredField.getDeclaredAnnotation(JsonProperty.class);
            if (Objects.nonNull(header)) {
                csvHeaders.add(header.value());
            }
        }
        return csvHeaders;
    }

    /**
     * Build csv schema csv schema.
     *
     * @param klass the klass
     * @return the csv schema
     */
    public static CsvSchema buildCsvSchema(Class klass) {
        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        List<String> csvHeaders = getCsvHeaders(klass);
        csvHeaders.forEach(csvSchemaBuilder::addColumn);
        return csvSchemaBuilder
                .setUseHeader(true)
                .build()
                .withHeader();
    }
}
