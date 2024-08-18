package com.lms.Employee.repository;

import com.lms.Employee.entity.JoinerMentorConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JoinerMentorConnectionRepository extends JpaRepository<JoinerMentorConnection, Long> {

      JoinerMentorConnection findByJoinerId(Long joinerId);


//    @Query("SELECT mj.joinerId FROM MentorJoiner mj WHERE mj.mentorId = :mentorId")
//    List<Long> findJoinerIdsByMentorId(Long mentorId);

    List<JoinerMentorConnection> findAllByMentorId(Long mentorId);
}
