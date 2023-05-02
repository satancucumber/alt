package LETI.alt.controller;

import LETI.alt.models.Formula;
import LETI.alt.repo.FormulaRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/formula")
public class FormulaController {
    private final FormulaRepo formulaRepo;
    @Autowired
    public FormulaController(FormulaRepo formulaRepo) {
        this.formulaRepo = formulaRepo;
    }

    @GetMapping
    public List<Formula> list() {
        return formulaRepo.findAll();
    }
    @GetMapping("{id}")
    public Formula getOne(@PathVariable("id") Formula formula) {
        return formula;
    }
    @GetMapping("{id}/logform")
    public String getLofForm(@PathVariable("id") Formula formula) {return formula.getLogform();}
    @GetMapping("{id}/desform")
    public String getDesForm(@PathVariable("id") Formula formula) {return formula.getDesform();}
    @PostMapping
    public Formula create(@RequestBody Formula formula) {
        return formulaRepo.save(formula);
    }
    @PutMapping("{id}")
    public Formula update(
            @PathVariable("id") Formula formulaFromDb,
            @RequestBody Formula formula
    ) {
        BeanUtils.copyProperties(formula, formulaFromDb, "id");
        return formulaRepo.save(formulaFromDb);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Formula formula) {
        formulaRepo.delete(formula);
    }
}
