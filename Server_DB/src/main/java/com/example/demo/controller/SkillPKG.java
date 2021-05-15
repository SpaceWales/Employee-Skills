package com.example.demo.controller;

import com.example.demo.model.Field;
import com.example.demo.model.Skill;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillPKG {

    public static List<Skill> mapSkills(SqlRowSet skills)
    {
        List<Skill> skillList = new ArrayList<>();
        try
        {
            while(skills.next())
            {
                Skill skill = mapSkill(skills);
                skillList.add(skill);
            }
        }
        catch(Exception e)
        {
            //null results
        }
        return skillList;
    }


    public static Skill mapSkill(SqlRowSet row)
    {
        Skill skill = new Skill();
        Field field = new Field();
        field.setField_id(row.getInt("field_id"));
        field.setField_name(row.getString("field_name"));
        field.setField_type(row.getString("field_type"));
        skill.setSkill_id(row.getInt("skill_id"));
        skill.setExperience(row.getInt("experience"));
        skill.setSummary(row.getString("summary"));
        skill.setField(field);
        return skill;
    }

    public static Skill mapSkill(ResultSet row)
    {
        Skill skill = new Skill();
        Field field = new Field();
        try {
            field.setField_id(row.getInt("field_id"));
            field.setField_name(row.getString("field_name"));
            field.setField_type(row.getString("field_type"));
            skill.setSkill_id(row.getInt("skill_id"));
            skill.setExperience(row.getInt("experience"));
            skill.setSummary(row.getString("summary"));
            skill.setField(field);
        }
        catch(SQLException e)
        {
            //passed in via prepared statement because of multiple parameters
            //overloaded method instead of backtracking changes
        }
        return skill;
    }

}
