package com.lms.Employee.service;

import com.lms.Employee.dto.EmployeeDto;
import com.lms.Employee.dto.JoinerMentorConnectionDto;
import com.lms.Employee.entity.Employee;

import java.util.List;

public interface IJoinerMentorConnectionService {
    String getRole(Long employeeId);
    void createJoinerMentorConnection(JoinerMentorConnectionDto joinerMentorConnectionDto);
    List<Long> getJoinerIdsByMentorId(Long mentorId);
//    List<JoinerMentorConnectionDto> fetchDetails(String mentorId);


}
