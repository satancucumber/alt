package LETI.alt.controller;

import LETI.alt.models.Literal;
import LETI.alt.repo.LiteralRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/literal")
public class LiteralController {
    private final LiteralRepo literalRepo;
    @Autowired
    public LiteralController(LiteralRepo literalRepo) {
        this.literalRepo = literalRepo;
    }

    @GetMapping
    public List<Literal> list() {
        return literalRepo.findAll();
    }
    @GetMapping("{id}")
    public Literal getOne(@PathVariable("id") Literal literal) {
        return literal;
    }
    @PostMapping
    public Literal create(@RequestBody Literal literal) {
        return literalRepo.save(literal);
    }
    @PostMapping("/list")
    public List<Literal> createAll(@RequestBody List<Literal> literals) {
        return literalRepo.saveAll(literals);
    }
    @PutMapping("{id}")
    public Literal update(
            @PathVariable("id") Literal literalFromDb,
            @RequestBody Literal literal
    ) {
        BeanUtils.copyProperties(literal, literalFromDb, "id");
        return literalRepo.save(literalFromDb);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Literal literal) {
        literalRepo.delete(literal);
    }
}
