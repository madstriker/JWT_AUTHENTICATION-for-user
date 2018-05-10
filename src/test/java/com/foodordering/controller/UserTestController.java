package com.foodordering.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodordering.dto.UserDto;
import com.foodordering.model.User;
import com.foodordering.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.validation.constraints.NotNull;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Arrays;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;

@RunWith(SpringJUnit4ClassRunner.class)

public class UserTestController {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    UserDto userDto;

    User user;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController).build();

        userDto = new UserDto();
        userDto.setEmail("ram@yahoo.com");
        userDto.setAddress("bkt");
        userDto.setFirstName("ram");
        userDto.setMiddleName("bahadur");
        userDto.setLastName("thapa");
        userDto.setUserPassword("ram");
        userDto.setContactNo("981646176");

        user = new User();
        user.setUserId(userDto.getUserId());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setUserPassword(userDto.getUserPassword());
        user.setContactNo(userDto.getContactNo());
    }

    @Test
    public void testHelloWorld() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello World!"));
    }

    @Test
    public void testUserJson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/json").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is("ram")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.middleName", is("bahadur")));
    }

    @Test
    public void addUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(userDto);
        System.out.println("/////////////" + jsonString);
        BDDMockito.given(userService.addUser(userDto)).willReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/create")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        User returnedUser = mapper.readValue(outputInJson, User.class);
//        To valid below comment add assertij dependency
//        assertThat(returnedUser).isEqualTo(user);
        assertThat(returnedUser,is(user));
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    public void getUser() throws Exception{
        BDDMockito.given(userService.getUsers()).willReturn(Arrays.asList(new User()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/users")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson=response.getContentAsString();
//        assertThat(outputInJson, N).isNotEmpty();
        assertThat(outputInJson,is(notNullValue()));
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
