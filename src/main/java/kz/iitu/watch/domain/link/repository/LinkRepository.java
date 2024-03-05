package kz.iitu.watch.domain.link.repository;

import kz.iitu.watch.domain.link.model.Link;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends CrudRepository<Link, Long> {

    List<Link> findAllByUserId(Long userId);

}
