package com.naive;

import java.util.HashMap;

public class NaiveBayesClassifier {
    private HashMap<String, Integer> yesCount = new HashMap<>();
    private HashMap<String, Integer> noCount = new HashMap<>();
    private int totalYes = 0;
    private int totalNo = 0;

    // Train the classifier with the input row and outcome (Healthy: Yes/No)
    public void train(String[] row, String outcome) {
        if (outcome.equalsIgnoreCase("YES")) {
            totalYes++;
            incrementCount(yesCount, row);
        } else {
            totalNo++;
            incrementCount(noCount, row);
        }
    }

    // Increment counts for "YES" or "NO" outcomes
    private void incrementCount(HashMap<String, Integer> countMap, String[] row) {
        for (String key : row) {
            countMap.put(key, countMap.getOrDefault(key, 0) + 1);
        }
    }

    // Calculate the probability of "YES" or "NO" given the input features
    public double calculateProbability(String[] input, String outcome) {
        double probability = 1.0;
        HashMap<String, Integer> countMap = outcome.equalsIgnoreCase("YES") ? yesCount : noCount;
        int totalCount = outcome.equalsIgnoreCase("YES") ? totalYes : totalNo;
        int vocabularySize = 5; // Number of unique features in the input (Plant Family, Soil Type, etc.)
        int laplaceConstant = 1; // Smoothing constant

        for (String key : input) {
            probability *= ((double) countMap.getOrDefault(key, 0) + laplaceConstant) /
                    (totalCount + laplaceConstant * vocabularySize);
        }
        probability *= (double) totalCount / (totalYes + totalNo);
        return probability;
    }

    // Predict if the plant is healthy (YES) or not (NO) based on input features
    public String predict(String[] input) {
        double yesProbability = calculateProbability(input, "YES");
        double noProbability = calculateProbability(input, "NO");

        System.out.println(String.format("P(Healthy = YES | input) = %.6f", yesProbability));
        System.out.println(String.format("P(Healthy = NO | input) = %.6f", noProbability));

        return yesProbability > noProbability ? "YES" : "NO";
    }
}
