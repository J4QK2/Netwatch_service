package kz.iitu.watch.ui.controller;

import kz.iitu.watch.domain.link.service.LinkService;
import kz.iitu.watch.domain.user.model.User;
import kz.iitu.watch.infrastructure.authorization.AuthorizationStructure;
import kz.iitu.watch.ui.dto.link.request.CreateLinkRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static kz.iitu.watch.infrastructure.authorization.AuthorizationStructure.AUTHORIZATION_HEADER;

@RestController
@RequestMapping("/api/link")
@AllArgsConstructor
public class LinkController {

    private final LinkService service;

    private final AuthorizationStructure authorization;

    @GetMapping
    ResponseEntity<?> findAllLinks(@RequestHeader(AUTHORIZATION_HEADER) String token) throws Exception {
        return authorization.checkUser(token, service::findAllLinks);
    }

    @PostMapping
    ResponseEntity<?> addLink(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @RequestBody CreateLinkRequest createLinkRequest
    ) throws Exception {
        return authorization.checkUser(token, user -> service.createLink(user, createLinkRequest));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteLink(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @PathVariable Long id
    ) throws Exception {
        return authorization.checkUser(token, user -> service.removeLink(user, id));
    }
}
