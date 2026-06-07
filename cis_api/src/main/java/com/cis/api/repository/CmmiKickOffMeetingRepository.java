package com.cis.api.repository;

import com.cis.api.entity.CmmiKickOffMeeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CmmiKickOffMeetingRepository extends JpaRepository<CmmiKickOffMeeting, Integer> {
    CmmiKickOffMeeting findByCmmiProjectId(Long cmmiProjectId);
}
