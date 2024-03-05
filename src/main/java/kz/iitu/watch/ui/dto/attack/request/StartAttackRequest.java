package kz.iitu.watch.ui.dto.attack.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StartAttackRequest {

    private Long attackId;
    private Long linkId;

}
