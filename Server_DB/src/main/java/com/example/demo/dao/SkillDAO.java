package com.example.demo.dao;

import com.example.demo.model.Skill;

import java.util.List;

public interface SkillDAO {

    public List<Skill> returnAllSkillsByEmployeeID(int id);
    public Skill findSkillByBothID(int employeeID,int skillID);
    public void deleteSkillFromEmployee(int employeeID,int skillID);
}
