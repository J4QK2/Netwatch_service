package kz.iitu.watch.domain.confirmation.repository;

import kz.iitu.watch.domain.confirmation.model.Confirmation;
import kz.iitu.watch.domain.confirmation.model.ConfirmationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationRepository extends CrudRepository<Confirmation, Long> {

    Optional<Confirmation> findByEmailAndType(String email, ConfirmationType type);

}
