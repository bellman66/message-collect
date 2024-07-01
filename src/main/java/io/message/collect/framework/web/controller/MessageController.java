package io.message.collect.framework.web.controller;

import io.message.collect.application.output.MessageOutput;
import io.message.collect.application.usecase.SignalReadUseCase;
import io.message.collect.domain.mapper.MessageMapper;
import io.message.collect.domain.message.SignalMessage;
import io.message.collect.domain.model.MechanicalSignal;
import io.message.collect.framework.web.data.request.MessageApiRequestGroup;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageOutput messageOutput;

    private final MessageMapper messageMapper;

    private final SignalReadUseCase<MechanicalSignal> signalSignalReadUseCase;

    @PostMapping("/publish")
    public ResponseEntity<String> publishMessage(@RequestBody MessageApiRequestGroup.CreateApiRequest request) throws ExecutionException, InterruptedException {
        SignalMessage signalMessage = messageMapper.toEntity(request);
        return ResponseEntity.ok(messageOutput.save(signalMessage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getMessage(@PathVariable String id) {
        return ResponseEntity.ok(signalSignalReadUseCase.findById(id).toString());
    }

}
