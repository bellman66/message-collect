package io.message.account.framework.database;

import io.message.account.application.port.output.AccountOutput;
import io.message.account.domain.interfaces.EntityAble;
import io.message.account.domain.model.Account;
import io.message.account.framework.database.jpa.AccountRepository;
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
