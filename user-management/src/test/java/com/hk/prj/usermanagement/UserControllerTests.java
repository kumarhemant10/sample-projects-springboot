package com.hk.prj.usermanagement;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.hk.prj.usermanagement.constants.UrlConstant;
import com.hk.prj.usermanagement.dto.UserDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests extends AbstractTest {

	@Test
	public void getAllUserTest() throws Exception {
		mockMvc.perform(get(UrlConstant.USERS))		
		.andExpect(status().isOk());
	}
	
	@Test
	public void postNewUser() throws Exception {
		UserDto user = new UserDto();
		user.setEmail("h@gmail.com");
		user.setUserName("fname");
		user.setMiddleName("m name");
		user.setLastName("l name");
		mockMvc.perform(post(UrlConstant.USERS).contentType(MediaType.APPLICATION_JSON).content(mapToJson(user)))		
		.andExpect(status().isCreated());
	}

	@Test
	public void getUserById1() throws Exception {
		mockMvc.perform(get(UrlConstant.USERS_BY_ID,1))
		.andDo(print())
		.andExpect(status().isOk());
	}
}
