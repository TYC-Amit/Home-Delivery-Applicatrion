package com.tyss.homedelivey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.homedelivey.UserDto.AssignDto;
import com.tyss.homedelivey.UserDto.DeliveryGuysDto;
import com.tyss.homedelivey.UserDto.UserDetailsDto;
import com.tyss.homedelivey.UserPojo.DeliveryGuys;
import com.tyss.homedelivey.UserPojo.UserDetails;
import com.tyss.homedelivey.response.ResponseMaster;
import com.tyss.homedelivey.services.HomeDeliveryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HomeDeliveryController {

	@Autowired
	private HomeDeliveryService deliveryService;

	@PostMapping("/add-user-details")
	public ResponseEntity<ResponseMaster> addUserDeatils(@RequestBody UserDetailsDto detailsDto) {
		log.info("Add User Details");
		UserDetails addUserDeatails = deliveryService.addUserDeatails(detailsDto);
		return new ResponseEntity<ResponseMaster>(
				new ResponseMaster(false, "Delivery for the user: Sushma is assigned to the delivery person: \r\n"
						+ "Kartik successfully\r\n" + "", addUserDeatails),
				HttpStatus.OK);
	}

	@GetMapping("/find-user-deatils-by-Id")
	public ResponseEntity<ResponseMaster> findUserDeatils(@RequestParam Integer userId) {
		log.info("Find User Deatils By User Id");
		UserDetails findUserDeatils = deliveryService.findUserDeatils(userId);
		return new ResponseEntity<ResponseMaster>(
				new ResponseMaster(false, "User Deatils Is Found  By Id", findUserDeatils), HttpStatus.OK);

	}

	@PutMapping("/update-user-details/{userId}")
	public ResponseEntity<ResponseMaster> updateUserDeatils(@PathVariable("userId") Integer userId,
			@RequestBody UserDetailsDto detailsDto) {
		log.info("Update User Details");
		UserDetails updateUserdetails = deliveryService.updateUserdetails(userId, detailsDto);
		return new ResponseEntity<>(new ResponseMaster(false, "User Details Updated Succesfully", updateUserdetails), HttpStatus.OK);
	}

	@DeleteMapping("/delete-user-details")
	public ResponseEntity<ResponseMaster> deleteUserDetails(@RequestParam Integer userId) {
		log.info("User Details Deleted Succesfully");
		deliveryService.deleteUserDetails(userId);
		return new ResponseEntity<>(new ResponseMaster(false, "Data Deleted Sucessfully", deliveryService), HttpStatus.OK);

	}
	
	@GetMapping("/find-all-user-details")
	public ResponseEntity<ResponseMaster> findAllUserDetails(){
		log.info("All Users Deatils Is Find By User Id");
		List<UserDetails> findAllUserDetails = deliveryService.findAllUserDetails();
		return new ResponseEntity<>(new ResponseMaster(false, "All User Details Is There", findAllUserDetails),HttpStatus.OK);
		
	}
	
	@PostMapping("/add-delivery-guys-details")
	public ResponseEntity<ResponseMaster> addDeliveryGuysDeatils(@RequestBody DeliveryGuysDto deliveryGuysDto) {
		log.info("Add Delivery Guys Details");
		 DeliveryGuys addDeliveryGuysDeatils = deliveryService.addDeliveryGuysDeatils(deliveryGuysDto);
		return new ResponseEntity<ResponseMaster>(
				new ResponseMaster(false, "Delivery for the user: Sushma is assigned to the delivery person: \r\n"
						+ "Kartik successfully\r\n" + "", addDeliveryGuysDeatils),
				HttpStatus.OK);
	}

	@GetMapping("/find-delivery-guy-by-Id")
	public ResponseEntity<ResponseMaster> findDeliveryGuys(@RequestParam Integer deliveryGuyId) {
		log.info("Find Delivery Guy By User Id");
		 DeliveryGuys findDeliveryGuys = deliveryService.findDeliveryGuys(deliveryGuyId);
		return new ResponseEntity<ResponseMaster>(
				new ResponseMaster(false, "User Deatils Is Found  By Id", findDeliveryGuys), HttpStatus.OK);

	}

	@PutMapping("/update-delivery-guy/{deliveryGuyId}")
	public ResponseEntity<ResponseMaster> updateDeliveryGuy(@PathVariable("deliveryGuyId") Integer deliveryGuyId,
			@RequestBody DeliveryGuysDto deliveryGuysDto) {
		log.info("Update Delivery Guys Details");
		DeliveryGuys updateDeliveryGuy = deliveryService.updateDeliveryGuy(deliveryGuyId, deliveryGuysDto);
		return new ResponseEntity<>(new ResponseMaster(false, "User Details Updated Succesfully", updateDeliveryGuy), HttpStatus.OK);
	}

	@DeleteMapping("/delete-delivery-guy-details")
	public ResponseEntity<ResponseMaster> deleteDeliveryGuy(@RequestParam Integer deliveryGuyId) {
		log.info("Delivery Guys Details Deleted Succesfully");
		deliveryService.deleteDeliveryGuy(deliveryGuyId);
		return new ResponseEntity<>(new ResponseMaster(false, "Data Deleted Sucessfully", deliveryService), HttpStatus.OK);

	}
	
	@GetMapping("/find-all-delivery-guy-details")
	public ResponseEntity<ResponseMaster> findAllDeliveryGuys(){
		log.info("All Delivery Guy Is Find By Delivery Guy Id");
		List<DeliveryGuys> findAllDeliveryGuys = deliveryService.findAllDeliveryGuys();
		return new ResponseEntity<>(new ResponseMaster(false, "All User Details Is There", findAllDeliveryGuys),HttpStatus.OK);	
	}
	
	@PostMapping("/assign-delivery-guy-to-user")
	public ResponseEntity<ResponseMaster> assignDeliveryGuyToUser(@RequestBody AssignDto assignDto){
		AssignDto details = deliveryService.assignDeliveryGuyToUser(assignDto);
        return new ResponseEntity<>(new ResponseMaster(false, "aassign", details),HttpStatus.OK);
        		
		
		
	}
}
