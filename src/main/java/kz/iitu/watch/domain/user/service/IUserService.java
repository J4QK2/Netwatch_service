package kz.iitu.watch.domain.user.service;

import kz.iitu.watch.domain.user.model.User;
import kz.iitu.watch.ui.dto.user.request.*;
import org.springframework.http.ResponseEntity;

public interface IUserService {

    ResponseEntity<?> login(EmailPasswordRequest loginRequest);

    ResponseEntity<?> register(EmailPasswordRequest registerRequest);

    ResponseEntity<?> getProfile(User user);

    ResponseEntity<?> changePassword(User user, ChangePasswordRequest changePasswordRequest);

    ResponseEntity<?> setNewPassword(ResetPasswordRequest resetPasswordRequest);

    ResponseEntity<?> resetPassword(StringBody stringBody);

    ResponseEntity<?> confirmRegister(ConfirmationRequest confirmationRequest);

}
