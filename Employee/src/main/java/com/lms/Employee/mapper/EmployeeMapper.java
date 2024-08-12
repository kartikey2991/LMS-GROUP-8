package com.lms.Employee.mapper;

import com.lms.Employee.dto.EmployeeDto;
import com.lms.Employee.entity.Employee;

public class EmployeeMapper {
    public static Employee mapToEmployee(EmployeeDto employeeDto, Employee employee){
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPassword(employeeDto.getPassword());
        employee.setRole(employeeDto.getRole());
        return employee;
    }

    public static EmployeeDto mapToEmployeeDto(Employee employee, EmployeeDto employeeDto){
        employeeDto.setEmployeeName(employee.getEmployeeName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPassword(employee.getPassword());
        employeeDto.setRole(employee.getRole());
        return employeeDto;
    }
//    employee_id INT AUTO_INCREMENT PRIMARY KEY,
//    employee_name VARCHAR(40) NOT NULL,
//    email VARCHAR(40) NOT NULL,
//    password VARCHAR(15) NOT NULL,
//    role VARCHAR(10)
}
