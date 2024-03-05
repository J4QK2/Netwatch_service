package kz.iitu.watch.infrastructure.authorization;

import kz.iitu.watch.domain.user.model.User;
import kz.iitu.watch.domain.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorizationStructure {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final UserRepository userRepository;

    public AuthorizationStructure(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> checkUser(String token, UserAllowedCallBack userAllowedCallBack) {
        Optional<User> userOptional = userRepository.findByToken(token);
        if (userOptional.isPresent()) {
            return userAllowedCallBack.allowed(userOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong JWT token");
        }
    }

}