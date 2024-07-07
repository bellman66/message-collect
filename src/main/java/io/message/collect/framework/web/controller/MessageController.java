package io.message.collect.framework.web.controller;

import io.message.collect.application.port.input.SignalReadUseCase;
import io.message.collect.application.port.output.MessageOutput;
import io.message.collect.domain.mapper.MessageMapper;
import io.message.collect.domain.message.SignalMessage;
import io.message.collect.domain.search.SignalSearch;
import io.message.collect.framework.web.data.request.MessageApiRequestGroup;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageOutput messageOutput;

    private final SignalReadUseCase<SignalSearch> signalSignalReadUseCase;

    @PostMapping("/publish")
    public ResponseEntity<String> publishMessage(@RequestBody MessageApiRequestGroup.CreateApiRequest request) throws ExecutionException, InterruptedException {
        SignalMessage signalMessage = MessageMapper.INSTANCE.toMessage(request);
        return ResponseEntity.ok(messageOutput.save(signalMessage));
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchMessage(@RequestParam String id) {
        return ResponseEntity.ok(signalSignalReadUseCase.searchById(id).toString());
    }

}
