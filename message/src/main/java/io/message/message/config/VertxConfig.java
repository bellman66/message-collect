package io.message.message.config;

import io.message.message.adapter.web.controller.MainVerticle;
import io.vertx.core.Vertx;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VertxConfig {

    @PostConstruct
    public void deployVerticle() {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());
    }
}
