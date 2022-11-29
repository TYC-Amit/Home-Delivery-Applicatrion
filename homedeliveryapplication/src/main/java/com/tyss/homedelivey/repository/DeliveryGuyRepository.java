package com.tyss.homedelivey.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.homedelivey.UserPojo.DeliveryGuys;

@Repository
public interface DeliveryGuyRepository extends JpaRepository<DeliveryGuys, Integer> {

	List<DeliveryGuys> findAllByRatingOrderByRatingAsc(int rating);

}
