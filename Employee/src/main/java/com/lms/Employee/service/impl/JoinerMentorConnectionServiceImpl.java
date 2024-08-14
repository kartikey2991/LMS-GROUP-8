package com.lms.Employee.service.impl;

import com.lms.Employee.dto.EmployeeDto;
import com.lms.Employee.dto.JoinerMentorConnectionDto;
import com.lms.Employee.entity.Employee;
import com.lms.Employee.entity.JoinerMentorConnection;
import com.lms.Employee.exception.EmployeeAlreadyExistsException;
import com.lms.Employee.exception.JoinerMentorConnectionAlreadyExistsException;
import com.lms.Employee.mapper.EmployeeMapper;
import com.lms.Employee.mapper.JoinerMentorConnectionMapper;
import com.lms.Employee.repository.EmployeeRepository;
import com.lms.Employee.repository.JoinerMentorConnectionRepository;
import com.lms.Employee.service.IJoinerMentorConnectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JoinerMentorConnectionServiceImpl implements IJoinerMentorConnectionService {

    private final EmployeeServiceImpl employeeService;
    private JoinerMentorConnectionRepository joinerMentorConnectionRepository;

    @Override
    public String getRole(Long employeeId){
        return employeeService.getRoleByEmployeeId(employeeId);
    }

    @Override
    public void createJoinerMentorConnection(JoinerMentorConnectionDto joinerMentorConnectionDto) {
        Optional<JoinerMentorConnection> foundJoinerMentorConnection = joinerMentorConnectionRepository.findByJoinerId(joinerMentorConnectionDto.getJoinerId());
        long mentorId = joinerMentorConnectionDto.getMentorId();
        long joinerId = joinerMentorConnectionDto.getJoinerId();
        String role = getRole(mentorId);
        if(Objects.equals(getRole(mentorId), "mentor") && Objects.equals(getRole(joinerId), "joiner")) {
            if (foundJoinerMentorConnection.isPresent()) {
                throw new JoinerMentorConnectionAlreadyExistsException("JoinerMentorConnection Already Exists For Given joinerId " + joinerMentorConnectionDto.getJoinerId());
            }
            JoinerMentorConnection joinerMentorConnection = JoinerMentorConnectionMapper.mapToJoinerMentorConnection(joinerMentorConnectionDto, new JoinerMentorConnection());
            joinerMentorConnectionRepository.save(joinerMentorConnection);
        }else{
            throw new JoinerMentorConnectionAlreadyExistsException("Wrong input connections");
        }
    }
    @Override
    public List<Long> getJoinerIdsByMentorId(Long mentorId) {
        return joinerMentorConnectionRepository.findJoinerIdsByMentorId(mentorId);
    }
}
