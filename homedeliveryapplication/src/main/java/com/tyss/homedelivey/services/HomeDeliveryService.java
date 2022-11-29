package com.tyss.homedelivey.services;

import java.util.List;

import com.tyss.homedelivey.UserDto.AssignDto;
import com.tyss.homedelivey.UserDto.DeliveryGuysDto;
import com.tyss.homedelivey.UserDto.UserDetailsDto;
import com.tyss.homedelivey.UserPojo.DeliveryGuys;
import com.tyss.homedelivey.UserPojo.UserDetails;

public interface HomeDeliveryService {
	
	public UserDetails addUserDeatails(UserDetailsDto detailsDto);
	
	public UserDetails findUserDeatils(Integer userId);

	public UserDetails updateUserdetails(Integer userId,UserDetailsDto detailsDto);
	
	public void deleteUserDetails(Integer userId);
	
	public List<UserDetails> findAllUserDetails();
	
	
	public DeliveryGuys addDeliveryGuysDeatils(DeliveryGuysDto deliveryGuysDto);
	
	public DeliveryGuys findDeliveryGuys(Integer deliveryGuyId);
	
	public DeliveryGuys updateDeliveryGuy(Integer deliveryGuyId,DeliveryGuysDto deliveryGuysDto);
	
	public void deleteDeliveryGuy(Integer deliveryGuyId);
	
	public List<DeliveryGuys> findAllDeliveryGuys();
	
	public AssignDto assignDeliveryGuyToUser(AssignDto assignDto);

}
