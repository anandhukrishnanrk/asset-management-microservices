package com.cis.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cis.api.entity.Client;
import com.cis.api.entity.UserSetup;




public interface UserRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByName(String username);

	
	

}
