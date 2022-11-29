package com.tyss.homedelivey.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.homedelivey.UserPojo.UserDetails;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<UserDetails, Integer>{

	Optional<UserDetails> findByUserDetailsId(Integer userDetailsId);
	
}
