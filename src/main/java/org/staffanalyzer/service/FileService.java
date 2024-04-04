package org.staffanalyzer.service;

import org.staffanalyzer.dto.Employee;

import java.util.List;

public interface FileService {
    List<Employee> readDataFromFile(String filename);
}
