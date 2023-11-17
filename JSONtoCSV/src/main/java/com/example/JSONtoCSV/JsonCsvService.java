package com.example.JSONtoCSV;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

@Service
public class JsonCsvService {

    void convertJsonToCsv(List<JsonEntity> jsonData) throws JsonProcessingException {

        try {
            if (!jsonData.isEmpty()) {
                // Build CSV schema dynamically
                CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
                JsonEntity firstObject = jsonData.get(0);

                // Use reflection to get field names from JsonEntity class
                Class<?> jsonEntityClass = JsonEntity.class;

                Arrays.stream(jsonEntityClass.getDeclaredFields())
                        .forEach(field -> csvSchemaBuilder.addColumn(field.getName()));
                CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

                // Convert JSON to CSV using the schema
                CsvMapper csvMapper = new CsvMapper();
                String csvData = csvMapper.writerFor(List.class)  // Use List.class as it's a list of JsonEntity
                        .with(csvSchema)
                        .writeValueAsString(jsonData);

                // Now you can handle the CSV data as needed (e.g., save it to a file, send as a response, etc.)
                saveCsvToFile(csvData, "output.csv");
                System.out.println(csvData);
            } else {
                System.out.println("Invalid JSON data. Expected a non-empty list.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    private void saveCsvToFile(String csvData, String fileName) throws IOException {
        // Save CSV data to a file
        Path filePath = Path.of(fileName);
        Files.write(filePath, csvData.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

}