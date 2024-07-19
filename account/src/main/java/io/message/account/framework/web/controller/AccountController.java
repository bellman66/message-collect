package io.message.account.framework.web.controller;

import io.message.account.application.port.input.AccountSignUpUseCase;
import io.message.account.domain.model.Account;
import io.message.account.framework.web.data.request.AccountApiRequestGroup;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountSignUpUseCase<Account> accountSignUpUseCase;

    @PostMapping("/sign-up")
    public ResponseEntity<Account> create(@RequestBody AccountApiRequestGroup.CreateApiRequest request) {
        Account account = accountSignUpUseCase.save(() -> new Account(UUID.randomUUID().toString(), request.name(), request.age()));
        return ResponseEntity.ok(account);
    }

}
