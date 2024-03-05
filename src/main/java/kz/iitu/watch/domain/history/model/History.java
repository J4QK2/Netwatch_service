package kz.iitu.watch.domain.history.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long linkId;
    private Long attackId;
    private Long dateTime;

    public History(Long userId, Long linkId, Long attackId, Long dateTime) {
        this.userId = userId;
        this.linkId = linkId;
        this.attackId = attackId;
        this.dateTime = dateTime;
    }
}
