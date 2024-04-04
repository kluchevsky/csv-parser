package org.staffanalyzer;

import org.staffanalyzer.dto.Employee;
import org.staffanalyzer.service.AnalyzeService;
import org.staffanalyzer.service.impl.AnalyzeServiceImpl;
import org.staffanalyzer.service.FileService;
import org.staffanalyzer.service.impl.FileServiceImpl;

import java.util.List;

public class StaffAnalyzer {

    public static void main(String[] args) {
        FileService fileService = new FileServiceImpl();
        AnalyzeService analyzeService = new AnalyzeServiceImpl();

        String filename;
        if (args.length > 0) {
            filename = args[0];
        } else {
            filename = "data.csv"; // default value
        }
        List<Employee> employees = fileService.readDataFromFile(filename);
        analyzeService.analyzeEmployees(employees);
    }
}