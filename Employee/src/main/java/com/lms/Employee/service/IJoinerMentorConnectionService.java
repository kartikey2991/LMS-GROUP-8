package com.lms.Employee.service;

import com.lms.Employee.dto.EmployeeDto;
import com.lms.Employee.dto.JoinerMentorConnectionDto;
import com.lms.Employee.entity.Employee;
import com.lms.Employee.entity.JoinerMentorConnection;

import java.util.List;

public interface IJoinerMentorConnectionService {
    String getRole(Long employeeId);
    void createJoinerMentorConnection(JoinerMentorConnectionDto joinerMentorConnectionDto);
    List<JoinerMentorConnection> getJoinerIdsByMentorId(Long mentorId);
    boolean updateJoinerMentorConnection(JoinerMentorConnectionDto joinerMentorConnectionDto);
    boolean deleteDetails(Long joinerId);


}
