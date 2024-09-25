package io.message.message.adapter.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.message.message.adapter.web.data.request.MessageApiRequestGroup;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MessageVerticle extends AbstractVerticle {

    private ObjectMapper objectMapper;

    private MessageController messageController;

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.post("/message/publish")
                .handler(routingContext -> routingContext.response().end("Message published"));

        vertx.createHttpServer().requestHandler(req -> req.body()
                .map(buffer -> {
                    try {
                        return objectMapper.readValue(buffer.getBytes(), MessageApiRequestGroup.CreateApiRequest.class);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .onComplete(answer -> {
                    if (answer.succeeded()) {
                        req.response()
                                .putHeader("content-type", MediaType.APPLICATION_JSON_VALUE)
                                .end(messageController.publishMessage(answer.result()).toString());
                    } else {
                        req.response()
                                .setStatusCode(400)
                                .end();
                    }
                })).listen(58001);
    }
}
