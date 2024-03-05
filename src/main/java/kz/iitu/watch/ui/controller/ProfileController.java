package kz.iitu.watch.ui.controller;

import kz.iitu.watch.domain.user.service.UserService;
import kz.iitu.watch.infrastructure.authorization.AuthorizationStructure;
import kz.iitu.watch.ui.dto.user.request.ChangePasswordRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static kz.iitu.watch.infrastructure.authorization.AuthorizationStructure.AUTHORIZATION_HEADER;

@RestController
@RequestMapping("/api/profile")
@AllArgsConstructor
public class ProfileController {

    private final UserService service;

    private final AuthorizationStructure authorization;

    @GetMapping
    ResponseEntity<?> getProfile(@RequestHeader(AUTHORIZATION_HEADER) String token) throws Exception {
        return authorization.checkUser(token, service::getProfile);
    }

    @PostMapping("/password")
    ResponseEntity<?> changePassword(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @RequestBody ChangePasswordRequest changePasswordRequest
    ) throws Exception {
        return authorization.checkUser(token, user -> service.changePassword(user, changePasswordRequest));
    }

}
