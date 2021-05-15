package com.example.demo.controller;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.dao.SkillDAO;
import com.example.demo.model.Employee;
import com.example.demo.model.Skill;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestController
{
    @Autowired
    EmployeeDAO employeeDAO;
    @Autowired
    SkillDAO skillDAO;

    //Employee section

    @RequestMapping(path="/employees", method = RequestMethod.GET)
    public List<Employee> getAllEmployees()
    {
        return employeeDAO.returnAllEmployees();
    }

    @RequestMapping(path="/employees", method = RequestMethod.POST)
    public Employee createEmployee(@RequestBody Employee employee)
    {
        return employeeDAO.createEmployee(employee);
    }

    @RequestMapping(path="/employees/{employeeID}", method = RequestMethod.GET)
    public Employee findEmployeeById(@PathVariable int employeeID)
    {
        return employeeDAO.findEmployeeByID(employeeID);
    }

    @RequestMapping(path="/employees/{employeeID}", method = RequestMethod.PUT)
    public Employee updateEmployeeById(@RequestBody Employee employee, @PathVariable("employeeID") int employeeID)
    {
        return employeeDAO.updateEmployeeByID(employee,employeeID);
    }

    @RequestMapping(path="/employees/{employeeID}", method = RequestMethod.DELETE)
    public void deleteEmployeeById(@PathVariable("employeeID") int employeeID)
    {
        employeeDAO.deleteEmployeeByID(employeeID);
    }


    //Skill

    @RequestMapping(path="/employees/{employeeID}/skills", method = RequestMethod.GET)
    public List<Skill> getAllSkillsByID(@PathVariable("employeeID") int employeeID)
    {
        return skillDAO.returnAllSkillsByEmployeeID(employeeID);
    }

    //could be bringing in skill or Employee not sure yet
    @RequestMapping(path="/employees/{employeeID}/skills", method = RequestMethod.POST)
    public Skill addSkillToEmployee(@RequestBody Skill skill, @PathVariable("employeeID") int employeeID)
    {
        return null;
    }

    @RequestMapping(path="/employees/{employeeID}/skills/{skillID}", method = RequestMethod.GET)
    public Skill findSkillByBothID(@PathVariable("employeeID") int employeeID,@PathVariable("skillID") int skillID)
    {
        return skillDAO.findSkillByBothID(employeeID,skillID);
    }

    //could be bringing in skill or employee not sure yet
    @RequestMapping(path="/employees/{employeeID}/skills/{skillID}", method = RequestMethod.PUT)
    public void updateSkillByBothID(@RequestBody Skill skill, @PathVariable("employeeID") int employeeId,@PathVariable("skillID") int skillID)
    {
        return;
    }

    @RequestMapping(path="/employees/{employeeID}/skills/{skillID}", method = RequestMethod.DELETE)
    public void deleteSkillFromEmployeeID(@PathVariable("employeeID") int employeeID, @PathVariable("skillID") int skillID)
    {
        skillDAO.deleteSkillFromEmployee(employeeID,skillID);
    }

}


//endpoints
/*

EMPLOYEE
get /employees --get all employees DONE
post /employees --create a new employee DONE employee/address
get /employees/{employeeId} --find employee by id DONE
put /employees/{employeeId} --update employee by id
delete /employees/{employeeId} --delete employee by id DONE

SKILL
get /employees/{employeeId}/skills --get all skills by employee DONE
post /employees/{employeeId}/skills --add a skill to employee
get /employees/{employeeId}/skills/{skillId} find a skill, from employee by both id DONE
put /employees/{employeeID}/skills/{skillId} update skill for employee by both id
delete /employees/{employeeID}/skills/{skillId} delete a skill from employee DONE

*/