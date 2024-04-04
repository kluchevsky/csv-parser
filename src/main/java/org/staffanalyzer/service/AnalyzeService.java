package org.staffanalyzer.service;

import org.staffanalyzer.dto.Employee;

import java.util.List;

public interface AnalyzeService {
    void analyzeEmployees(List<Employee> employees);
}
