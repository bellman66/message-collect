package io.message.collect.framework.database;

import io.message.collect.application.output.AccountOutput;
import io.message.collect.domain.interfaces.EntityAble;
import io.message.collect.domain.model.Account;
import io.message.collect.framework.database.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class AccountAdapter implements AccountOutput<Account> {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public Account save(EntityAble<Account> saveAble) {
        return accountRepository.save(saveAble.toEntity());
    }

}
