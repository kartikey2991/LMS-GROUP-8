package com.lms.Employee.service;

import com.lms.Employee.dto.EmployeeDto;
import com.lms.Employee.entity.Employee;
import com.lms.Employee.entity.JoinerMentorConnection;

import java.util.List;

public interface IEmployeeService {
    void createEmployee(EmployeeDto employeeDto);

    EmployeeDto fetchDetails(String email);

    boolean updateDetails(EmployeeDto employeeDto);

    boolean deleteDetails(String email);

    List<Employee> fetchDetailsByRole(String role);
}
