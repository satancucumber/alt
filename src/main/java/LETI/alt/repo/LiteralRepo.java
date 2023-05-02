package LETI.alt.repo;

import LETI.alt.models.Literal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiteralRepo extends JpaRepository<Literal, Long> {
}
