package com.lms.Employee.service;

import com.lms.Employee.dto.EmployeeDto;

public interface IEmployeeService {
    void createEmployee(EmployeeDto employeeDto);

    EmployeeDto fetchDetails(String email);

    boolean updateDetails(EmployeeDto employeeDto);

    boolean deleteDetails(String email);
}
