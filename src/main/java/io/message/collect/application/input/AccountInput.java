package io.message.collect.application.input;

import io.message.collect.application.usecase.InquiryUseCase;
import io.message.collect.domain.interfaces.EntityAble;
import io.message.collect.domain.model.Account;
import io.message.collect.framework.database.AccountAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class AccountInput implements InquiryUseCase<Account> {

    private AccountAdapter accountAdapter;

    @Override
    @Transactional
    public Account save(EntityAble<Account> saveAble) {
        return accountAdapter.save(saveAble);
    }

}
