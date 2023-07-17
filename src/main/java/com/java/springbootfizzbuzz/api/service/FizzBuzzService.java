package com.java.springbootfizzbuzz.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.java.springbootfizzbuzz.api.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FizzBuzzService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    public List<String> getFizzBuzz(int limit) throws ServiceException {

        //Validate if limit is greater than zero
        if (limit <= 0) {
            throw new ServiceException("Limit must be greater than 0");
        }

        List<String> result = new ArrayList<>();

        // Iterate from 1 to the given limit and generate the FizzBuzz sequence
        for (int i = 1; i <= limit; i++) {
            // Check if the number is divisible by 3 and 5, and add "FizzBuzz" to the result
            if (i % 3 == 0 && i % 5 == 0) {
                result.add("FizzBuzz");
            }
            // Check if the number is divisible by 3, and add "Fizz" to the result
            else if (i % 3 == 0) {
                result.add("Fizz");
            }
            // Check if the number is divisible by 5, and add "Buzz" to the result
            else if (i % 5 == 0) {
                result.add("Buzz");
            }
            // If the number is not divisible by either 3 or 5, add the number itself as a string to the result
            else {
                result.add(Integer.toString(i));
            }
        }

        return result;
    }
}
