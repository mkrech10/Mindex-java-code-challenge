package com.mindex.challenge.data;

/**
 * This object represents the Reporting structure for each employee. None of the objects created by this
 * class are persisted. 
 * It will read employee data from the database and then calculate the nunber of reporting employees. 
 * This number counts ALL employees under another employee all the way down the tree.
 */
public class ReportingStructure {
    private String employeeId;
    private int numberOfReports;

    public ReportingStructure() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}
