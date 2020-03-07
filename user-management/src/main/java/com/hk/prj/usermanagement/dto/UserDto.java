package com.hk.prj.usermanagement.dto;

import lombok.Data;

/**
 * Dto class to hold user 
 * 
 * @author hemant
 * @since v1
 *
 */
@Data
public class UserDto {
	
	private Long userId;
	private String userName;
	private String email;
	private String firstName;
	private String middleName;
	private String lastName;
	
	
}
