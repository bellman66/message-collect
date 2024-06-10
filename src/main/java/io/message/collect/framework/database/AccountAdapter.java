package io.message.collect.framework.database;

import io.message.collect.application.output.AccountOutput;
import io.message.collect.domain.interfaces.SaveAble;
import io.message.collect.domain.model.Account;
import io.message.collect.framework.database.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
@Transactional(readOnly = true)
public class AccountAdapter implements AccountOutput<Account> {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public Account save(SaveAble<Account> saveAble) {
        return accountRepository.save(saveAble.toEntity());
    }

}
