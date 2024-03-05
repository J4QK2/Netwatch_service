package kz.iitu.watch.domain.attack.service;

import kz.iitu.watch.domain.attack.repository.AttackRepository;
import kz.iitu.watch.domain.history.model.History;
import kz.iitu.watch.domain.history.repository.HistoryRepository;
import kz.iitu.watch.domain.link.model.Link;
import kz.iitu.watch.domain.link.repository.LinkRepository;
import kz.iitu.watch.domain.user.model.User;
import kz.iitu.watch.ui.dto.attack.request.StartAttackRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AttackService implements IAttackService {

    private final AttackRepository repository;

    private final LinkRepository linkRepository;

    private final HistoryRepository historyRepository;

    @Override
    public ResponseEntity<?> findAllAttacks() {
        return ResponseEntity.ok(repository.findAll());
    }

    @Override
    public ResponseEntity<?> startAttack(User user, StartAttackRequest startAttackRequest) {
        Optional<Link> linkOptional = linkRepository.findById(startAttackRequest.getLinkId());
        if (linkOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Link not found");
        }
        Link link = linkOptional.get();
        if (!Objects.equals(link.getUserId(), user.getId())) {
            return ResponseEntity.badRequest().body("Link not found");
        }
        historyRepository.save(new History(user.getId(), startAttackRequest.getLinkId(), startAttackRequest.getAttackId(), System.currentTimeMillis()));
        return ResponseEntity.ok("Started");
    }
}
