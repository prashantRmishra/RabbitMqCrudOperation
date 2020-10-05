package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.example.demo.config.Configuration;
import com.example.demo.dao.mapper.UserDetailsMapper;
import com.example.demo.model.Employee;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

@Bean
public class UserDAOImpl implements UserDAO {

    @Inject
    Configuration config;
    List<Employee> empList = new ArrayList<>();

    public JdbcTemplate getJdbcTempalte() {
        return new JdbcTemplate(config.getDriverManagerDataSource());
    }

    public TransactionTemplate getTransactionTempalte() {
        return new TransactionTemplate(config.getPlatformtransactionManager());
    }

    @Override
    public Employee getUserDAO(String id) {
        int result = 0;

        result += getTransactionTempalte().execute(action -> {

            try {
                empList = getJdbcTempalte().query("select * from usedetails where empid=?;", new Object[] { id },
                        new UserDetailsMapper());
            } catch (Exception e) {
                action.setRollbackOnly();
                e.printStackTrace();
                empList = null;
            }
            return empList.isEmpty() ? 0 : 1;
        });

        return result > 0 ? empList.get(0) : null;
    }

    @Override
    public String addUserDAO(Employee e) {

        int result = 0;

        result += getTransactionTempalte().execute(action -> {
            int row = 0;
            try {
                row += getJdbcTempalte().update("insert into usedetails values(?,?)",
                        new Object[] { e.getEmpId(), e.getName() });
            } catch (Exception p) {
                action.setRollbackOnly();
                p.printStackTrace();
                row = 0;
            }
            return row;
        });

        return result > 0 ? "User Added Successfully" : "Failed To Add";
    }

    @Override
    public String updateUserDAO(Employee e, String id) {

        int result = 0;

        result += getTransactionTempalte().execute(action -> {
            int row = 0;
            try {
                row += getJdbcTempalte().update("update usedetails set name =? ,empid=? where empid =?;",
                        new Object[] { e.getName(), e.getEmpId(), id });
            } catch (Exception p) {
                action.setRollbackOnly();
                p.printStackTrace();
                row = 0;
            }
            return row;
        });

        return result > 0 ? "User updated Successfully" : "Failed To Update";
    }

    @Override
    public String deleteUserDAO(String id) {
        int result = 0;

        result += getTransactionTempalte().execute(action -> {
            int row = 0;
            try {
                row += getJdbcTempalte().update("delete from usedetails where empid =?;", new Object[] { id });
            } catch (Exception p) {
                action.setRollbackOnly();
                p.printStackTrace();
                row = 0;
            }
            return row;
        });

        return result > 0 ? "User Deleted Successfully" : "Failed To Delete";
    }

}