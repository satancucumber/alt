package LETI.alt.repo;

import LETI.alt.models.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvidenceRepo extends JpaRepository<Evidence, Long> {
}
