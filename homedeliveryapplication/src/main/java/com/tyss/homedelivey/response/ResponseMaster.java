package com.tyss.homedelivey.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMaster {

	private boolean error;
	private String message;
	private Object data;
}
