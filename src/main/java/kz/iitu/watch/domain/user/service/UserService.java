package kz.iitu.watch.domain.user.service;

import kz.iitu.watch.domain.confirmation.model.Confirmation;
import kz.iitu.watch.domain.confirmation.model.ConfirmationType;
import kz.iitu.watch.domain.confirmation.repository.ConfirmationRepository;
import kz.iitu.watch.domain.user.model.User;
import kz.iitu.watch.domain.user.repository.UserRepository;
import kz.iitu.watch.infrastructure.authorization.TokenUtils;
import kz.iitu.watch.ui.dto.user.request.*;
import kz.iitu.watch.ui.dto.user.response.ProfileResponse;
import kz.iitu.watch.ui.dto.user.response.TokenResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository repository;

    private final ConfirmationRepository confirmationRepository;


    @Override
    public ResponseEntity<?> login(EmailPasswordRequest loginRequest) {
        Optional<User> userOptional = repository.findByEmail(loginRequest.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(loginRequest.getPassword())) {
                String token = TokenUtils.generateUserToken(loginRequest.getEmail(), loginRequest.getPassword());
                user.setToken(token);
                repository.save(user);
                return ResponseEntity.ok(new TokenResponse(token));
            } else {
                return ResponseEntity.badRequest().body("Wrong password");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not registered");
    }

    @Override
    public ResponseEntity<?> register(EmailPasswordRequest registerRequest) {
        Optional<User> userOptional = repository.findByEmail(registerRequest.getEmail());
        if (userOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        confirmationRepository.findByEmailAndType(
            registerRequest.getEmail(),
            ConfirmationType.REGISTER
        ).ifPresent(confirmationRepository::delete);
        String code = generateCode();

        // send sms

        confirmationRepository.save(
            new Confirmation(
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                code,
                ConfirmationType.REGISTER
            )
        );

        return ResponseEntity.ok("Sms sent");
    }

    @Override
    public ResponseEntity<?> getProfile(User user) {
        return ResponseEntity.ok(
            new ProfileResponse(
                user.getId(),
                user.getEmail()
            )
        );
    }

    @Override
    public ResponseEntity<?> changePassword(User user, ChangePasswordRequest changePasswordRequest) {
        if (!user.getPassword().equals(changePasswordRequest.getOldPassword())) {
            return ResponseEntity.badRequest().body("Wrong old password");
        }
        user.setPassword(changePasswordRequest.getNewPassword());
        user.setToken(TokenUtils.generateUserToken(user.getEmail(), user.getPassword()));
        repository.save(user);
        return ResponseEntity.ok(new TokenResponse(user.getToken()));
    }

    @Override
    public ResponseEntity<?> setNewPassword(ResetPasswordRequest resetPasswordRequest) {
        Optional<Confirmation> confirmationOptional = confirmationRepository.findByEmailAndType(resetPasswordRequest.getEmail(), ConfirmationType.RESET);
        if (confirmationOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Confirmation not found!");
        }
        Confirmation confirmation = confirmationOptional.get();
        if (!confirmation.getCode().equals(resetPasswordRequest.getCode())) {
            return ResponseEntity.badRequest().body("Wrong code!");
        }
        confirmationRepository.delete(confirmation);
        Optional<User> userOptional = repository.findByEmail(resetPasswordRequest.getEmail());
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found!");
        }
        User user = userOptional.get();
        user.setPassword(resetPasswordRequest.getPassword());
        user.setToken(TokenUtils.generateUserToken(user.getEmail(), user.getPassword()));
        repository.save(user);
        return ResponseEntity.ok(new TokenResponse(user.getToken()));
    }

    @Override
    public ResponseEntity<?> resetPassword(StringBody stringBody) {
        Optional<User> userOptional = repository.findByEmail(stringBody.getBody());
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not registered");
        }
        confirmationRepository.findByEmailAndType(
            stringBody.getBody(),
            ConfirmationType.RESET
        ).ifPresent(confirmationRepository::delete);
        String code = generateCode();
        confirmationRepository.save(new Confirmation(stringBody.getBody(), code, ConfirmationType.RESET));

        // send sms

        return ResponseEntity.ok("Sms sent!");
    }

    @Override
    public ResponseEntity<?> confirmRegister(ConfirmationRequest confirmationRequest) {
        Optional<Confirmation> confirmationOptional = confirmationRepository.findByEmailAndType(confirmationRequest.getEmail(), ConfirmationType.REGISTER);
        if (confirmationOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Confirmation not found");
        }
        Confirmation confirmation = confirmationOptional.get();
        if (!confirmation.getCode().equals(confirmationRequest.getCode())) {
            return ResponseEntity.badRequest().body("Incorrect code");
        }
        User user = repository.save(
            new User(
                confirmation.getEmail(),
                confirmation.getPassword(),
                TokenUtils.generateUserToken(confirmation.getEmail(), confirmation.getPassword())
            )
        );
        confirmationRepository.delete(confirmation);
        return ResponseEntity.ok(new TokenResponse(user.getToken()));
    }

    private String generateCode() {
        Random random = new Random();
        int number = random.nextInt(1000000);
        return String.format("%06d", number);
    }

}
