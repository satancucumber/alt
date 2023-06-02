package LETI.alt.Logical;

import LETI.alt.models.Formula;
import LETI.alt.models.Literal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ResMethod {
    private List<Formula> formulas;
    private List<List<Literal>> res;
    private Literal suspect;

    public Literal getSuspect() {
        return suspect;
    }

    public void setSuspect(Literal suspect) {
        this.suspect = suspect;
    }

    public List<Formula> getFormulas() {
        return formulas;
    }
    public void setFormulas(List<Formula> formulas) {
        this.formulas = formulas;
    }

    public List<List<Literal>> getRes() {
        return res;
    }

    public void setRes(List<List<Literal>> res) {
        this.res = res;
    }

//    public void resolution() {
//        List<List<Literal>> res = this.getRes();
//        Literal suspect = this.getSuspect();
//        suspect.setNerative(true);
//        res.add(Arrays.asList(suspect));
//
//
//
//        for (int i = 0; i < res.size(); i++) {
//            for (int j = i + 1; j < res.size(); j++) {
//                List<Literal> first = new ArrayList<Literal>();
//                first.addAll(res.get(i));
//                List<Literal> second = new ArrayList<Literal>();
//                second.addAll(res.get(j));
//                Literal delf;
//                Literal dels;
//                for (Literal f : first) {
//                    for (Literal s : second) {
//                        if (f.getName().equals(s.getName()) &&
//                                ((!f.getNerative() && s.getNerative()) ||
//                                        (f.getNerative() && !s.getNerative()))) {
//                            delf = f;
//                            dels = s;
//                        }
//                    }
//
//                }
//            }
//        }
//    }
}
