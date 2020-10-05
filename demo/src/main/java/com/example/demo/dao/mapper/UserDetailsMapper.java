package com.example.demo.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.model.Employee;

import org.springframework.jdbc.core.RowMapper;

public class UserDetailsMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee e = new Employee();
        e.setEmpId(rs.getString("empid"));
        e.setName(rs.getString("name"));
        return e;
    }
    
}