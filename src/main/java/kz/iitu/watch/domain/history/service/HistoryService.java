package kz.iitu.watch.domain.history.service;

import kz.iitu.watch.domain.history.model.History;
import kz.iitu.watch.domain.history.repository.HistoryRepository;
import kz.iitu.watch.domain.link.model.Link;
import kz.iitu.watch.domain.link.repository.LinkRepository;
import kz.iitu.watch.domain.user.model.User;
import kz.iitu.watch.ui.dto.attack.mapper.HistoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HistoryService implements IHistoryService {

    private final HistoryRepository repository;

    private final LinkRepository linkRepository;

    private final HistoryMapper mapper;

    @Override
    public ResponseEntity<?> findAllHistoryForUser(User user) {
        return ResponseEntity.ok(mapper.parse(repository.findAllByUserId(user.getId())));
    }

    @Override
    public ResponseEntity<?> findAllHistoryForLink(User user, Long id) {
        Optional<Link> linkOptional = linkRepository.findById(id);
        if (linkOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Link not found");
        }
        Link link = linkOptional.get();
        if (!Objects.equals(link.getUserId(), user.getId())) {
            return ResponseEntity.badRequest().body("Link not found");
        }
        return ResponseEntity.ok(mapper.parse(repository.findAllByLinkId(link.getId())));
    }

    @Override
    public ResponseEntity<?> deleteHistory(User user, Long id) {
        Optional<History> historyOptional = repository.findById(id);
        if (historyOptional.isPresent()) {
            History history = historyOptional.get();
            if (Objects.equals(history.getUserId(), user.getId())) {
                repository.delete(history);
            }
        }
        return ResponseEntity.ok("Deleted");
    }
}
