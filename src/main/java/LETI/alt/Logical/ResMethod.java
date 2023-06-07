package LETI.alt.Logical;

import LETI.alt.models.Literal;

import java.util.*;

public class ResMethod {
    private List<List<Literal>> res;
    private Literal suspect;
    private int error;
    private HashMap<List<Literal>, List<List<Literal>>> path;

    public int getError() {return error;}

    public void setError(int Error) {this.error = Error;}

    public Literal getSuspect() {
        return suspect;
    }

    public void setSuspect(Literal suspect) {
        this.suspect = suspect;
    }

    public List<List<Literal>> getRes() {
        return res;
    }

    public void setRes(List<List<Literal>> res) {
        this.res = res;
    }

    public void setPath(HashMap<List<Literal>, List<List<Literal>>> path) {this.path = path;}

    public HashMap<List<Literal>, List<List<Literal>>> getPath() {return path;}

    public void makeRes(int k) {
        List<List<Literal>> resNew = new ArrayList<>(new ArrayList<>());
        if (k == 0) {
            for (List<Literal> i : this.res) {
                for (Literal j : i) {
                    if (j.getSuspect()) {
                        this.suspect = j;
                        break;
                    }
                }
            }
            Literal literal = new Literal();
            literal.setNegative(true);
            literal.setSuspect(true);
            literal.setName(this.suspect.getName());
            literal.setDescription(this.suspect.getDescription());
            literal.setId(this.suspect.getId());
            literal.setFormulas(this.suspect.getFormulas());
            this.res.add(List.of(literal));
            List<List<Literal>> r = new ArrayList<>(Arrays.asList(null, null));
            for (List<Literal> w: this.getRes()) {
                path.put(w, r);
            }
        }
        for (int i = 0; i < this.getRes().size() - 1; i++) {
            for (int j = i + 1;j < this.getRes().size(); j++) {
                List<Literal> a = new ArrayList<>(new ArrayList<>(this.getRes().get(i)));
                List<Literal> b = new ArrayList<>(new ArrayList<>(this.getRes().get(j)));
                List<Integer> flag_dublicate = new ArrayList<>();
                List<Integer> remove_a = new ArrayList<>();
                List<Integer> remove_b = new ArrayList<>();
                boolean resolution = false;
                for (int m = 0; m < a.size(); m++) {
                    for (int n = 0; n < b.size(); n++) {
                        if (Objects.equals(a.get(m).getName(), b.get(n).getName())) {
                            if (Objects.equals(a.get(m).getNegative(), b.get(n).getNegative())) {
                                boolean flag_dublicate_bool = false;
                                for (Integer l : flag_dublicate) {
                                    if (Objects.equals(a.get(l).getName(), a.get(m).getName())) {
                                        flag_dublicate_bool = true;
                                        break;
                                    }
                                }
                                remove_b.add(n);
                                if (!flag_dublicate_bool) {
                                    flag_dublicate.add(m);
                                } else {
                                    remove_a.add(m);
                                }
                            } else {
                                List<Integer> quadruplicates = new ArrayList<>(new ArrayList<>(flag_dublicate));
                                boolean b1 = false;
                                remove_a.add(m);
                                remove_b.add(n);
                                resolution = true;
                                for (int p = n + 1; p < b.size(); p++) {
                                    if (Objects.equals(a.get(m).getName(), b.get(p).getName())) {
                                        if (Objects.equals(a.get(m).getNegative(), b.get(p).getNegative())) {
                                            for (Integer l : quadruplicates) {
                                                if (Objects.equals(a.get(l).getName(), a.get(p).getName())) {
                                                    b1 = true;
                                                    break;
                                                }
                                            }
                                            remove_b.add(p);
                                            if (!b1) {
                                                quadruplicates.add(m);
                                            } else {
                                                remove_a.add(m);
                                            }
                                            b1 = false;

                                        }
                                    }
                                }
                                if (m+1 <= a.size()) {
                                    for (int u = m + 1; u < a.size(); u++) {
                                        for (int v = 0; v < b.size(); v++) {
                                            if (Objects.equals(a.get(u).getName(), b.get(v).getName())) {
                                                if (Objects.equals(a.get(u).getNegative(), b.get(v).getNegative())) {
                                                    for (Integer l : quadruplicates) {
                                                        if (Objects.equals(a.get(l).getName(), a.get(u).getName())) {
                                                            b1 = true;
                                                            break;
                                                        }
                                                    }
                                                    remove_b.add(v);
                                                    if (!b1) {
                                                        quadruplicates.add(u);
                                                    } else {
                                                        remove_a.add(u);
                                                    }
                                                    b1 = false;

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (resolution) {
                    List<Literal> c = new ArrayList<>(collecting(a, remove_a));
                    c.addAll(collecting(b, remove_b));
                    if (!valRepeatRes(resNew, c)) {
                        path.put(c, Arrays.asList(a, b));
                        resNew.add(c);
                    }
                }
            }
        }
        this.res.addAll(resNew);
        for (List<Literal> i : this.getRes()) {
            for (Literal j: i) System.out.println(j.getName() + " " + j.getNegative());
            System.out.println("_");
        }
        System.out.println("==================");


        if (!valMakeRes()) {
            if (k < 10) {
                k++;
                makeRes(k);
            } else {
                res.clear();
            }
        } else {
            List<Literal> empty_flag = new ArrayList<>();
            for (List<Literal> i : this.res) {
                if (i.size() == 0) {
                    empty_flag = i;
                    break;
                }
            }
            List<List<Literal>> empty_flag1 = path.get(empty_flag);
            List<List<List<Literal>>> result = new ArrayList<>();
            List<List<Literal>> iterator = new ArrayList<>();
            List<List<Literal>> iterator1 = new ArrayList<>();
            result.add(empty_flag1);
            while (true) {
                iterator.add(empty_flag1.get(0));
                iterator = path.get(empty_flag1.get(0));
                result.add(iterator);
                iterator.add(empty_flag1.get(1));
                iterator = path.get(empty_flag1.get(1));
                result.add(iterator);
            }
        }
    }

    private List<Literal> collecting(List<Literal> list, List<Integer> remove) {
        List<Literal> result = new ArrayList<>();
        boolean flag_remove = false;
        for (int iterator = 0; iterator < list.size(); iterator++) {
            for (int iterator1 : remove){
                if (iterator == iterator1) {
                    flag_remove = true;
                    break;
                }
            }
            if (!flag_remove) {
                result.add(list.get(iterator));
            }
            flag_remove = false;
        }
        return result;
    }

    private boolean valMakeRes() {
        for (List<Literal> i: this.res) {
            if (i.size() == 0) return true;
        }
        return false;
    }

    private boolean valRepeatRes(List<List<Literal>> newRes, List<Literal> list) {
        for (List<Literal> i: newRes) {
            if (Objects.equals(i, list)) {
                return true;
            }
        }

        for (List<Literal> j: this.getRes()) {
            if (Objects.equals(j, list)) {
                return true;
            }
        }

        return false;
    }
}
