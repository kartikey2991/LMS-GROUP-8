package com.lms.Employee.controller;

import com.lms.Employee.dto.EmployeeDto;
import com.lms.Employee.dto.JoinerMentorConnectionDto;
import com.lms.Employee.dto.ResponseDto;
import com.lms.Employee.service.IJoinerMentorConnectionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class JoinerMentorConnectionController {

    private final IJoinerMentorConnectionService iJoinerMentorConnectionService;

    public JoinerMentorConnectionController(IJoinerMentorConnectionService iJoinerMentorConnectionService) {
        this.iJoinerMentorConnectionService = iJoinerMentorConnectionService;
    }

    @PostMapping("/createConnection")
    public ResponseEntity<ResponseDto> createJoinerMentorConnection(@RequestBody @Valid JoinerMentorConnectionDto joinerMentorConnectionDto){
        iJoinerMentorConnectionService.createJoinerMentorConnection(joinerMentorConnectionDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("Successfully Created", HttpStatus.CREATED));
    }

    @GetMapping("/fetchByMentorId/{mentorId}/")
    public List<Long> getJoinerIds(@PathVariable Long mentorId) {
        return iJoinerMentorConnectionService.getJoinerIdsByMentorId(mentorId);
    }
}
