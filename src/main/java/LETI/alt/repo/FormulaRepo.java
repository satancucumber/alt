package LETI.alt.repo;

import LETI.alt.models.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaRepo extends JpaRepository<Formula, Long> {
}
