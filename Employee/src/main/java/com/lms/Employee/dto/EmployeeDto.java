package com.lms.Employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long employeeId;
    private String employeeName;
    private String email;
    private String password;
    private String role;
    private Boolean communicationSw;

}
