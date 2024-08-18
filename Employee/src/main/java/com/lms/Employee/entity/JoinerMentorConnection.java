package com.lms.Employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "JoinerMentorConnection")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JoinerMentorConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long connectionId;
    private Long joinerId;
    private Long mentorId;
}
