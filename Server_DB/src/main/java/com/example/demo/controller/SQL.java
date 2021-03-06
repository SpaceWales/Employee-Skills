package com.example.demo.controller;

public class SQL {

    //hard code table variables
    //prepare call methods return string

    public String return_all_employees()
    {
        String sql = "select * from employee\n" +
                "left join address on address.address_id = employee.address_id";
        return sql;
    }

    public String return_all_skills()
    {
        String sql = "select\n" +
                "skill.skill_id,field.field_id," +
                "field.field_name,field.field_type,\n" +
                "skill.experience,skill.summary\n" +
                "from field\n" +
                "join skill on skill.field_id = field.field_id\n" +
                "join employeeskills on employeeskills.skill_id = skill.skill_id\n" +
                "join employee on employee.id = employeeskills.employee_id\n" +
                "where employee.id = ?";
        return sql;
    }

    public String find_employee_by_ID()
    {
        String sql = "select * from employee,address " +
                "where employee.id = ?" +
                "limit 1";
        return sql;
    }

    public String find_employee_and_skill_by_IDs()
    {
        String sql = "select\n" +
                "skill.skill_id,field.field_id," +
                "field.field_name,field.field_type,\n" +
                "skill.experience,skill.summary\n" +
                "from field\n" +
                "join skill on skill.field_id = field.field_id\n" +
                "join employeeskills on employeeskills.skill_id = skill.skill_id\n" +
                "join employee on employee.id = employeeskills.employee_id\n" +
                "where employee.id = ? and skill.skill_id = ? limit 1";
        return sql;
    }

    public String[] delete_skill_from_employee()
    {
        String[] sqlStatements = new String[2];
        sqlStatements[0] = "delete from employeeskills where employee_id = ? and skill_id = ?";
        sqlStatements[1] = "delete from skill where skill_id = ?";
        return sqlStatements;
    }

    public String get_companies_by_id()
    {
        String sql = "select businessunit.company_id, businessunit.company\n" +
                "from businessunit\n" +
                "join employeebusiness on employeebusiness.company_id = businessunit.company_id\n" +
                "join employee on employee.id = employeebusiness.employee_id\n" +
                "where employee.id = ?";
        return sql;
    }

    public String insert_address()
    {
        String sql = "insert into address (street,suite,city,region,postal,country) " +
                "values(?,?,?,?,?,?) RETURNING address_id";
        return sql;

    }

    public String insert_employee()
    {
        String sql = "insert into employee (firstname,lastname,contactemail,companyemail,address_id,birthdate,hireddate) " +
                "values(?,?,?,?,?,?,?) RETURNING id";
        return sql;
    }

    public String update_employee()
    {
        String sql = "update employee\n" +
                "set \n" +
                "firstname = ?,\n" +
                "lastname = ?,\n" +
                "contactemail = ?,\n" +
                "companyemail = ?,\n" +
                "address_id = ?,\n" +
                "birthdate = ?,\n" +
                "hireddate = ?\n" +
                "where employee.id = ?";
        return sql;
    }

    public String update_address_with_employee_id()
    {
        String sql = "update address\n" +
                "set\n" +
                "street = ?,\n" +
                "suite = ?,\n" +
                "city = ?,\n" +
                "region = ?,\n" +
                "postal = ?,\n" +
                "country = ?\n" +
                "from employee\n" +
                "where address.address_id = employee.address_id \n" +
                "and employee.id = ? RETURNING address.address_id";
        return sql;
    }

    public String[] delete_employee_by_ID()
    {
        String[] sql = new String[3];
        sql[0] = "delete from employeebusiness where employee_id = ?";
        sql[1] = "delete from employeeskills where employee_id = ?";
        sql[2] = "delete from employee where id = ?";

        return sql;
    }

    public String insert_into_field_return_field_id()
    {
        String sql = "insert into field\n" +
                "(field_name,field_type)\n" +
                "values(?,?) RETURNING field_id";
        return sql;
    }

    public String insert_into_skill_with_field_id_return_skill_id()
    {
        String sql = "insert into skill\n" +
                "(field_id,experience,summary)\n" +
                "values (?,?,?) RETURNING skill_id";
        return sql;
    }

    public String insert_into_joined_employeeskills_with_skill_id_and_employee_id()
    {
        String sql = "insert into employeeskills\n" +
                "(employee_id,skill_id)\n" +
                "values(?,?)";
        return sql;
    }

    public String update_skill_with_skill_id_employee_id()
    {
        String sql = "update skill \n" +
                "set \n" +
                "experience = ?,\n" +
                "summary = ?\n" +
                "from employeeskills\n" +
                "where skill.skill_id = ? and employeeskills.employee_id = ?";
        return sql;
    }

    public String update_field_with_skill_id_employee_id()
    {
        String sql = "update field \n" +
                "set field_name = ?, \n" +
                "field_type = ? \n" +
                "from skill \n" +
                "join employeeskills on employeeskills.skill_id = skill.skill_id \n" +
                "where field.field_id = skill.field_id and skill.skill_id = ? " +
                "and employeeskills.employee_id = ?";
        return sql;
    }




}
