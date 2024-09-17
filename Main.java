package com.naive;

import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {
        try {
            NaiveBayesClassifier Healthy = new NaiveBayesClassifier();
            CSVUtil.loadCSV("d:\\Users\\ishwa\\Downloads\\Healthy.csv",Healthy); // Update with your CSV file path
            Scanner scanner = new Scanner(in);

            out.print("Enter Plant Family: ");
            String plantFamily = scanner.nextLine();
            out.print("Enter Soil Type: ");
            String soilType = scanner.nextLine();
            out.print("Enter Season: ");
            String season = scanner.nextLine();
            out.print("Enter Activity Name (e.g., Watering): ");
            String activityName = scanner.nextLine();
            out.print("Enter Activity Frequency: ");
            String activityFrequency = scanner.nextLine();

            String[] input = {plantFamily, soilType, season, activityName, activityFrequency};
            String prediction = Healthy.predict(input);

            out.println("Input: " + Arrays.toString(input));
            out.println("Prediction (Healthy): " + prediction);

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
