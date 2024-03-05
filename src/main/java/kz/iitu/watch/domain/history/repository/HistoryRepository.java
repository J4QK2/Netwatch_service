package kz.iitu.watch.domain.history.repository;

import kz.iitu.watch.domain.history.model.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends CrudRepository<History, Long> {

    List<History> findAllByUserId(Long userId);

    List<History> findAllByLinkId(Long linkId);

}
