package kz.iitu.watch.domain.link.service;

import kz.iitu.watch.domain.user.model.User;
import kz.iitu.watch.ui.dto.link.request.CreateLinkRequest;
import org.springframework.http.ResponseEntity;

public interface ILinkService {

    ResponseEntity<?> createLink(User user, CreateLinkRequest createLinkRequest);

    ResponseEntity<?> findAllLinks(User user);

    ResponseEntity<?> removeLink(User user, Long id);
}
