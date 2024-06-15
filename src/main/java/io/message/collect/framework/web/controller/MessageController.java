package io.message.collect.framework.web.controller;

import io.message.collect.application.output.MessageOutput;
import io.message.collect.domain.model.Message;
import io.message.collect.framework.web.data.request.MessageApiRequestGroup;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageOutput messageOutput;

    @PostMapping("/publish")
    public ResponseEntity<String> publishMessage(@RequestBody MessageApiRequestGroup.CreateApiRequest request) throws ExecutionException, InterruptedException {
        Message message = Message.create(request.content());
        return ResponseEntity.ok(messageOutput.save(() -> message));
    }

}
