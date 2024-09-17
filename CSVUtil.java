package com.naive;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;

public class CSVUtil {
    public static void loadCSV(String filePath, NaiveBayesClassifier classifier) throws IOException {
        FileReader reader = new FileReader(filePath);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());

        for (CSVRecord record : csvParser) {
            String[] row = {record.get("plant_family"), record.get("soil_type"), record.get("season"),
                    record.get("activity_name"), record.get("activity_frequency")};
            String outcome = record.get("healthy"); // The last column should be "healthy" (YES/NO)
            classifier.train(row, outcome);
        }

        csvParser.close();
        reader.close();
    }
}
