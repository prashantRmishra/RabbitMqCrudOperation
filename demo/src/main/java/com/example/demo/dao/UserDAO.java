package com.example.demo.dao;

import com.example.demo.model.Employee;

public interface UserDAO {
    public Employee getUserDAO(String id);

    public String addUserDAO(Employee e);

    public String updateUserDAO(Employee e,String id);

    public String deleteUserDAO(String id);

}