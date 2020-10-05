package com.example.demo.model;

import java.io.Serializable;
import java.sql.Date;

public class Employee implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String name;

    private String empId;

    private Date sg_session_open_ts;
    private Date sg_session_close_ts;
     private String auth_token="";
     private String action_user="";

    public Employee(String name, String empId) {
        this.name = name;
        this.empId = empId;
    }

    public Employee() {
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the empId
     */
    public String getEmpId() {
        return empId;
    }

    @Override
    public String toString() {
        return "Employee [empName=" + name + ", empId=" + empId + ", sg_session_open_ts = "+ sg_session_open_ts + " ,sg_session_close_ts ="+ sg_session_close_ts +  "]";
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    /**
     * 
     * @param d for sg_session_open_ts
     * 
     */
    public void setSgSessionOpenDate(Date d) {
        this.sg_session_open_ts = d;
    }

    /**
     * 
     * @param d for sg_session_close_ts
     * 
     */

    public void setSgSessionCloseDate(Date d) {
        this.sg_session_close_ts = d;
    }

    /**
     * 
     * @return sg_session_open_ts
    */
    public Date getSgSessionOpenDate() {
        return this.sg_session_open_ts;
    }

    /**
     * 
     * @return this.sg_session_close_ts
    */
    public Date getSgSessionClaoseDate() {
        return this.sg_session_close_ts;
    }

}