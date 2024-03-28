package org.example;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String filename;
        if (args.length > 0) {
            filename = args[0];
        } else {
            filename = "data.csv"; // default value
        }
        List<Employee> employees = FileService.readDataFromFile(filename);
        AnalyzeService.analyzeEmployees(employees);
    }

}