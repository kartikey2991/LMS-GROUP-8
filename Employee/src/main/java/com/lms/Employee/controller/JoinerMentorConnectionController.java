package com.lms.Employee.controller;

import com.lms.Employee.dto.EmployeeDto;
import com.lms.Employee.dto.JoinerMentorConnectionDto;
import com.lms.Employee.dto.ResponseDto;
import com.lms.Employee.entity.JoinerMentorConnection;
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
    public ResponseEntity<List<JoinerMentorConnection>> getJoinerIds(@PathVariable Long mentorId) {
        List<JoinerMentorConnection> list = iJoinerMentorConnectionService.getJoinerIdsByMentorId(mentorId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(list);
    }

    @PutMapping("/updateJoinerMentorConnection/")
    public ResponseEntity<ResponseDto> updateDetails(@RequestBody JoinerMentorConnectionDto joinerMentorConnectionDto){
        boolean isUpdated = iJoinerMentorConnectionService.updateJoinerMentorConnection(joinerMentorConnectionDto);

        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto("Updated Successfully", HttpStatus.ACCEPTED));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto("Unable to update", HttpStatus.BAD_REQUEST));
        }
    }

    @DeleteMapping("/deleteJoinerMentorConnection/")
    public ResponseEntity<ResponseDto> deleteJoinerMentorConnection(@RequestParam Long joinerId){
        boolean isDeleted = iJoinerMentorConnectionService.deleteDetails(joinerId);

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
