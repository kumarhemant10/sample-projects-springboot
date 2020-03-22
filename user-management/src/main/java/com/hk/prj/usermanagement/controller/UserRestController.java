package com.hk.prj.usermanagement.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hk.prj.usermanagement.constants.UrlConstant;
import com.hk.prj.usermanagement.dto.UserDto;
import com.hk.prj.usermanagement.model.User;
import com.hk.prj.usermanagement.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {
	
	@Autowired UserService userService;
	@Autowired ModelMapper modelMapper;
	
	@GetMapping(UrlConstant.USERS)
	@ResponseStatus(HttpStatus.OK)
	public List<UserDto> get() {
		List<User> users = userService.findAll();
		return convertEntityToDto(users);
	}
	
	@GetMapping(UrlConstant.USERS_BY_ID)
	@ResponseStatus(HttpStatus.OK)
	public UserDto get(@PathVariable("id") Long userId) {
		return convertEntityToDto(userService.findById(userId));
	}
	
	@PostMapping(UrlConstant.USERS)
	@ResponseStatus(HttpStatus.CREATED)
	public UserDto save(@RequestBody UserDto userDto) {
		User user = convertDtoToEntity(userDto);
		userService.save(user);
		return convertEntityToDto(user);
	}

	@PutMapping(UrlConstant.USERS_BY_ID)
	@ResponseStatus(HttpStatus.OK)
	public UserDto update(@PathVariable("id") Long userId, @RequestBody UserDto userDto) {
		User user = convertDtoToEntity(userDto);
		userService.save(user);
		return convertEntityToDto(user);
	}
	
	@DeleteMapping(UrlConstant.USERS_BY_ID)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long userId) {
		userService.delete(userId);
	}
	
	private User convertDtoToEntity(UserDto userDto) {
		User user = null;
		if(ObjectUtils.isEmpty(userDto.getUserId())){
			user = modelMapper.map(userDto, User.class);
		}else {
			user = userService.findById(userDto.getUserId());
			user.setUserName(userDto.getUserName());
			user.setMiddleName(userDto.getMiddleName());
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setEmail(userDto.getEmail());
		}
		return user;
	}

	private List<UserDto> convertEntityToDto(List<User> users) {
		return users.stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}
	
	private UserDto convertEntityToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}
	

}
