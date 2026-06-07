package com.asset.asset_api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asset.asset_api.entity.SeatUserAlloted;

public interface SeatUserAllotedRepository extends JpaRepository<SeatUserAlloted, Integer>{

	
	
	@Query("""
				from SeatUserAlloted as a where a.activeStatus='Y' and a.user.activeStatus='Y'
			""")
	static
			
			 List<SeatUserAlloted> getAllUsersUnderCurrentOffice(Integer officeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
