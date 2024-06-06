package io.message.collect.application.web.service;

import io.message.collect.application.data.entity.Account;
import io.message.collect.application.web.repository.AccountRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private AccountRepository template;

    @Transactional
    public void savePerson() {
        template.insert(new Account(UUID.randomUUID().toString(), "John", 30));
    }

}
