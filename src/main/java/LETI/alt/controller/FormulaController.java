package LETI.alt.controller;

import LETI.alt.models.Formula;
import LETI.alt.models.Literal;
import LETI.alt.models.Plot;
import LETI.alt.repo.FormulaRepo;
import LETI.alt.repo.PlotRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/formula")
public class FormulaController {
    private final FormulaRepo formulaRepo;
    private final PlotRepo plotRepo;
    @Autowired
    public FormulaController(FormulaRepo formulaRepo, PlotRepo plotRepo) {
        this.formulaRepo = formulaRepo;
        this.plotRepo = plotRepo;
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
        formula.setOperators(formula.toPolish());         // Перевод в прямую польскую запись
        return formulaRepo.save(formula);
    }
    @PostMapping("list")
    public List<Formula> createAll(@RequestBody List<Formula> formulas) {

        return formulaRepo.saveAll(formulas);
    }
    @PutMapping("{id}")
    public Formula update(
            @PathVariable("id") Formula formulaFromDb,
            @RequestBody Formula formula
    ) {
        BeanUtils.copyProperties(formula, formulaFromDb, "id");
        return formulaRepo.save(formulaFromDb);
    }
    @PutMapping("literals/{id}")
    public Formula update(
            @PathVariable("id") Formula formulaFromDb,
            @RequestBody List<Literal> literals
            ) {
        formulaFromDb.setLiterals(literals);
        return formulaRepo.save(formulaFromDb);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Formula formula) {
        formulaRepo.delete(formula);
    }
}
