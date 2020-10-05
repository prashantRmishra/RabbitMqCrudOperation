package com.example.demo.service;

import com.example.demo.model.Employee;

public interface UserService {
    public Employee getUser(String id);

    public String addUser(Employee e);

    public String updateUser(Employee e, String id);

    public String deleteUser(String id);

}