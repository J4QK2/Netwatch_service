package kz.iitu.watch.infrastructure.authorization;

import kz.iitu.watch.domain.user.model.User;
import org.springframework.http.ResponseEntity;

public interface UserAllowedCallBack {

    ResponseEntity<?> allowed(User user);

}