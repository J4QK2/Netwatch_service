package kz.iitu.watch.ui.controller;

import kz.iitu.watch.domain.history.service.HistoryService;
import kz.iitu.watch.infrastructure.authorization.AuthorizationStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static kz.iitu.watch.infrastructure.authorization.AuthorizationStructure.AUTHORIZATION_HEADER;

@RestController
@RequestMapping("/api/history")
@AllArgsConstructor
public class HistoryController {

    private final HistoryService service;

    private final AuthorizationStructure authorization;

    @GetMapping("/all")
    ResponseEntity<?> findAllForUser(
        @RequestHeader(AUTHORIZATION_HEADER) String token
    ) throws Exception {
        return authorization.checkUser(token, service::findAllHistoryForUser);
    }

    @GetMapping("/all/{id}")
    ResponseEntity<?> findAllForLink(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @PathVariable Long id
    ) throws Exception {
        return authorization.checkUser(token, user -> service.findAllHistoryForLink(user, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteHistory(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @PathVariable Long id
    ) throws Exception {
        return authorization.checkUser(token, user -> service.deleteHistory(user, id));
    }

}
