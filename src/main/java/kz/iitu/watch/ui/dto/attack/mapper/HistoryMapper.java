package kz.iitu.watch.ui.dto.attack.mapper;

import kz.iitu.watch.domain.attack.model.Attack;
import kz.iitu.watch.domain.attack.repository.AttackRepository;
import kz.iitu.watch.domain.history.model.History;
import kz.iitu.watch.domain.link.model.Link;
import kz.iitu.watch.domain.link.repository.LinkRepository;
import kz.iitu.watch.ui.dto.attack.repsonse.HistoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class HistoryMapper {

    private final LinkRepository linkRepository;

    private final AttackRepository attackRepository;

    public HistoryResponse parse(History history) {
        Optional<Attack> attackOptional = attackRepository.findById(history.getAttackId());
        if (attackOptional.isPresent()) {
            Optional<Link> linkOptional = linkRepository.findById(history.getLinkId());
            return linkOptional.map(link -> new HistoryResponse(
                    history.getId(),
                    history.getDateTime(),
                    link,
                    attackOptional.get()
            )).orElse(null);
        }
        return null;
    }

    public List<HistoryResponse> parse(List<History> histories) {
        List<HistoryResponse> response = new ArrayList<>();
        for (History history : histories) {
            HistoryResponse historyResponse = parse(history);
            if (historyResponse != null) {
                response.add(historyResponse);
            }
        }
        return response;
    }

}
