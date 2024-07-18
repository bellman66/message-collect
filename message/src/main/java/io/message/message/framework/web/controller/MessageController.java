package io.message.message.framework.web.controller;

import io.message.message.application.port.input.SignalReadUseCase;
import io.message.message.application.port.output.MessageOutput;
import io.message.message.domain.mapper.MessageMapper;
import io.message.message.domain.message.SignalMessage;
import io.message.message.domain.search.SignalSearch;
import io.message.message.framework.web.data.request.MessageApiRequestGroup;
import lombok.AllArgsConstructor;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

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

    @PostMapping("/search")
    public ResponseEntity<String> searchGroupByQuery(@RequestBody MessageApiRequestGroup.SearchApiRequest request) {
        return ResponseEntity.ok(signalSignalReadUseCase.searchGroupByQuery(new CriteriaQuery(new Criteria("content").matches(request.content()))).toString());
    }

}
