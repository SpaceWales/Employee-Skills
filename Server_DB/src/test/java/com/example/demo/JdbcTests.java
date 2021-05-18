package com.example.demo;


import com.example.demo.controller.SQL;
import com.example.demo.dao.EmployeeDAO;
import com.example.demo.dao.SkillDAO;
import com.example.demo.jdbc.EmployeeJdbc;
import com.example.demo.jdbc.SkillJdbc;
import com.example.demo.model.Employee;
import com.example.demo.model.Field;
import com.example.demo.model.Skill;
import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.sql.Date;
import java.util.List;

public class JdbcTests {

    private static JdbcTemplate jdbcTemplate;
    private static SingleConnectionDataSource dataSource;
    private static SkillDAO skillDAO;
    private static EmployeeDAO employeeDAO;
    private static SQL sql = new SQL();

    @BeforeClass
    public static void beforeClass() throws Exception {
        dataSource = new SingleConnectionDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/skill-test");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        dataSource.setAutoCommit(false);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        dataSource.destroy();
    }

    @Before
    public void setUp() throws Exception {
        jdbcTemplate = new JdbcTemplate(dataSource);
        skillDAO = new SkillJdbc(jdbcTemplate);
        employeeDAO = new EmployeeJdbc(jdbcTemplate);
    }

    @After
    public void tearDown() throws Exception {
        dataSource.getConnection().rollback();
    }

    public Employee generate_employee_class()
    {
        //set non-nullable fields
        Employee employee = new Employee();
        employee.setFirstname("FirstName");
        employee.setLastname("LastName");
        employee.setBirthdate(new Date(1990-1-1));
        return employee;
    }

    public Skill generate_skill_class()
    {
        Field field = new Field();
        Skill skill = new Skill();

        field.setField_type("field type");
        field.setField_name("field name");

        skill.setExperience(12);
        skill.setSummary("summary");
        skill.setField(field);

        return skill;
    }

    //posts

    @Test
    public void employee_creation_test()
    {
        Employee employee = generate_employee_class();

        employeeDAO.createEmployee(employee);

        int employeeID = employee.getId();

        Employee employeeActual = employeeDAO.findEmployeeByID(employeeID);

        Assert.assertEquals(employeeID,employeeActual.getId());
    }

    @Test
    public void add_skill_to_employee_test()
    {
        Employee employee = generate_employee_class();
        employeeDAO.createEmployee(employee);

        Skill skill = generate_skill_class();

        skillDAO.addSkilltoEmployeeID(skill,employee.getId());

        List<Skill> actual = skillDAO.returnAllSkillsByEmployeeID(employee.getId());

        Assert.assertEquals(skill.getSkill_id(),actual.get(0).getSkill_id());
    }

    //gets

    @Test
    public void get_all_employees_test()
    {
        Employee employee = generate_employee_class();
        employeeDAO.createEmployee(employee);

        List<Employee> employeeList = employeeDAO.returnAllEmployees();

        //loop employee list to check for added employee
        boolean contained = false;
        for(Employee e : employeeList)
        {
            if(e.getId() == employee.getId()) contained = true;
        }
        Assert.assertTrue(contained);
    }

    //add more if there is time, gets are verified through other tests


    //puts

    @Test
    public void update_employee_test()
    {
        Employee employee = generate_employee_class();
        //created before update
        employeeDAO.createEmployee(employee);

        String updatedName = "Updated";
        employee.setFirstname(updatedName);

        //updated employee
        employeeDAO.updateEmployeeByID(employee,employee.getId());

        //verify
        Employee employeeActual = employeeDAO.findEmployeeByID(employee.getId());

        Assert.assertEquals(updatedName,employeeActual.getFirstname());
    }

    @Test
    public void update_skill_for_employee_test()
    {
        Employee employee = generate_employee_class();
        Skill skill = generate_skill_class();

        //add to db, then update and verify

        employeeDAO.createEmployee(employee);
        skillDAO.addSkilltoEmployeeID(skill,employee.getId());

        //change skill
        int updatedSkillExperience = 99;
        skill.setExperience(updatedSkillExperience);

        skillDAO.updateSkillByEmployeeAndSkillID(skill,skill.getSkill_id(),employee.getId());

        //verify update

        Skill skillActual = skillDAO.findSkillByBothID(employee.getId(),skill.getSkill_id());

        Assert.assertEquals(skillActual.getExperience(),updatedSkillExperience);
    }

    //deletes

    @Test
    public void delete_employee_test()
    {
        //add employee, verify employee is contained, then delete
        Employee employee = generate_employee_class();
        employee = employeeDAO.createEmployee(employee);

        //employee is in database
        boolean exists = false;
        List<Employee> verifiedEmployee = employeeDAO.returnAllEmployees();
        for(Employee e : verifiedEmployee)
        {
            if(e.getId() == employee.getId())exists = true;
        }
        Assert.assertTrue(exists);

        //delete employee

        employeeDAO.deleteEmployeeByID(employee.getId());

        //verify employee no longer in DB
        List<Employee> employeeActual = employeeDAO.returnAllEmployees();
        boolean existsActual = false;
        for(Employee e : employeeActual)
        {
            if(e.getId() == employee.getId()) existsActual = true;
        }
        Assert.assertFalse(existsActual);
    }

    @Test
    public void delete_skill_from_employee_test()
    {
        //add skill to employee, verify exists, then delete
        Employee employee = generate_employee_class();
        Skill skill = generate_skill_class();

        //added
        employee = employeeDAO.createEmployee(employee);
        skill = skillDAO.addSkilltoEmployeeID(skill,employee.getId());

        //verify
        Skill verifySkillExists = skillDAO.findSkillByBothID(employee.getId(),skill.getSkill_id());

        Assert.assertEquals(skill.getSkill_id(),verifySkillExists.getSkill_id());

        //delete
        skillDAO.deleteSkillFromEmployee(employee.getId(),skill.getSkill_id());

        //verify deletion

        //will return a 0 id/experience with null fields
        Skill verifyDelete = skillDAO.findSkillByBothID(employee.getId(),skill.getSkill_id());

        Assert.assertTrue(verifyDelete.getSkill_id() == 0);
    }








}
