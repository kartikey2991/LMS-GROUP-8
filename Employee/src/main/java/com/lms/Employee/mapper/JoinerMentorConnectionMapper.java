package com.lms.Employee.mapper;

import com.lms.Employee.dto.JoinerMentorConnectionDto;
import com.lms.Employee.entity.JoinerMentorConnection;

public class JoinerMentorConnectionMapper {
    public static JoinerMentorConnection mapToJoinerMentorConnection(JoinerMentorConnectionDto joinerMentorConnectionDto, JoinerMentorConnection joinerMentorConnection){
//        joinerMentorConnection.setConnectionId(joinerMentorConnectionDto.getConnectionId());
        joinerMentorConnection.setJoinerId(joinerMentorConnectionDto.getJoinerId());
        joinerMentorConnection.setMentorId(joinerMentorConnectionDto.getMentorId());
        return joinerMentorConnection;
    }

    public static JoinerMentorConnectionDto mapToJoinerMentorConnectionDto(JoinerMentorConnection joinerMentorConnection, JoinerMentorConnectionDto joinerMentorConnectionDto){
//        joinerMentorConnectionDto.setConnectionId(joinerMentorConnection.getConnectionId());
        joinerMentorConnectionDto.setJoinerId(joinerMentorConnection.getJoinerId());
        joinerMentorConnectionDto.setMentorId(joinerMentorConnection.getMentorId());
        return joinerMentorConnectionDto;
    }
}
