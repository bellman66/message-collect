package io.message.collect.framework.web.controller;

import io.message.collect.application.input.AccountInput;
import io.message.collect.domain.model.Account;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/collect")
public class AccountController {

    private final AccountInput accountInput;

    @PostMapping("/publish")
    public ResponseEntity<?> save(HttpServletRequest request) {
        accountInput.save(() -> new Account(UUID.randomUUID().toString(), request.getParameter("name"), Integer.parseInt(request.getParameter("age"))));
        
        return ResponseEntity.ok().build();
    }

}
