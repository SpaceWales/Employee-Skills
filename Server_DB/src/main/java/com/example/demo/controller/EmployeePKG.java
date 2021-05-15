package com.example.demo.controller;

import com.example.demo.model.Address;
import com.example.demo.model.Employee;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class EmployeePKG {

    public static List<Employee> allEmployees(SqlRowSet employees)
    {
        List<Employee> employeeList = new ArrayList<>();
        try
        {
            while(employees.next())
            {
                Employee employee = mapEmployee(employees);
                employeeList.add(employee);
            }
        }
        catch(Exception e)
        {
            //no results found
        }
        return employeeList;
    }

    public static Employee employeeByID(SqlRowSet employees)
    {
        Employee employee = new Employee();
        try
        {
            while(employees.next())
            {
                employee = mapEmployee(employees);
            }
        }
        catch(Exception e)
        {

        }
        return employee;
    }

    //call from db
    public static Employee mapEmployee(SqlRowSet row)
    {
        Employee employee = new Employee();
        Address address = new Address();
        //base employee
        employee.setId(row.getInt("id"));
        employee.setFirstname(row.getString("firstname"));
        employee.setLastname(row.getString("lastname"));
        employee.setContactemail(row.getString("contactemail"));
        employee.setCompanyemail(row.getString("companyemail"));
        employee.setBirthdate(row.getDate("birthdate"));
        employee.setHireddate(row.getDate("hireddate"));
        //address
        address.setAddress_id(row.getInt("address_id"));
        address.setStreet(row.getString("street"));
        address.setSuite(row.getString("suite"));
        address.setCity(row.getString("city"));
        address.setRegion(row.getString("region"));
        address.setPostal(row.getString("postal"));
        address.setCountry(row.getString("country"));

        employee.setAddress(address);
        return employee;
    }
}
