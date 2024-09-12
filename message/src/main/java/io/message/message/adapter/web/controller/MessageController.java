package io.message.message.adapter.web.controller;

import io.message.message.MessageTopic;
import io.message.message.adapter.web.data.request.MessageApiRequestGroup;
import io.message.message.application.port.input.SignalReadUseCase;
import io.message.message.application.port.output.MessageOutput;
import io.message.message.domain.mapper.MessageMapper;
import io.message.message.domain.message.SignalMessage;
import io.message.message.domain.search.SignalSearch;
import lombok.AllArgsConstructor;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageOutput<SignalMessage> messageOutput;

    private final SignalReadUseCase<SignalSearch> signalReadUseCase;

    @PostMapping("/publish")
    public Mono<ResponseEntity<String>> publishMessage(
            @RequestBody MessageApiRequestGroup.CreateApiRequest request) {
        SignalMessage signalMessage = MessageMapper.INSTANCE.toMessage(request);

        return messageOutput
                .save(MessageTopic.PUBLISH, signalMessage)
                .map(message -> ResponseEntity.ok(message.getId()));
    }

    @PostMapping("/search")
    public Mono<ResponseEntity<List<SearchHit<SignalSearch>>>> searchGroupByQuery(
            @RequestBody MessageApiRequestGroup.SearchApiRequest request) {
        CriteriaQuery query = new CriteriaQuery(Criteria.where("content").is(request.content()));

        return signalReadUseCase
                .searchGroupByQuery(query)
                .collectList()
                .map(ResponseEntity::ok);
    }
}
