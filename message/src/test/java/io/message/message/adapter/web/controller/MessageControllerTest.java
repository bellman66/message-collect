package io.message.message.adapter.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.web.reactive.server.WebTestClient;

@DisabledInAotMode
@SpringBootTest
class MessageControllerTest {

    private WebTestClient webTestClient;

    @Autowired
    private MessageController messageController;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(messageController).build();
    }

    @Test
    void publishMessage() {
    }

    @Test
    void searchGroupByQuery() {
    }
}
