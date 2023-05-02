package LETI.alt.controller;

import LETI.alt.models.Evidence;
import LETI.alt.repo.EvidenceRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/evidence")
public class EvidenceController {
    private final EvidenceRepo evidenceRepo;
    @Autowired
    public EvidenceController(EvidenceRepo evidenceRepo) {
        this.evidenceRepo = evidenceRepo;
    }

    @GetMapping
    public List<Evidence> list() {
        return evidenceRepo.findAll();
    }
    @GetMapping("{id}")
    public Evidence getOne(@PathVariable("id") Evidence evidence) {
        return evidence;
    }
    @PostMapping
    public Evidence create(@RequestBody Evidence evidence) {
        return evidenceRepo.save(evidence);
    }
    @PutMapping("{id}")
    public Evidence update(
            @PathVariable("id") Evidence evidenceFromDb,
            @RequestBody Evidence evidence
    ) {
        BeanUtils.copyProperties(evidence, evidenceFromDb, "id");
        return evidenceRepo.save(evidenceFromDb);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Evidence evidence) {
        evidenceRepo.delete(evidence);
    }
}
