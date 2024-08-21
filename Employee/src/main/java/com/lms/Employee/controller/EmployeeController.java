package com.lms.Employee.controller;

import com.lms.Employee.EmployeeApplication;
import com.lms.Employee.dto.EmployeeDto;
import com.lms.Employee.dto.ResponseDto;
import com.lms.Employee.entity.Employee;
import com.lms.Employee.entity.JoinerMentorConnection;
import com.lms.Employee.service.IEmployeeService;
import com.lms.Employee.service.impl.EmployeeServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class EmployeeController {

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private EmployeeServiceImpl employeeService;

    private final IEmployeeService iEmployeeService;

    public EmployeeController(IEmployeeService iEmployeeService){
        this.iEmployeeService = iEmployeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createEmployee(@RequestBody @Valid EmployeeDto employeeDto){
        iEmployeeService.createEmployee(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("Successfully Created", HttpStatus.CREATED));
    }
    @GetMapping("/fetch")
    public ResponseEntity<EmployeeDto> fetchEmployeeDetails(@RequestParam String email){
        EmployeeDto employeeDto = iEmployeeService.fetchDetails(email);
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeDto);
    }

    @GetMapping("/fetchByRole")
    public ResponseEntity<List<Employee>> fetchDetailsByRole(@RequestParam String role) {
        List<Employee> list = iEmployeeService.fetchDetailsByRole(role);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(list);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateDetails(@RequestBody EmployeeDto employeeDto){
        boolean isUpdated = iEmployeeService.updateDetails(employeeDto);

        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto("Updated Successfully", HttpStatus.ACCEPTED));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto("Unable to update", HttpStatus.BAD_REQUEST));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteDetails(@RequestParam String email){
        boolean isDeleted = iEmployeeService.deleteDetails(email);

        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto("Deleted Successfully", HttpStatus.ACCEPTED));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto("Unable to delete", HttpStatus.BAD_REQUEST));
        }
    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(buildVersion);
    }

    @GetMapping("/employee/{employeeId}/role")
    public String getRole(@PathVariable Long employeeId) {
        return employeeService.getRoleByEmployeeId(employeeId);
    }
}

