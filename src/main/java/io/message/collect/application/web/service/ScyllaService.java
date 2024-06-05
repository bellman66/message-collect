package io.message.collect.application.web.service;

import io.message.collect.application.data.Person;
import io.message.collect.application.web.repository.ScyllaRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ScyllaService {

    private ScyllaRepository scyllaRepository;

    @PostConstruct
    public void init() {
        savePerson();
    }

    @Transactional
    public void savePerson() {
        scyllaRepository.save(new Person("1", "John", 30));
    }
}
