package com.example.demo.jdbc;

import com.example.demo.controller.EmployeePKG;
import com.example.demo.controller.SQL;
import com.example.demo.controller.SkillPKG;
import com.example.demo.dao.EmployeeDAO;
import com.example.demo.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeJdbc implements EmployeeDAO
{
    private JdbcTemplate jdbcTemplate;
    private SQL sql = new SQL();

    public EmployeeJdbc(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}


    @Override
    public List<Employee> returnAllEmployees()
    {

        List<Employee> employeeList = new ArrayList<>();
        try
        {
            String statement = sql.return_all_employees();
            SqlRowSet employees = jdbcTemplate.queryForRowSet(statement);
            employeeList = EmployeePKG.allEmployees(employees);
            for(Employee employee : employeeList)
            {
                packageSkills(employee);
            }

        }
        catch (Exception e)
        {
            //failed statement
        }
        return employeeList;
    }

    @Override
    public void deleteEmployeeByID(int id)
    {
        String statement = sql.delete_employee_by_ID();
        try
        {
            jdbcTemplate.update(statement,id);
        }
        catch(Exception e)
        {
            //failed statement
        }
        return;
    }

    @Override
    public Employee findEmployeeByID(int id)
    {
        Employee employee = new Employee();

        try
        {
            String statement = sql.find_employee_by_ID();
            SqlRowSet employeeQry = jdbcTemplate.queryForRowSet(statement,id);
            employee = EmployeePKG.employeeByID(employeeQry);
            packageSkills(employee);
        }
        catch(Exception e)
        {
            //failed statement
        }
        return employee;
    }

    @Override
    public Employee createEmployee(Employee employee)
    {
        System.out.println(employee);
        //insert address, get address id back
        //insert employee, using address_id

        return employee;
    }



    public Employee packageSkills(Employee employee)
    {
        String skillQuery = sql.return_all_skills();
        SqlRowSet skillSet = jdbcTemplate.queryForRowSet(skillQuery,employee.getId());
        employee.setSkills(SkillPKG.mapSkills(skillSet));
        return employee;
    }
}
