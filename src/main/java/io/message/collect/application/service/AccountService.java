package io.message.collect.application.service;

import io.message.collect.application.port.input.AccountSignUpUseCase;
import io.message.collect.application.port.output.AccountOutput;
import io.message.collect.domain.interfaces.EntityAble;
import io.message.collect.domain.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class AccountService implements AccountSignUpUseCase<Account> {

    private AccountOutput<Account> accountOutput;

    @Override
    @Transactional
    public Account save(EntityAble<Account> saveAble) {
        return accountOutput.save(saveAble);
    }

}
