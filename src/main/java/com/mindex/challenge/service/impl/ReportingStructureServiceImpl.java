package com.mindex.challenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the implementation of ReportingStructureService
 * This class contains 2 functions. One is the rest service call for getReportingStructure.
 * The other is the recursive counting function for calculating the number of reporting employees.
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ReportingStructure getReportingStructure(String id) {
        LOG.debug("Retrieving reporting structure for employee with id [{}]", id);

        //assumes there are no reporting employees 
        int numberOfReports = 0;
        Employee topEmployee = employeeService.read(id);

        //checking for valid/existing employeeId so there is something to calculate.
        if (topEmployee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        //checks on the directReports properties to begin calculating - If there are no immediate direct reports
        //there is nothing to calculate.
        if (topEmployee.getDirectReports() != null) {
            ArrayList<Employee> directReports = new ArrayList<Employee>(topEmployee.getDirectReports());
            numberOfReports = getDirectReportCount(directReports);
        }

        // build the ReportingStructure object for returning to caller
        ReportingStructure tempRS = new ReportingStructure();
        tempRS.setEmployeeId(id);
        tempRS.setNumberOfReports(numberOfReports);

        return tempRS;
    }

    private int getDirectReportCount(ArrayList<Employee> directReports) {

        //initially the number of reports is the size of the direct report list
        int directReportCount = directReports.size();
        int listSize = directReports.size();

        //loop through all the immediate direct reports in the List
        for (int i = 0; i < listSize; i++) {
            //get the employee object from the list at index i
            Employee report = directReports.get(i);
            //pulling the employeeId out so that id can be used for looking up that employee's direct reports.
            report = employeeService.read(report.getEmployeeId());

            //if there is a direct report then that direct report needs to be checked to see if it has any direct reports as well.
            if (report.getDirectReports() != null) {
                // pulls the list of direct reporst
                ArrayList<Employee> moreReports = new ArrayList<Employee>(report.getDirectReports());
                //recursively calls this method to start counting the direct reports on the current direct report.
                directReportCount = directReportCount + getDirectReportCount(moreReports);
            }
        }

        return directReportCount;
    }
}
