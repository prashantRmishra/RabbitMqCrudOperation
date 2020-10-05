package com.example.demo.controller;

import java.sql.Date;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.DeleteRoute;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Param;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PathParam;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.annotation.PutRoute;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Session;
import com.example.demo.model.Employee;
import com.example.demo.sender.Rabbitsender;
import com.example.demo.service.UserService;

@Path
public class Controller {
    @Inject
    Rabbitsender rabbitsender;
    @Inject
    UserService uService;
    Employee e = new Employee();
    private Session session;

    @GetRoute("/getdata/:id")
    @JSON
    public String getEmployeeDetails(@PathParam String id) {

        try {
            e=uService.getUser(id);
            
        } catch (Exception p) {
            p.printStackTrace();
            e = null;
        }
        rabbitsender.send(e.toString(), "SELECT");

        return e.equals(null) ? "something went wrong " : "data sent to rabitMQ "+e.toString();
    }

    @PostRoute("/add")
    @JSON
    public String addDetails(@Param Employee emp,Request request) {
        String result="";
        try {
            this.session=request.session();
            result+=uService.addUser(emp);
            e=emp;
            e.setSgSessionOpenDate(new Date(this.session.created()));
            e.setSgSessionCloseDate(new Date(this.session.expired()));
        } catch (Exception p) {
            p.printStackTrace();
            e = null;
        }
        
        rabbitsender.send(e.toString(), "ADD");

        return e.equals(null) ? "something went wrong " : result+" and data sent to rabitMQ";
    }

    @PutRoute("/update/:id")
    @JSON
    public String updateData(@PathParam String id, @Param Employee emp,Request request) {
        String result="";
        try {
            result+=uService.updateUser(emp, id);
            e=emp;
            this.session=request.session();
            e.setSgSessionOpenDate(new Date(this.session.created()));
            e.setSgSessionCloseDate(new Date(this.session.expired()));
        } catch (Exception p) {
            p.printStackTrace();
            e = null;
        }
        rabbitsender.send(e.toString(), "UPDATE");
        return e.equals(null) ? "something went wrong " : result+" and sent to rabitMQ";
    }

    @DeleteRoute("/delete/:id")
    @JSON
    public String deleteData(@PathParam String id) {
        String result="";
        try {
            result+=uService.deleteUser(id);
            e=null;
           
        } catch (Exception p) {
            p.printStackTrace();
            e = null;
        }
        rabbitsender.send("id= "+id, "DELETE");
        return result.equals("") ? "Failed To Delete":result;
    }

}