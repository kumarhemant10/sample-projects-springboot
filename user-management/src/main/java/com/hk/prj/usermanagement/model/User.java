package com.hk.prj.usermanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="user", schema = "um")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@NotBlank(message = "user name is mandatory")
	private String userName;
	@NotBlank(message = "first name is mandatory")
	private String firstName;
	private String middleName;
	@NotBlank(message = "first name is mandatory")
	private String lastName;
	@NotBlank(message = "email is mandatory")
	private String email;
	
	public User(String userName, String firstName, String middleName, String lastName, String email) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
	}
	
	
	
}
