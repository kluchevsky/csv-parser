package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {

    /**
     * Test reading employees from a CSV file with valid data.
     * It verifies if the method can successfully read employee data from the file.
     */
    @Test
    void testReadEmployeesFromFile_Positive() {
        // Arrange
        String filename = Objects.requireNonNull(getClass().getResource("/test_positive.csv")).getFile();

        // Act
        List<Employee> employees = FileService.readDataFromFile(filename);

        // Assert
        assertNotNull(employees);
        assertFalse(employees.isEmpty());
        assertEquals(3, employees.size());
        assertEquals(1, employees.get(0).getId());
        assertEquals("John", employees.get(0).getFirstName());
        assertEquals("Doe", employees.get(0).getLastName());
        assertEquals(50000, employees.get(0).getSalary());
        assertEquals(-1, employees.get(0).getManagerId());
    }

    /**
     * Test reading employees from a nonexistent file.
     * It checks if the method properly handles the case when the file is not found.
     */
    @Test
    void testReadDataFromFile_FileNotFound() {
        // Arrange
        String filename = "nonexistent_file.csv";

        // Act
        List<Employee> employees = FileService.readDataFromFile(filename);

        // Assert
        assertNotNull(employees);
        assertTrue(employees.isEmpty());
    }

    /**
     * Test reading employees from an empty file.
     * It verifies if the method correctly handles an empty file.
     */
    @Test
    void testReadEmployeesFromFile_EmptyFile() {
        // Arrange
        String filename = Objects.requireNonNull(getClass().getResource("/empty_file.csv")).getFile();

        // Act
        List<Employee> employees = FileService.readDataFromFile(filename);

        // Assert
        assertNotNull(employees);
        assertTrue(employees.isEmpty());
    }

    /**
     * Tests reading data from a file without a header.
     * Ensures that the data is successfully read from the file and contains the expected values.
     */
    @Test
    void testReadDataFromFile_NoHeader() {
        // Arrange
        String filename = Objects.requireNonNull(getClass().getResource("/no_header_file.csv")).getFile();

        // Act
        List<Employee> employees = FileService.readDataFromFile(filename);

        // Assert
        assertNotNull(employees, "The list of employees should not be null");
        assertFalse(employees.isEmpty(), "The list of employees should not be empty");

        // Additional assertions to validate the content of the list
        assertEquals(2, employees.size(), "The number of employees should be 2");
        assertEquals(1, employees.get(0).getId(), "The ID of the first employee should be 1");
        assertEquals("John", employees.get(0).getFirstName(), "The first employee's first name should be John");
        assertEquals("Doe", employees.get(0).getLastName(), "The first employee's last name should be Doe");
        assertEquals(40000, employees.get(0).getSalary(), "The first employee's salary should be 40000");
        assertEquals(-1, employees.get(0).getManagerId(), "The first employee should have no manager");

        assertEquals(2, employees.get(1).getId(), "The ID of the second employee should be 2");
        assertEquals("Alice", employees.get(1).getFirstName(), "The second employee's first name should be Alice");
        assertEquals("Smith", employees.get(1).getLastName(), "The second employee's last name should be Smith");
        assertEquals(45000, employees.get(1).getSalary(), "The second employee's salary should be 45000");
        assertEquals(1, employees.get(1).getManagerId(), "The second employee's manager ID should be 1");
    }

    /**
     * Tests reading data from a file with invalid format.
     * Ensures that the method throws a Exception when the file has an invalid format.
     */
    @Test
    void testReadDataFromFile_InvalidFormat() {
        // Arrange
        String filename = Objects.requireNonNull(getClass().getResource("/invalid_format_file.csv")).getFile();

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            FileService.readDataFromFile(filename);
        });
    }

}