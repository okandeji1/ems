package com.kandesoft.ems.service;

import com.kandesoft.ems.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployee(long id);
    Employee updateEmployee(Employee employee, long id);
    void deleteEmployee(long id);
}
