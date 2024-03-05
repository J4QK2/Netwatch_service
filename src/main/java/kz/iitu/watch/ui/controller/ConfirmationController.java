package kz.iitu.watch.ui.controller;

import kz.iitu.watch.domain.user.service.UserService;
import kz.iitu.watch.ui.dto.user.request.ConfirmationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/confirmation")
@AllArgsConstructor
public class ConfirmationController {

    private final UserService service;

    @PostMapping("/register")
    ResponseEntity<?> confirmRegister(@RequestBody ConfirmationRequest confirmationRequest) throws Exception {
        return service.confirmRegister(confirmationRequest);
    }

}
