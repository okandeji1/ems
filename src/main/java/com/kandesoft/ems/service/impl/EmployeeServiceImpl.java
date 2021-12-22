package com.kandesoft.ems.service.impl;

import com.kandesoft.ems.exception.ResourceNotFoundException;
import com.kandesoft.ems.model.Employee;
import com.kandesoft.ems.repository.EmployeeRepository;
import com.kandesoft.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(long id) {
        /*Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()){
            return employee.get();
        }
        throw new ResourceNotFoundException("Employee", "Id", id);*/

        // Using lambda method
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        // Check if the employee exist
        Employee findEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
        findEmployee.setFirstName(employee.getFirstName());
        findEmployee.setLastName(employee.getLastName());
        findEmployee.setEmail(employee.getEmail());
        // save data
        employeeRepository.save(findEmployee);
        return findEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
        // Delete
        employeeRepository.deleteById(id);
    }
}
