package com.tyss.homedelivey.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.DtoInstantiatingConverter;
import org.springframework.stereotype.Service;

import com.tyss.homedelivey.UserDto.AssignDto;
import com.tyss.homedelivey.UserDto.DeliveryGuysDto;
import com.tyss.homedelivey.UserDto.UserDetailsDto;
import com.tyss.homedelivey.UserPojo.DeliveryGuys;
import com.tyss.homedelivey.UserPojo.UserDetails;
import com.tyss.homedelivey.exception.NotFoundException;
import com.tyss.homedelivey.repository.DeliveryAddressRepository;
import com.tyss.homedelivey.repository.DeliveryGuyRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HomeDeliveryServiceImpl implements HomeDeliveryService {

	@Autowired
	private DeliveryAddressRepository addressRepository;

	@Autowired
	private DeliveryGuyRepository guyRepository;

	@Override
	public UserDetails addUserDeatails(UserDetailsDto detailsDto) {
		try {
			UserDetails userDetails = new UserDetails();
			log.info("User Deatils Added");
			BeanUtils.copyProperties(detailsDto, userDetails);
			
			if (detailsDto == null) {
				log.error("User Deatils Is Not Added Succesfully");
				throw new NotFoundException("User Deatails Not Added Succesfully");
			} else {
				log.warn("Data save In User Details");
				
				UserDetails save = addressRepository.save(userDetails);
				save.setUserDetailsId(save.getUserId());
				return addressRepository.save(save);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public UserDetails findUserDeatils(Integer userId) {
		try {
			log.error("User Details Is Not Found By User Id");
			return addressRepository.findById(userId)
					.orElseThrow(() -> new NotFoundException("User Deatils Is Not Found For Particular Id"));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public UserDetails updateUserdetails(Integer userId, UserDetailsDto detailsDto) {
		try {
			log.error("User Id Is Not Found Which Id You Update");
			UserDetails userDetails = addressRepository.findById(userId)
					.orElseThrow(() -> new NotFoundException("Id Not Found"));
			BeanUtils.copyProperties(detailsDto, userDetails);
			log.info("User Details Is Updated Successfully");
			addressRepository.save(userDetails);
			return userDetails;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void deleteUserDetails(Integer userId) {
		try {
			log.info("User Deleted By User Id");
			UserDetails userDetails = addressRepository.findById(userId)
					.orElseThrow(() -> new NotFoundException("Id Not Found"));
			addressRepository.delete(userDetails);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public List<UserDetails> findAllUserDetails() {
		log.info("List Of Users Details Is Present");
		List<UserDetails> findAll = addressRepository.findAll();
		if (findAll.isEmpty()) {
			log.error("No User Details Is Present");
			throw new NotFoundException("No Data Is Present In User Details");
		}
		return findAll;
	}

	@Override
	public DeliveryGuys addDeliveryGuysDeatils(DeliveryGuysDto deliveryGuysDto) {
		try {
			DeliveryGuys deliveryGuys = new DeliveryGuys();
			log.info("Delivery Guys Deatils Added");
			BeanUtils.copyProperties(deliveryGuysDto, deliveryGuys);
			if (deliveryGuysDto == null) {
				log.error("User Deatils Is Not Added Succesfully");
				throw new NotFoundException("Delivery Guys Details Not Added Succesfully");
			} else {
				log.warn("Delivery Guys Data Saved");
				return guyRepository.save(deliveryGuys);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public DeliveryGuys findDeliveryGuys(Integer deliveryGuyId) {
		try {
			log.error("Delivery Guy  Is Not Found By Delivery Guy Id");
			return guyRepository.findById(deliveryGuyId)
					.orElseThrow(() -> new NotFoundException("Delivery Guy  Deatils Is Not Found For Particular Id"));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public DeliveryGuys updateDeliveryGuy(Integer deliveryGuyId, DeliveryGuysDto deliveryGuysDto) {
		try {
			log.error("Delivery Guy Id Is Not Found Which Id You Want Update");
			DeliveryGuys deliveryGuy = guyRepository.findById(deliveryGuyId)
					.orElseThrow(() -> new NotFoundException("Delivery Guy Id Not Found"));
			BeanUtils.copyProperties(deliveryGuysDto, deliveryGuy);
			log.info("Delivery Guys Details Is Updated Successfully");
			guyRepository.save(deliveryGuy);
			return deliveryGuy;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void deleteDeliveryGuy(Integer deliveryGuyId) {
		try {
			log.info("Delivery Guys Deleted By Delivery Guy Id");
			DeliveryGuys deliveryGuys = guyRepository.findById(deliveryGuyId)
					.orElseThrow(() -> new NotFoundException("Delivery Guy Id Not Found"));
			guyRepository.delete(deliveryGuys);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<DeliveryGuys> findAllDeliveryGuys() {
		log.info("List Of Delievry Guys Details Is Present");
		List<DeliveryGuys> findAll = guyRepository.findAll();
		if (findAll.isEmpty()) {
			log.error("No Delivery Guy Details Is Present");
			throw new NotFoundException("No Data Is Present In Delivery Guy");
		}
		return findAll;
	}

	@Override
	public AssignDto assignDeliveryGuyToUser(AssignDto assignDto) {
		try {
		Integer userDetailsId = assignDto.getUser().getUserDetailsId();
		Optional<UserDetails> userDetails = addressRepository.findByUserDetailsId(userDetailsId);
		
			if (userDetails == null) {
				throw new NotFoundException("User Deatils Is Not Found");
			}
			log.info("User Details Found Successfully");

			 List<DeliveryGuys> findAll = guyRepository.findAll();
			if (findAll.isEmpty()) {
				throw new NotFoundException("No Delivery Guys Available");
			}
			List<DeliveryGuys> collect = findAll.stream().filter(t -> {
				return t.getAvaliableFrom().isAfter(userDetails.get().getRequiredFrom())
						|| t.getAvaliableTo().isBefore(userDetails.get().getRequiredTo());
			}).collect(Collectors.toList());

			if (collect.isEmpty()) {

				throw new NotFoundException(
						" Delivery Guy is already occupied with other delivery. We are unable to assign delivery of your product to other delivery persons also right now");
			} else {

				List<DeliveryGuys> collect2 = collect.stream().filter(t -> {
					return t.getLocation().equalsIgnoreCase(userDetails.get().getAddress());
				}).collect(Collectors.toList());

				if (collect2.isEmpty()) {
					throw new NotFoundException("Delivery Guy Is Not Availabel At This Address");
				} else {
					
					DeliveryGuys deliveryBoy = collect2.stream().max((a1, a2) -> a1.getRating() - a2.getRating())
							.get();
					userDetails.get().setDeliveryGuys(deliveryBoy);
					deliveryBoy.setAvaliableFrom(userDetails.get().getRequiredTo());
					deliveryBoy.setAvaliableTo(userDetails.get().getRequiredFrom());
					deliveryBoy.setAvailability(false);
					addressRepository.save(userDetails.get());
					guyRepository.save(deliveryBoy);
					assignDto.setUser(userDetails.get());
					return assignDto;
				}
			}
	}	
	catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	
	}	
}
