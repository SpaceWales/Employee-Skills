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
            //failed statement
        }
        return;
    }


}
