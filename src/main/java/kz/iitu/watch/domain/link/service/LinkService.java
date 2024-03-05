package kz.iitu.watch.domain.link.service;

import kz.iitu.watch.domain.link.model.Link;
import kz.iitu.watch.domain.link.repository.LinkRepository;
import kz.iitu.watch.domain.user.model.User;
import kz.iitu.watch.ui.dto.link.request.CreateLinkRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LinkService implements ILinkService {

    private final LinkRepository repository;

    @Override
    public ResponseEntity<?> createLink(User user, CreateLinkRequest createLinkRequest) {
        return ResponseEntity.ok(
            repository.save(
                new Link(
                    user.getId(),
                    System.currentTimeMillis(),
                    createLinkRequest.getName(),
                    createLinkRequest.getWebUrl(),
                    createLinkRequest.getImageUrl()
                )
            )
        );
    }

    @Override
    public ResponseEntity<?> findAllLinks(User user) {
        return ResponseEntity.ok(repository.findAllByUserId(user.getId()));
    }

    @Override
    public ResponseEntity<?> removeLink(User user, Long id) {
        Optional<Link> linkOptional = repository.findById(id);
        if (linkOptional.isPresent()) {
            Link link = linkOptional.get();
            if (Objects.equals(link.getUserId(), user.getId())) {
                repository.delete(link);
            }
        }
        return ResponseEntity.ok("Deleted");
    }
}
