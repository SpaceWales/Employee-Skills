package com.example.demo.dao;

import com.example.demo.model.Employee;

import java.util.List;

public interface EmployeeDAO
{
    public List<Employee> returnAllEmployees();
    public void deleteEmployeeByID(int id);
    public Employee findEmployeeByID(int id);
    public Employee createEmployee(Employee employee);

}
