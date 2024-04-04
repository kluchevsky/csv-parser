package org.staffanalyzer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.staffanalyzer.dto.Employee;
import org.staffanalyzer.service.impl.AnalyzeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnalyzeServiceTest {

    AnalyzeServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new AnalyzeServiceImpl();
    }

    /**
     * Test analyzing salary when it is less than expected.
     * Ensures that the method returns the correct message when the salary is less than expected.
     */
    @Test
    void testAnalyzeSalary_LessThanShould() {
        // Arrange
        Employee manager = new Employee(1, "John", "Doe", 110000, -1);
        List<Employee> employees = new ArrayList<>();
        employees.add(manager);
        employees.add(new Employee(2, "Alice", "Smith", 105000, 1));
        employees.add(new Employee(3, "Bob", "Jones", 95000, 1));

        service.buildEmployeesMaps(employees);

        // Act
        String result = service.analyzeSalary(1).get();

        // Assert
        assertNotNull(result);
        assertEquals("John Doe earns 10000,00 less than it should.", result);
    }

    /**
     * Test analyzing salary when it is more than expected.
     * Ensures that the method returns the correct message when the salary is more than expected.
     */
    @Test
    void testAnalyzeSalary_MoreThanShould() {
        // Arrange
        Employee manager = new Employee(1, "John", "Doe", 160000, -1);
        List<Employee> employees = new ArrayList<>();
        employees.add(manager);
        employees.add(new Employee(2, "Alice", "Smith", 105000, 1));
        employees.add(new Employee(3, "Bob", "Jones", 95000, 1));

        service.buildEmployeesMaps(employees);

        // Act
        String result = service.analyzeSalary(1).get();

        // Assert
        assertNotNull(result);
        assertEquals("John Doe earns 10000,00 more than it should.", result);
    }

    /**
     * Test positive case for analyzeSalary method. Employee's salary is within the bounds
     * Verifies that the analyzeSalary method returns null when the employee's salary is within the bounds.
     */
    @Test
    void testAnalyzeSalary_PositiveCase() {
        // Arrange
        Employee manager = new Employee(1, "John", "Doe", 130000, -1);
        List<Employee> employees = new ArrayList<>();
        employees.add(manager);
        employees.add(new Employee(2, "Alice", "Smith", 105000, 1));
        employees.add(new Employee(3, "Bob", "Jones", 95000, 1));

        service.buildEmployeesMaps(employees);

        // Act
        Optional<String> result = service.analyzeSalary(1);

        // Assert
        assertTrue(result.isEmpty());
    }

    /**
     * Test counting managers for each employee in the list.
     * Ensures that the method correctly counts the number of managers for each employee in the list.
     */
    @Test
    void testCountManagers() {
        // Arrange
        Employee ceo = new Employee(1, "John", "Doe", 100000, -1);
        Employee managerA = new Employee(2, "Alice", "Smith", 80000, 1);
        Employee managerB = new Employee(3, "Bob", "Jones", 70000, 1); // Manager B reports to CEO
        Employee managerC = new Employee(4, "Charlie", "Brown", 60000, 3); // Manager C reports to Manager B
        Employee employeeA = new Employee(5, "Emily", "Davis", 50000, 4); // Employee A reports to Manager C
        Employee employeeB = new Employee(6, "Ethan", "Wilson", 40000, 4); // Employee B also reports to Manager C

        List<Employee> employees = new ArrayList<>();
        employees.add(ceo);
        employees.add(managerA);
        employees.add(managerB);
        employees.add(managerC);
        employees.add(employeeA);
        employees.add(employeeB);

        service.buildEmployeesMaps(employees);

        // Act & Assert
        assertEquals(0, service.countManagers(ceo));
        assertEquals(1, service.countManagers(managerA));
        assertEquals(1, service.countManagers(managerB));
        assertEquals(2, service.countManagers(managerC));
        assertEquals(3, service.countManagers(employeeA));
        assertEquals(3, service.countManagers(employeeB));
    }
}