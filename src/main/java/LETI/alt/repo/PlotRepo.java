package LETI.alt.repo;

import LETI.alt.models.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlotRepo extends JpaRepository<Plot, Long> {
}
