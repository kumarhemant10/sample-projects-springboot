
package com.hk.prj.usermanagement;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.hk.prj.usermanagement.constants.UrlConstant;
import com.hk.prj.usermanagement.dto.UserDto;
import com.hk.prj.usermanagement.model.User;
import com.hk.prj.usermanagement.service.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest 
public class UserControllerTests extends AbstractTest {

	@MockBean UserService userService;
	
	@Before public void setUpUserService() {
		when(userService.findAll()).thenReturn(getAllUsers());
		when(userService.findById(Long.valueOf(1))).thenReturn(getAllUsers().get(1));
		when(userService.save(any(User.class))).thenReturn(getAllUsers().get(0));
	}
	
	private List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		users.add(new User("user 1", "f name1","m name1","l name1","user1@email.com"));
		users.add(new User("user 2", "f name2","m name2","l name2","user2@email.com"));
		users.add(new User("user 3", "f name3","m name3","l name3","user3@email.com"));
		users.add(new User("user 4", "f name4","m name4","l name4","user4@email.com"));
		users.add(new User("user 5", "f name5","m name5","l name5","user5@email.com"));
		return users;
	}

	@Test public void getAllUserTest() throws Exception {
		mockMvc.perform(get(UrlConstant.USERS))			
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(5)))
		    .andExpect(jsonPath("$[0].userName", is("user 1"))); 
	}

	@Test public void getUserById1() throws Exception {
		mockMvc.perform(get(UrlConstant.USERS_BY_ID,1))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.userName", is("user 2")));
		
	}
	
	/**
	 * test that if null object is passed to create user then controller should return exception {@link BadRequest} 
	 * 
	 * @throws Exception
	 */
	@Test public void postBadRequestTest() throws Exception { 
		UserDto userDto = null;
		mockMvc.perform(post(UrlConstant.USERS).contentType(MediaType.APPLICATION_JSON).content(mapToJson(userDto))) 
		.andExpect(status().isBadRequest());

		//verify that repository save method is not called
		verify(userService, times(0)).save(any(User.class));
	}

	@Test public void postTest() throws Exception { 
		UserDto userDto = new UserDto("user 1", "f name1","m name1","l name1","user1@email.com");
		mockMvc.perform(post(UrlConstant.USERS).contentType(MediaType.APPLICATION_JSON).content(mapToJson(userDto))) 
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.userName", is("user 1")));
		
		//verify that repository method is called once
		verify(userService, times(1)).save(any(User.class));
	}
	 
}
