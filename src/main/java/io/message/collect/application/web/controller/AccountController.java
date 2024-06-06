package io.message.collect.application.web.controller;

import io.message.collect.application.web.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collect")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/publish")
    public ResponseEntity<?> save(HttpServletRequest request) {
        accountService.savePerson();


        return ResponseEntity.ok().build();
    }

}
