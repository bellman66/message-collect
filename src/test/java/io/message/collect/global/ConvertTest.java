package io.message.collect.global;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.message.collect.domain.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConvertTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
    }

    @Test
    public void test() {
        // Given
        String json = "{\"name\":\"John\"}";

        // When
        Account result = objectMapper.convertValue(json, Account.class);

        // Then
        System.out.println(result);
    }

}
