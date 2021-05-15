package com.example.demo.jdbc;

import com.example.demo.controller.SQL;
import com.example.demo.controller.SkillPKG;
import com.example.demo.dao.SkillDAO;
import com.example.demo.model.Skill;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class SkillJdbc implements SkillDAO {
    JdbcTemplate jdbcTemplate;
    SQL sql = new SQL();
    Connection con;


    public SkillJdbc(JdbcTemplate jdbcTemplate) throws SQLException
    {
        this.jdbcTemplate = jdbcTemplate;
        this.con = jdbcTemplate.getDataSource().getConnection();
    }

    @Override
    public List<Skill> returnAllSkillsByEmployeeID(int id)
    {
        String statement = sql.return_all_skills();
        SqlRowSet skills = jdbcTemplate.queryForRowSet(statement, id);
        return SkillPKG.mapSkills(skills);
    }

    @Override
    public Skill findSkillByBothID(int employeeID, int skillID) {
        Skill skill = new Skill();
        ResultSet skillForEmployee = null;
        try
        {
            PreparedStatement ps = con.prepareStatement(sql.find_employee_and_skill_by_IDs());
            ps.setInt(1, employeeID);
            ps.setInt(2, skillID);
            skillForEmployee = ps.executeQuery();
            if (skillForEmployee.next())
            {
                skill = SkillPKG.mapSkill(skillForEmployee);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return skill;
    }

    @Override
    public void deleteSkillFromEmployee(int employeeID,int skillID)
    {
        try
        {
            String[] sqlDeleteSkill = sql.delete_skill_from_employee();
            //deletes from joined table, employeeID -> skillID
            jdbcTemplate.update(sqlDeleteSkill[0],employeeID,skillID);
            //deletes from skill table, only skillID
            jdbcTemplate.update(sqlDeleteSkill[1],skillID);
            //if time clear out field table as well
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return;
    }

    @Override
    public Skill addSkilltoEmployeeID(Skill skill,int employeeID)
    {
        try
        {
            ResultSet result;
            PreparedStatement prep;

            prep = con.prepareStatement(sql.insert_into_field_return_field_id());
            //field_name -> field_type
            prep.setString(1,skill.getField().getField_name());
            prep.setString(2,skill.getField().getField_type());
            result = prep.executeQuery();
            if(result.next())
            {
                skill.getField().setField_id(result.getInt("field_id"));
            }

            prep = con.prepareStatement(sql.insert_into_skill_with_field_id_return_skill_id());
            //field.field_id -> skill.experience -> skill.summary
            prep.setInt(1,skill.getField().getField_id());
            prep.setInt(2,skill.getExperience());
            prep.setString(3,skill.getSummary());
            result = prep.executeQuery();
            if(result.next())
            {
                skill.setSkill_id(result.getInt("skill_id"));
            }

            prep = con.prepareStatement(sql.insert_into_joined_employeeskills_with_skill_id_and_employee_id());
            //employeeID -> skill_id
            prep.setInt(1,employeeID);
            prep.setInt(2,skill.getSkill_id());
            prep.executeUpdate();

        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return skill;
    }

    public void updateSkillByEmployeeAndSkillID(Skill skill,int skillID,int employeeID)
    {
        try
        {
            PreparedStatement prep;
            prep = con.prepareStatement(sql.update_skill_with_skill_id_employee_id());
            // experience -> summary -> skillID -> employeeID
            prep.setInt(1,skill.getExperience());
            prep.setString(2,skill.getSummary());
            prep.setInt(3,skillID);
            prep.setInt(4,employeeID);
            prep.executeUpdate();

            prep = con.prepareStatement(sql.update_field_with_skill_id_employee_id());
            //field_name -> field_type -> skillID -> employeeID
            prep.setString(1,skill.getField().getField_name());
            prep.setString(2,skill.getField().getField_type());
            prep.setInt(3,skillID);
            prep.setInt(4,employeeID);
            prep.executeUpdate();

        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }


}
