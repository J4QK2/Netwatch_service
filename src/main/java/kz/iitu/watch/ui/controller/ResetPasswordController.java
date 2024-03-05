package kz.iitu.watch.ui.controller;

import kz.iitu.watch.domain.user.service.UserService;
import kz.iitu.watch.ui.dto.user.request.ResetPasswordRequest;
import kz.iitu.watch.ui.dto.user.request.StringBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reset")
@AllArgsConstructor
public class ResetPasswordController {

    private final UserService service;

    @PostMapping
    ResponseEntity<?> resetRequest(@RequestBody StringBody stringBody) throws Exception {
        return service.resetPassword(stringBody);
    }

    @PostMapping("/set")
    ResponseEntity<?> setNewPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) throws Exception {
        return service.setNewPassword(resetPasswordRequest);
    }

}
