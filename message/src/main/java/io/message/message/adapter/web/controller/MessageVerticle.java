package io.message.message.adapter.web.controller;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class MessageVerticle extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.post("/message/publish")
                .handler(routingContext -> routingContext.response().end("Message published"));

        vertx.createHttpServer().requestHandler(req -> {
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x!");
        }).listen(58001);
    }
}
