package com.lms.Employee.controller;

import com.lms.Employee.dto.EmployeeDto;
import com.lms.Employee.dto.ResponseDto;
import com.lms.Employee.service.IEmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Validated
public class EmployeeController {

    private IEmployeeService iEmployeeService;

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
}
