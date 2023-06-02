package LETI.alt.controller;

import LETI.alt.models.Formula;
import LETI.alt.models.Literal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/resmethod")
public class ResMethodController {
    private List<List<Formula>> resFormulas;
    private List<List<String>> resStrKNF;
//    private List<List<Literal>> resKNF;
    @GetMapping
    public List<List<Formula>> listFormulas() {
        return this.resFormulas;
    }
    @GetMapping("string")
    public List<List<String>> listString() {
        return this.resStrKNF;
    }
    @PostMapping
    public List<List<Formula>> create(@RequestBody List<Formula> formulas) {
        List<List<Formula>> resFormulas = new ArrayList<List<Formula>>();
        List<List<String>> resStrKNF = new ArrayList<List<String>>();
//        List<List<Literal>> resKNF = new ArrayList<List<Literal>>();
        for (Formula formula : formulas) {
            List<Formula> list = new ArrayList<Formula>();
            Formula newFormula = new Formula();
            newFormula.setId(formula.getId());
            newFormula.setDescription(formula.getDescription());
            newFormula.setOperators(formula.getOperators());
            newFormula.setLiterals(formula.getLiterals());
            newFormula.setPlot(formula.getPlot());
            newFormula.simImplication();
            if (!formula.getOperators().equals(newFormula.getOperators())) {
                Formula f = new Formula();
                f.setId(newFormula.getId());
                f.setDescription(newFormula.getDescription());
                f.setOperators(newFormula.getOperators());
                f.setLiterals(newFormula.getLiterals());
                f.setPlot(newFormula.getPlot());
                list.add(f);
            }
            newFormula.deMorgan();
            if (list.size() == 0) {
                if (!formula.getOperators().equals(newFormula.getOperators())) {
                    Formula f = new Formula();
                    f.setId(newFormula.getId());
                    f.setDescription(newFormula.getDescription());
                    f.setOperators(newFormula.getOperators());
                    f.setLiterals(newFormula.getLiterals());
                    f.setPlot(newFormula.getPlot());
                    list.add(f);
                }
            }
            else {
                if (!list.get(list.size()-1).getOperators().equals(newFormula.getOperators())) {
                    Formula f = new Formula();
                    f.setId(newFormula.getId());
                    f.setDescription(newFormula.getDescription());
                    f.setOperators(newFormula.getOperators());
                    f.setLiterals(newFormula.getLiterals());
                    f.setPlot(newFormula.getPlot());
                    list.add(f);
                }
            }
            newFormula.simDistributivity();
            if (list.size() == 0) {
                if (!formula.getOperators().equals(newFormula.getOperators())) {
                    Formula f = new Formula();
                    f.setId(newFormula.getId());
                    f.setDescription(newFormula.getDescription());
                    f.setOperators(newFormula.getOperators());
                    f.setLiterals(newFormula.getLiterals());
                    f.setPlot(newFormula.getPlot());
                    list.add(f);
                }
            }
            else {
                if (!list.get(list.size()-1).getOperators().equals(newFormula.getOperators())) {
                    Formula f = new Formula();
                    f.setId(newFormula.getId());
                    f.setDescription(newFormula.getDescription());
                    f.setOperators(newFormula.getOperators());
                    f.setLiterals(newFormula.getLiterals());
                    f.setPlot(newFormula.getPlot());
                    list.add(f);
                }
            }
            if (list.size() == 0) {
                formula.setDescription("Оставляем без изменений: ");
                list.add(formula);
            }
            resFormulas.add(list);

            List<List<Literal>> knf = newFormula.toKNF();

//            resKNF.addAll(knf);

            List<String> strknf = new ArrayList<String>();

            for (List<Literal> literals : knf) {
                String dis = "";
                for (Literal literal : literals) {
                    if (literal.getNerative()) {
                        dis += "!" + literal.getName();
                    }
                    else {
                        dis += literal.getName();
                    }
                    if (!literal.equals(literals.get(literals.size() - 1))) {
                        dis += "|";
                    }
                }
                strknf.add(dis);
            }
            resStrKNF.add(strknf);
        }

        this.resFormulas = resFormulas;
//        this.resKNF = resKNF;
        this.resStrKNF = resStrKNF;

//        for (List<Formula> formlist : resFormulas) {
//            System.out.println(formlist.size());
//            for (Formula f : formlist) {
//                System.out.println(f.getLogform());
//                System.out.println(f.getDesform());
//            }
//        }

        return resFormulas;
    }
}

