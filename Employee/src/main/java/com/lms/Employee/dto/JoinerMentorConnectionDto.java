package com.lms.Employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinerMentorConnectionDto {
    private Long joinerId;
    private Long mentorId;
    private EmployeeDto employeeDto;
}
