package com.cis.api.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cis.api.entity.CmpUploadDetails;

@Repository
public interface CmpUploadDetailsRepository extends JpaRepository<CmpUploadDetails, Long> {

    // @Query("SELECT cud FROM CmpUploadDetails cud WHERE cud.cmpDetails.projectId =
    // :projectId AND cud.cmpDetails.deleteStatus = 'N'")
//    Collection<CmpUploadDetails> findAllByCmpDetailsProjectIdAndCmpDetailsDeleteStatusOrderByCmpDetailsCmpIdDesc(
//            @Param("projectId") Integer projectId, String deleteStatus);
	
	Collection<CmpUploadDetails>
	findAllByCmpDetailsProjectIdAndCmpDetailsDeleteStatusOrderByCmpDetailsCmpIdDesc(
	        Integer projectId,
	        String deleteStatus
	);
	
	
}
