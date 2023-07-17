package com.java.springbootfizzbuzz.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.java.springbootfizzbuzz.api.service.FizzBuzzService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class FizzBuzzIntegrationTest {

    @Autowired
    private FizzBuzzService fizzBuzzService;

    @Test
    public void shouldGetListByValidLimit() {
        int limit = 15;
        //Get json from fizz buzz method
        List<String> result = fizzBuzzService.getFizzBuzz(limit);

        //Check if not null
        assertNotNull(result);
        //Check the json size is the same as the limit parameter
        assertEquals(limit, result.size());

        //Check if the values in the json are correct
        assertEquals("1", result.get(0));
        assertEquals("2", result.get(1));
        assertEquals("Fizz", result.get(2));
        assertEquals("4", result.get(3));
        assertEquals("Buzz", result.get(4));
        assertEquals("Fizz", result.get(5));
        assertEquals("7", result.get(6));
        assertEquals("8", result.get(7));
        assertEquals("Fizz", result.get(8));
        assertEquals("Buzz", result.get(9));
        assertEquals("11", result.get(10));
        assertEquals("Fizz", result.get(11));
        assertEquals("13", result.get(12));
        assertEquals("14", result.get(13));
        assertEquals("FizzBuzz", result.get(14));
    }

    @Test
    public void shouldNotGetJsonListByZeroLimit() {
        //Parameter set with zero and check if an error message exists and the message is correct
        assertThatThrownBy(() -> fizzBuzzService.getFizzBuzz(0)).hasMessage("Limit must be greater than 0");
    }

    @Test
    public void shouldNotGetJsonListByNegativeLimit() {
        //Parameter set with negative int and check if an error message exists and the message is correct
        assertThatThrownBy(() -> fizzBuzzService.getFizzBuzz(-10)).hasMessage("Limit must be greater than 0");
    }
}
