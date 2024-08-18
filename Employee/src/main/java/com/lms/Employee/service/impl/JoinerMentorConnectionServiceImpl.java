package com.lms.Employee.service.impl;

import com.lms.Employee.dto.JoinerMentorConnectionDto;
import com.lms.Employee.entity.JoinerMentorConnection;
import com.lms.Employee.exception.JoinerMentorConnectionAlreadyExistsException;
import com.lms.Employee.exception.ResourceNotFoundException;
import com.lms.Employee.mapper.JoinerMentorConnectionMapper;
import com.lms.Employee.repository.JoinerMentorConnectionRepository;
import com.lms.Employee.service.IJoinerMentorConnectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        JoinerMentorConnection foundJoinerMentorConnection = joinerMentorConnectionRepository.findByJoinerId(joinerMentorConnectionDto.getJoinerId());
        long mentorId = joinerMentorConnectionDto.getMentorId();
        long joinerId = joinerMentorConnectionDto.getJoinerId();
        String role = getRole(mentorId);
        if(Objects.equals(getRole(mentorId), "mentor") && Objects.equals(getRole(joinerId), "joiner")) {
            if (foundJoinerMentorConnection != null) {
                throw new JoinerMentorConnectionAlreadyExistsException("JoinerMentorConnection Already Exists For Given joinerId " + joinerMentorConnectionDto.getJoinerId());
            }
            JoinerMentorConnection joinerMentorConnection = JoinerMentorConnectionMapper.mapToJoinerMentorConnection(joinerMentorConnectionDto, new JoinerMentorConnection());
            joinerMentorConnectionRepository.save(joinerMentorConnection);
        }else{
            throw new JoinerMentorConnectionAlreadyExistsException("Wrong input connections");
        }
    }
    @Override
    public List<JoinerMentorConnection> getJoinerIdsByMentorId(Long mentorId) {
        System.out.println("TEST--------------------------");
        List<JoinerMentorConnection> allByMentorId = joinerMentorConnectionRepository.findAllByMentorId(mentorId);
        System.out.println(allByMentorId);
        return allByMentorId;
    }

    @Override
    public boolean updateJoinerMentorConnection(JoinerMentorConnectionDto joinerMentorConnectionDto) {
        boolean isUpdated = false;

        JoinerMentorConnection joinerMentorConnection = joinerMentorConnectionRepository.findByJoinerId(joinerMentorConnectionDto.getJoinerId());
        String stringJoinerId = String.valueOf(joinerMentorConnectionDto.getJoinerId());
        if(joinerMentorConnection == null) {
            throw new ResourceNotFoundException("JoinerMentorConnection", "joinerMentorConnection", stringJoinerId);
        }
        else{
            long mentorId = joinerMentorConnectionDto.getMentorId();
            long joinerId = joinerMentorConnectionDto.getJoinerId();
            String role = getRole(mentorId);
            if(Objects.equals(getRole(mentorId), "mentor") && Objects.equals(getRole(joinerId), "joiner")) {
                JoinerMentorConnectionMapper.mapToJoinerMentorConnection(joinerMentorConnectionDto, joinerMentorConnection);
                joinerMentorConnectionRepository.save(joinerMentorConnection);
                isUpdated = true;
            }
        }

        return isUpdated;
    }

    @Override
    public boolean deleteDetails(Long joinerId) {
        boolean isDeleted = false;

        JoinerMentorConnection joinerMentorConnection = joinerMentorConnectionRepository.findByJoinerId(joinerId);
        String stringJoinerId = String.valueOf(joinerId);
        if(joinerMentorConnection == null) {
            throw new ResourceNotFoundException("JoinerMentorConnection", "joinerMentorConnection", stringJoinerId);
        }else{
            joinerMentorConnectionRepository.delete(joinerMentorConnection);
            isDeleted = true;
        }

        return isDeleted;
    }
}
