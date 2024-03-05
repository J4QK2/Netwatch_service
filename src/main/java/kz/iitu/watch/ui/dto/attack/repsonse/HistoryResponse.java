package kz.iitu.watch.ui.dto.attack.repsonse;

import kz.iitu.watch.domain.attack.model.Attack;
import kz.iitu.watch.domain.link.model.Link;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoryResponse {

    private Long id;
    private Long dateTime;
    private Link link;
    private Attack attack;

}
