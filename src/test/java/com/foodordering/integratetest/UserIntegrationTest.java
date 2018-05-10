package com.foodordering.integratetest;

import com.foodordering.dao.UserDAO;
import com.foodordering.dto.UserDto;
import com.foodordering.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserIntegrationTest {

    private static final String fooResourceUrl="http://localhost:8080/user";

    @Test
    public void testRestTemplate() {

        RestTemplate testRestTemplate = new RestTemplate();
        ResponseEntity<User[]> response
                = testRestTemplate.getForEntity(fooResourceUrl+"/users", User[].class);
        for (User user : response.getBody()) {
            System.out.println(user.getFirstName());
        }
       assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
    }

    @Test
    public void testToAddRestTemplate() {

        RestTemplate testRestTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/user";
        User user = testRestTemplate
                .getForObject(fooResourceUrl + "/76", User.class);
        assertThat(user.getFirstName(),notNullValue());
        assertThat(user, is(notNullValue()));

    }

    @Test
    public void testToRetrieveHeader() {
        RestTemplate testRestTemplate = new RestTemplate();
        HttpHeaders httpHeaders=testRestTemplate.headForHeaders(fooResourceUrl+"/users");
        System.out.println("Headers----------->"+httpHeaders);
      /*  User user = testRestTemplate
                .getForObject(fooResourceUrl + "/76", User.class);*/
        Assert.assertTrue(httpHeaders.getContentType()
                .includes(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testToAddUserByRestTemplate() {
        TestRestTemplate restTemplate=new TestRestTemplate();
        HttpEntity<UserDto> request=new HttpEntity<>(new UserDto(1,"ram","ram@yahoo.com",
                "ktm","ram","kumar","saha","98139813"));
        User user=restTemplate.postForObject(fooResourceUrl+"/create",request,User.class);
        assertThat(user, notNullValue());
    }

    @Test
    public void testToPostForLocationByRestTemplate() {
        RestTemplate restTemplate=new RestTemplate();
        HttpEntity<UserDto> request=new HttpEntity<>(new UserDto(1,"ram","raj@yahoo.com",
                "ktm","ram","kumar","saha","98139813"));
        URI location=restTemplate.postForLocation(fooResourceUrl+"/create",request);
//        System.out.println("Location------------>"+location.toString());
        assertThat(location,notNullValue());
    }

    @Test
    public void testToExchangeAPIByRestTemplate() {
        RestTemplate restTemplate=new RestTemplate();
        HttpEntity<UserDto> request=new HttpEntity<UserDto>(new UserDto(1,"ram","raj@yahoo.com",
                "ktm","ram","kumar","saha","98139813"));
        ResponseEntity<User> response = restTemplate
                .exchange(fooResourceUrl+"/create", HttpMethod.POST, request, User.class);
        assertThat(response.getStatusCode(),is(HttpStatus.OK));
        UserDto userDto=request.getBody();
        assertThat(userDto,notNullValue());
        assertThat(userDto.getFirstName(),is("ram"));


    }
}
