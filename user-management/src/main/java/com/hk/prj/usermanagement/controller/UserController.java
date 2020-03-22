package com.hk.prj.usermanagement.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.hk.prj.usermanagement.constants.UrlConstant;
import com.hk.prj.usermanagement.dto.UserDto;
import com.hk.prj.usermanagement.model.User;
import com.hk.prj.usermanagement.service.UserService;

@Controller
public class UserController {
	
	@Autowired UserService userService;
	@Autowired ModelMapper modelMapper;
	
	@GetMapping("/user-management")
	public ModelAndView getUserManagementView(ModelAndView modelAndView) {
		modelAndView.setViewName("users");
		modelAndView.addObject("users", userService.findAll());
		return modelAndView;
	}

	@GetMapping("/add-user-view")
	public ModelAndView getAddUserView(ModelAndView modelAndView) {
		modelAndView.setViewName("add-user");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}
	
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
	public ModelAndView save(@ModelAttribute("user") UserDto userDto) {
		User user = convertDtoToEntity(userDto);
		userService.save(user);
		ModelAndView modelAndView = new ModelAndView("add-user");
		modelAndView.addObject("user", new User());
		modelAndView.addObject("savedUser", user);
		return modelAndView;
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
