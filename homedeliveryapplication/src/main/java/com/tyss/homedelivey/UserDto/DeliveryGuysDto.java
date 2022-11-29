package com.tyss.homedelivey.UserDto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryGuysDto {

	private String deliveryGuyName;
	
	private String location;
	
	private boolean availability;
	
	private LocalTime avaliableFrom;
	
	private LocalTime avaliableTo;
	
	private int rating;
}
