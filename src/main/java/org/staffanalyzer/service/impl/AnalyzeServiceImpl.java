package org.staffanalyzer.service.impl;

import org.staffanalyzer.dto.Employee;
import org.staffanalyzer.service.AnalyzeService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

public class AnalyzeServiceImpl implements AnalyzeService {
    private static final double LOWER_BOUND_MULTIPLIER = 1.2;
    private static final double UPPER_BOUND_MULTIPLIER = 1.5;
    private static final int MAX_REPORTING_LINE = 4;
    private static final String REPORTING_LINE_TEMPLATE = "%s %s has a reporting line that is %d too long.";
    private static final String SALARY_LESS_TEMPLATE = "%s %s earns %.2f less than it should.";
    private static final String SALARY_MORE_TEMPLATE = "%s %s earns %.2f more than it should.";

    public static Map<Integer, List<Employee>> subordinatesMap;
    public static Map<Integer, Employee> employeesMap;

    @Override
    public void analyzeEmployees(List<Employee> employees) {
        buildEmployeesMaps(employees);

        analyzeSalaries();
        analyzeReportingLines(employees);
    }

    public void buildEmployeesMaps(List<Employee> employees) {
        employeesMap = employees.stream()
                .collect(Collectors.toMap(Employee::id, identity(), (a, b) -> a));
        subordinatesMap = employees.stream()
                .filter(employee -> employee.managerId() != -1)
                .collect(Collectors.groupingBy(Employee::managerId));
    }

    public String analyzeSalary(Integer id) {
        Employee manager = employeesMap.get(id);
        List<Employee> employees = subordinatesMap.get(id);
        double managerSalary = manager.salary();
        double averageSubordinatesSalary = calculateAverageSubordinatesSalary(employees);
        double lowerBound = averageSubordinatesSalary * LOWER_BOUND_MULTIPLIER;
        double upperBound = averageSubordinatesSalary * UPPER_BOUND_MULTIPLIER;

        if (managerSalary < lowerBound) {
            return String.format(SALARY_LESS_TEMPLATE,
                    manager.firstName(), manager.lastName(), lowerBound - managerSalary);
        } else if (managerSalary > upperBound) {
            return String.format(SALARY_MORE_TEMPLATE,
                    manager.firstName(), manager.lastName(), managerSalary - upperBound);
        }

        return null;
    }

    public void analyzeReportingLines(List<Employee> employees) {
        employees.forEach(emp -> {
            int managerCount = countManagers(emp);
            if (managerCount > MAX_REPORTING_LINE) {
                System.out.println(String.format(REPORTING_LINE_TEMPLATE,
                        emp.firstName(), emp.lastName(), managerCount - MAX_REPORTING_LINE));
            }
        });
    }

    public int countManagers(Employee employee) {
        int managerId = employee.managerId();
        int count = 0;

        while (managerId != -1) {
            managerId = findManager(managerId);
            count++;
        }

        return count;
    }

    private void analyzeSalaries() {
        AnalyzeServiceImpl.subordinatesMap.forEach((k, v) -> {
            String result = analyzeSalary(k);
            if (result != null) {
                System.out.println(result);
            }
        });
    }

    private int findManager(int id) {
        return Optional.ofNullable(AnalyzeServiceImpl.employeesMap.get(id)).map(Employee::managerId).orElse(-1);
    }

    private double calculateAverageSubordinatesSalary(List<Employee> employees) {
        if (employees.isEmpty()) {
            return 0;
        }
        return employees.stream()
                .mapToDouble(Employee::salary)
                .average()
                .orElse(0);
    }
}

