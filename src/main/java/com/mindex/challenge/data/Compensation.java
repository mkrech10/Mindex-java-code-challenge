package com.mindex.challenge.data;

import java.util.Date;

/**
 *  This class is used to represent the compensation object. 
 *  The primary key is the compensationId. 
 *  There is also an employee object as part of this class as well. This is what was in the requirements.
 *  The employeeId is used to tie the compensation back to the employee that earns this compensation.
 */
public class Compensation {
    private String compensationId;
    private Employee employee;
    private float salary;
    private Date effectiveDate;

    public Compensation() {
    }

    public String getCompensationId() {
        return compensationId;
    }

    public void setCompensationId(String compensationId) {
        this.compensationId = compensationId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
