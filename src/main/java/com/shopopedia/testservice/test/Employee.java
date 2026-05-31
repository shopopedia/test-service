package com.shopopedia.testservice.test;

import lombok.Getter;

import java.util.List;
import java.util.OptionalDouble;

@Getter
public class Employee {
    private final String id;
    private final String name;
    private final String department;
    private final double salary;

    public Employee(String id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " (" + salary + ")";
    }

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("1", "Alice", "Engineering", 120000),
                new Employee("2", "Bob", "Engineering", 95000),
                new Employee("3", "Carol", "Engineering", 110000),
                new Employee("4", "Dave", "Sales", 80000),
                new Employee("5", "Eve", "Sales", 75000),
                new Employee("6", "Frank", "HR", 70000)
        );

        double totalSalary = 0;

        for (Employee emp : employees) {
            totalSalary = totalSalary + emp.getSalary();
        }

        OptionalDouble averageSalary = OptionalDouble.of(totalSalary / employees.size());

        averageSalary = employees.stream()
                        .mapToDouble(Employee::getSalary).average();

        System.out.println("totalSalary: " + totalSalary);
        System.out.println("averageSalary: " + averageSalary);
    }
}
