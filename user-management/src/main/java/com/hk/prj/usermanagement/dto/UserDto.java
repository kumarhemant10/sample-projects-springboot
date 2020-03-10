package com.hk.prj.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto class to hold user 
 * 
 * @author hemant
 * @since v1
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private Long userId;
	private String userName;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	
	public UserDto(String userName, String firstName, String middleName, String lastName, String email) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
	}	
	
	
	
}
