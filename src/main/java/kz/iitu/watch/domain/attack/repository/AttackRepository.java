package kz.iitu.watch.domain.attack.repository;

import kz.iitu.watch.domain.attack.model.Attack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttackRepository extends CrudRepository<Attack, Long> {



}
