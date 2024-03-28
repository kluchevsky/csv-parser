package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FileService {
    private static final int ID_INDEX = 0;
    private static final int FIRST_NAME_INDEX = 1;
    private static final int LAST_NAME_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final int MANAGER_ID_INDEX = 4;
    private static final String DELIMITERS_REGEX = "[;,]";
    public static final String ID_FIELD = "Id";

    public static List<Employee> readDataFromFile(String filename) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    if (line.startsWith(ID_FIELD)) {
                        isHeader = false;
                        continue; // Skip the header line
                    }
                }
                String[] parts = line.split(DELIMITERS_REGEX);
                int id = Integer.parseInt(parts[ID_INDEX]);
                String firstName = parts[FIRST_NAME_INDEX];
                String lastName = parts[LAST_NAME_INDEX];
                int salary = Integer.parseInt(parts[SALARY_INDEX]);
                int managerId = parts.length >= 5 && !parts[MANAGER_ID_INDEX].isEmpty() ? Integer.parseInt(parts[MANAGER_ID_INDEX]) : -1;
                employees.add(new Employee(id, firstName, lastName, salary, managerId));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
