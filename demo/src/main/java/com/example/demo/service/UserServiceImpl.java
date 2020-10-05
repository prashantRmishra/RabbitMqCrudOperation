package com.example.demo.service;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.example.demo.dao.UserDAO;
import com.example.demo.model.Employee;


@Bean
public class UserServiceImpl implements UserService {

    @Inject
    UserDAO userDAO;

    @Override
    public Employee getUser(String id) {

        return userDAO.getUserDAO(id);
    }

    @Override
    public String addUser(Employee e) {

        return userDAO.addUserDAO(e);
    }

    @Override
    public String updateUser(Employee e, String id) {

        return userDAO.updateUserDAO(e, id);
    }

    @Override
    public String deleteUser(String id) {

        return userDAO.deleteUserDAO(id);
    }

}