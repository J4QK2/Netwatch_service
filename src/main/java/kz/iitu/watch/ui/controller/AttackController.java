package kz.iitu.watch.ui.controller;

import kz.iitu.watch.domain.attack.service.AttackService;
import kz.iitu.watch.infrastructure.authorization.AuthorizationStructure;
import kz.iitu.watch.ui.dto.attack.request.StartAttackRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static kz.iitu.watch.infrastructure.authorization.AuthorizationStructure.AUTHORIZATION_HEADER;

@RestController
@RequestMapping("/api/attack")
@AllArgsConstructor
public class AttackController {

    private final AttackService service;

    private final AuthorizationStructure authorization;

    @GetMapping("/all")
    ResponseEntity<?> findAllAttacks(
        @RequestHeader(AUTHORIZATION_HEADER) String token
    ) throws Exception {
        return service.findAllAttacks();
    }

    @PostMapping("/start")
    ResponseEntity<?> startAttack(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @RequestBody StartAttackRequest startAttackRequest
    ) throws Exception {
        return authorization.checkUser(token, user -> service.startAttack(user, startAttackRequest));
    }

}
