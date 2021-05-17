package com.example.demo.jdbc;

import com.example.demo.controller.EmployeePKG;
import com.example.demo.controller.SQL;
import com.example.demo.controller.SkillPKG;
import com.example.demo.dao.EmployeeDAO;
import com.example.demo.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeJdbc implements EmployeeDAO
{
    private JdbcTemplate jdbcTemplate;
    private SQL sql = new SQL();
    Connection con;

    public EmployeeJdbc(JdbcTemplate jdbcTemplate) throws SQLException {
        this.jdbcTemplate = jdbcTemplate;
        this.con = jdbcTemplate.getDataSource().getConnection();
    }


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
            System.out.println(e);
        }
        return employeeList;
    }

    @Override
    public void deleteEmployeeByID(int id)
    {
        String[] statement = sql.delete_employee_by_ID();
        try
        {
            jdbcTemplate.update(statement[0],id);
            jdbcTemplate.update(statement[1],id);
            jdbcTemplate.update(statement[2],id);
        }
        catch(Exception e)
        {
            System.out.println(e);
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
            packageCompanies(employee);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return employee;
    }

    @Override
    public Employee createEmployee(Employee employee)
    {
        //insert address,
        //get address id back
        //insert employee, using address_id,
        //get employeeID

        try
        {

            ResultSet resultSet;
            PreparedStatement prep;
            prep = con.prepareStatement(sql.insert_address());
            prep.setString(1,employee.getAddress().getStreet());
            prep.setString(2,employee.getAddress().getSuite());
            prep.setString(3,employee.getAddress().getCity());
            prep.setString(4,employee.getAddress().getRegion());
            prep.setString(5,employee.getAddress().getPostal());
            prep.setString(6,employee.getAddress().getCountry());
            resultSet = prep.executeQuery();
            if(resultSet.next())
            {
                employee.getAddress().setAddress_id(resultSet.getInt("address_id"));
            }

            prep = con.prepareStatement(sql.insert_employee());
            prep.setString(1,employee.getFirstname());
            prep.setString(2,employee.getLastname());
            prep.setString(3,employee.getContactemail());
            prep.setString(4,employee.getCompanyemail());
            prep.setInt(5,employee.getAddress().getAddress_id());
            prep.setDate(6,employee.getBirthdate());
            prep.setDate(7,employee.getHireddate());
            resultSet = prep.executeQuery();
            if(resultSet.next())
            {
                employee.setId(resultSet.getInt("id"));
            }

        }
        catch(SQLException e)
        {
            System.out.println(e);
        }


        return employee;
    }

    @Override
    public Employee updateEmployeeByID(Employee employee, int employeeID)
    {
        try
        {
            employee.setId(employeeID);
            PreparedStatement prep;
            ResultSet result;
            prep = con.prepareStatement(sql.update_address_with_employee_id());
            prep.setString(1,employee.getAddress().getStreet());
            prep.setString(2,employee.getAddress().getSuite());
            prep.setString(3,employee.getAddress().getCity());
            prep.setString(4,employee.getAddress().getRegion());
            prep.setString(5,employee.getAddress().getPostal());
            prep.setString(6,employee.getAddress().getCountry());
            prep.setInt(7,employee.getId());
            result = prep.executeQuery();
            if(result.next())
            {
                employee.getAddress().setAddress_id(result.getInt("address_id"));
            }

            prep = con.prepareStatement(sql.update_employee());
            prep.setString(1,employee.getFirstname());
            prep.setString(2,employee.getLastname());
            prep.setString(3,employee.getContactemail());
            prep.setString(4,employee.getCompanyemail());
            prep.setInt(5,employee.getAddress().getAddress_id());
            prep.setDate(6,employee.getBirthdate());
            prep.setDate(7,employee.getHireddate());
            prep.setInt(8,employee.getId());
            prep.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return employee;
    }


    @Override
    public Employee packageSkills(Employee employee)
    {
        String skillQuery = sql.return_all_skills();
        SqlRowSet skillSet = jdbcTemplate.queryForRowSet(skillQuery,employee.getId());
        employee.setSkills(SkillPKG.mapSkills(skillSet));
        return employee;
    }

    @Override
    public Employee packageCompanies(Employee employee)
    {
        String companyQuery = sql.get_companies_by_id();
        SqlRowSet companies = jdbcTemplate.queryForRowSet(companyQuery,employee.getId());
        employee.setCompanies(EmployeePKG.mapCompanies(companies));
        return employee;
    }
}
