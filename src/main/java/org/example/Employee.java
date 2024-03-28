package org.example;

class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int salary;
    private int managerId;

    public Employee(int id, String firstName, String lastName, int salary, int managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSalary() {
        return salary;
    }

    public int getManagerId() {
        return managerId;
    }
}
