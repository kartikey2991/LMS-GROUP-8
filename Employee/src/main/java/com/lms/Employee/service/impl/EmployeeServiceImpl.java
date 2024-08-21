package com.lms.Employee.service.impl;

import com.lms.Employee.dto.EmployeeDto;
import com.lms.Employee.entity.Employee;
import com.lms.Employee.entity.JoinerMentorConnection;
import com.lms.Employee.exception.EmployeeAlreadyExistsException;
import com.lms.Employee.exception.ResourceNotFoundException;
import com.lms.Employee.mapper.EmployeeMapper;
import com.lms.Employee.repository.EmployeeRepository;
import com.lms.Employee.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private EmployeeRepository employeeRepository;


    @Override
    public void createEmployee(EmployeeDto employeeDto) {
        String email = employeeDto.getEmail();

        Optional<Employee> foundEmployee = employeeRepository.findByEmail(email);

        if (foundEmployee.isPresent()) {
            throw new EmployeeAlreadyExistsException("Employee Already Exists For Given Email " + email);
        }
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto, new Employee());
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeDto fetchDetails(String email) {
        Employee employee = employeeRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "email", email)
        );
        return EmployeeMapper.mapToEmployeeDto(employee, new EmployeeDto());
    }

    @Override
    public boolean updateDetails(EmployeeDto employeeDto) {
        boolean isUpdated = false;

        Employee employee = employeeRepository.findByEmail(employeeDto.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "email", employeeDto.getEmail())
        );

        if (employee != null) {
            EmployeeMapper.mapToEmployee(employeeDto, employee);
            employeeRepository.save(employee);
            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteDetails(String email) {
        boolean isDeleted = false;

        Employee employee = employeeRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "email", email)
        );

        if (employee != null) {
            employeeRepository.delete(employee);
            isDeleted = true;
        }

        return isDeleted;
    }

    @Override
    public List<Employee> fetchDetailsByRole(String role) {
        List<Employee> allByRole = employeeRepository.findAllByRole(role);
        return allByRole;
    }

    public String getRoleByEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if (employee != null) {
            return employee.getRole();
        } else {
            // Handle the case when the employee is not found
            throw new RuntimeException("Employee not found with id: " + employeeId);
        }
    }
}