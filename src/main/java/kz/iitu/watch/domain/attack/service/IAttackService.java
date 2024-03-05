package kz.iitu.watch.domain.attack.service;

import kz.iitu.watch.domain.user.model.User;
import kz.iitu.watch.ui.dto.attack.request.StartAttackRequest;
import org.springframework.http.ResponseEntity;

public interface IAttackService {

    ResponseEntity<?> findAllAttacks();

    ResponseEntity<?> startAttack(User user, StartAttackRequest startAttackRequest);

}
