package kz.iitu.watch.domain.confirmation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "confirmation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Confirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String code;
    private ConfirmationType type;

    public Confirmation(String email, String password, String code, ConfirmationType type) {
        this.email = email;
        this.password = password;
        this.code = code;
        this.type = type;
    }

    public Confirmation(String email, String code, ConfirmationType type) {
        this.email = email;
        this.password = password;
        this.code = code;
        this.type = type;
    }
}
