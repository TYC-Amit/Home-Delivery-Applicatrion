package com.tyss.homedelivey.UserDto;

import java.time.LocalTime;
import java.util.List;

import com.tyss.homedelivey.UserPojo.DeliveryGuys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {

	private Integer userDetailsId;
	
	private String userName;

	private String address;

	private LocalTime requiredFrom;

	private LocalTime requiredTo;

	private DeliveryGuys deliveryGuys;
}
