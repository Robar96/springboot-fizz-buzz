package com.java.springbootfizzbuzz.resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FizzBuzzResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetJsonListByValidLimit() throws Exception {

        //Use fizz buzz endpoint with a valid param
        mockMvc.perform(MockMvcRequestBuilders.get("/fizzBuzz")
                        .param("limit", "15"))
                .andExpect(status().isOk()) // Check status is 200(ok)

                //Check the json size is the same as the limit parameter and that the values are correct
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", hasSize(15)))
                .andExpect(jsonPath("$[0]", is("1")))
                .andExpect(jsonPath("$[1]", is("2")))
                .andExpect(jsonPath("$[2]", is("Fizz")))
                .andExpect(jsonPath("$[3]", is("4")))
                .andExpect(jsonPath("$[4]", is("Buzz")))
                .andExpect(jsonPath("$[5]", is("Fizz")))
                .andExpect(jsonPath("$[6]", is("7")))
                .andExpect(jsonPath("$[7]", is("8")))
                .andExpect(jsonPath("$[8]", is("Fizz")))
                .andExpect(jsonPath("$[9]", is("Buzz")))
                .andExpect(jsonPath("$[10]", is("11")))
                .andExpect(jsonPath("$[11]", is("Fizz")))
                .andExpect(jsonPath("$[12]", is("13")))
                .andExpect(jsonPath("$[13]", is("14")))
                .andExpect(jsonPath("$[14]", is("FizzBuzz")));
    }

    @Test
    public void shouldNotGetJsonListByZeroLimit() throws Exception {
        //Use fizz buzz endpoint with a zero int param
        mockMvc.perform(MockMvcRequestBuilders.get("/fizzBuzz")
                        .param("limit", "0"))
                .andExpect(status().isBadRequest()) // Check status is 400(bad)
                .andExpect(jsonPath("$[0]", is("Limit must be greater than 0")));
    }

    @Test
    public void shouldNotGetJsonListByNegativeLimit() throws Exception {
        //Use fizz buzz endpoint with a negative int param
        mockMvc.perform(MockMvcRequestBuilders.get("/fizzBuzz")
                        .param("limit", "-10"))
                .andExpect(status().isBadRequest()) // Check status is 400(bad)
                .andExpect(jsonPath("$[0]", is("Limit must be greater than 0")));
    }

    @Test
    public void shouldNotGetJsonListByInvalidValue() throws Exception {
        //Use fizz buzz endpoint with string param (invalid)
        mockMvc.perform(MockMvcRequestBuilders.get("/fizzBuzz")
                        .param("limit", "abc"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) result.getResolvedException();
                    assertEquals("limit", exception.getName());
                    assertEquals("Failed to convert value of type 'java.lang.String' to required type 'int'; For input string: \"abc\"", exception.getMessage());
                });
    }
}