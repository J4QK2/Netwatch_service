package kz.iitu.watch.ui.controller;

import kz.iitu.watch.domain.user.service.UserService;
import kz.iitu.watch.ui.dto.user.request.EmailPasswordRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authorization")
@AllArgsConstructor
public class AuthorizationController {

    private final UserService service;

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody EmailPasswordRequest emailPasswordRequest) throws Exception {
        return service.login(emailPasswordRequest);
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody EmailPasswordRequest emailPasswordRequest) throws Exception {
        return service.register(emailPasswordRequest);
    }

}
