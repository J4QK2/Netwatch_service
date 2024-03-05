package kz.iitu.watch.domain.history.service;

import kz.iitu.watch.domain.user.model.User;
import org.springframework.http.ResponseEntity;

public interface IHistoryService {

    ResponseEntity<?> findAllHistoryForUser(User user);

    ResponseEntity<?> findAllHistoryForLink(User user, Long id);

    ResponseEntity<?> deleteHistory(User user, Long id);

}
