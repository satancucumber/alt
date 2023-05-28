package LETI.alt.models;

import LETI.alt.Logical.Operator;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "formula", schema = "detective")
public class Formula {
    private static Map<String, Operator> LOperators = Map.of(
            "!", new Operator("!", "НЕ *", "!*"),
            "&", new Operator("&", "* И *", "*&*"),
            "|", new Operator("|", "* ИЛИ *", "*|*"),
            "=>", new Operator("=>", "ЕСЛИ *, ТО *", "*=>*"),
            "<=>", new Operator("<=>", "* ТОГДА И ТОЛЬКО ТОГДА, КОГДА *", "*<=>*")
    );
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "description")
    private String description;
    @ManyToMany
    @JoinTable (name="formula_literal", schema = "detective",
            joinColumns=@JoinColumn (name="formula_id"),
            inverseJoinColumns=@JoinColumn(name="literal_id"))
    private List<Literal> literals;
    @Column(name = "operators")
    private List<String> operators;
    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="plot_id")
    private Plot plot;
//    private String logform;
//    private String desform;


    public List<String> getOperators() {
        return operators;
    }

    /*
        (A&B)|C
        operators: [(, *, &, *, ), |, *]

        operators: [ '*', '=>', '(', '(', '!', '*', '<=>', '*', ')', '&', '!', '(', '*', '|', '(', '*', '&', '*', ')', ')', ')' ]
        tree:
                                           =>
                                       *        &
                                            <=>     !
                                           !   *    |
                                           *      *   &
                                                     * *

        operators: [ =>, *, &, <=>, !, *, *, !, |, &, *, * ]
     */
    public List<String> toPolish() {
        List<String> l = new ArrayList<String>();
        Stack<String> r = new Stack<String>();
        for (String operator : this.operators) {
            r.push(operator);
        }
        Stack<String> s = new Stack<String>();
        String item;
        while (r.size() != 0) {
            item = r.pop();
            if (item.equals("(")) {
                String operator = s.pop();
                while (!operator.equals(")")) {
                    l.add(operator);
                    operator = s.pop();
                }
            } else if (item.equals("*") | item.equals("!")) {
                l.add(item);
            } else {
                s.push(item);
            }
        }
        while (s.size() != 0) {
            l.add(s.pop());
        }
        Collections.reverse(l);  // Если убрать получится обратная польская запись
        return l;
    }
    public void negative(){
        Stack<String> r = new Stack<>();
        List<String> l = new ArrayList<>();
        List<String> s;

        s = this.operators;
        Collections.reverse(s);
        for(String operator: s){
            r.push(operator);
        }

        r.push("!");
        Collections.reverse(r);

        while (r.size() != 0){
            l.add(r.pop());
        }
        this.operators = l;
        deMorgan();

    }
    public void simplificationCF() {
        List<String> l;
        Stack<String> p = new Stack<>();
        Stack<String> r = new Stack<>();
        String item;

        l = this.operators;
//        Collections.reverse(l); //Реверсируем, что записать нормально в стек

        for (String operator: l) { // [=>, &, *, <=>, *, !, *, *]
            p.push(operator);
        }

        while (p.size() != 0){
            item = p.pop();
            switch (item) {
                case ("=>") -> simImplication();
                case ("<=>") -> simEquivalence();
            }
        }
        deMorgan();

    }

    public void simImplication(){
        Stack<List<String>> r = new Stack<List<String>>();
        Stack<String> p = new Stack<String>();

        List<String> l = this.operators;

        for (String operator: l) { // [=>, &, *, <=>, *, !, *, *]
            p.push(operator);
        }

        while (p.size() != 0) {
            switch (p.peek()) {
                case ("*") -> {
                    r.push(List.of(p.pop()));
                }
                case ("!") -> {
                    List<String> list = new ArrayList<>(List.of(p.pop()));
                    list.addAll(r.pop());
                    r.push(list);
                }
                case ("&"), ("|"), ("<=>") -> {
                    List<String> list = new ArrayList<>(List.of(p.pop()));
                    list.addAll(r.pop());
                    list.addAll(r.pop());
                    r.push(list);
                }
                case ("=>") -> {
                    p.pop();
                    List<String> list = new ArrayList<>(List.of("|"));
                    if (r.peek().get(r.peek().size()-1).equals("!")){
                        List<String> a = r.pop();
                        a.remove(0);
                        list.addAll(a);
                    }else {
                        list.add("!");
                        list.addAll(r.pop());
                    }

                    list.addAll(r.pop());
                    r.push(list);
                }
            }
        }

        Collections.reverse(r);
        this.operators = r.pop();
    }

    public void simEquivalence(){
        Stack<List<String>> r = new Stack<>();
        Stack<String> p = new Stack<>();

        List<String> l = this.operators;

        for (String operator: l) { // [=>, &, *, <=>, *, !, *, *]
            p.push(operator);
        }

        while (p.size() != 0) {
            switch (p.peek()) {
                case ("*") -> {
                    r.push(List.of(p.pop()));
                }
                case ("!") -> {
                    List<String> list = new ArrayList<>(List.of(p.pop()));
                    list.addAll(r.pop());
                    r.push(list);
                }
                case ("&"), ("|"), ("=>") -> {
                    List<String> list = new ArrayList<>(List.of(p.pop()));
                    list.addAll(r.pop());
                    list.addAll(r.pop());
                    r.push(list);
                }
                case ("<=>") -> {
                    p.pop();
                    List<String> list = new ArrayList<>(List.of("|"));
                    List<String> a = r.pop();
                    List<String> b = r.pop();
                    list.add("&");
                    list.addAll(a);
                    list.addAll(b);
                    list.add("&");
                    if (Objects.equals(a.get(0), "!")){
                        a.remove(0);
                        list.addAll(a);
                    }else {
                        list.add("!");
                        list.addAll(a);
                    }
                    if (Objects.equals(b.get(0), "!")){
                        b.remove(0);
                        list.addAll(b);
                    }else {
                        list.add("!");
                        list.addAll(b);
                    }

                    r.push(list);
                }
            }
        }

        Collections.reverse(r);
        this.operators = r.pop();
    }

    public void deMorgan(){
        Stack<List<String>> r = new Stack<List<String>>();
        Stack<String> p = new Stack<String>();

        List<String> l = this.operators;

        for (String operator: l) { // [=>, &, *, <=>, *, !, *, *]
            p.push(operator);
        }
        while (p.size() != 0) {
            switch (p.peek()) {
                case ("*") -> {
                    r.push(List.of(p.pop()));
                }
                case ("!") -> {
                    List<String> list = new ArrayList<>(List.of(p.pop()));
                    list.addAll(r.pop());
                    r.push(list);
                }
                case ("&"), ("|") -> {
                    List<String> list = new ArrayList<>();
                    String a = p.pop();
                    if (p.size() != 0) {
                            if (Objects.equals(p.peek(), "!")) {
                                p.pop();
                                switch (a) {
                                    case ("&") -> {
                                        list.add("|");
                                        List<String> d = r.pop();
                                        List<String> b = r.pop();
                                        if (Objects.equals(d.get(0), "!")){
                                            d.remove(0);
                                            list.addAll(d);
                                        }else {
                                            list.add("!");
                                            list.addAll(d);
                                        }
                                        if (Objects.equals(b.get(0), "!")){
                                            b.remove(0);
                                            list.addAll(b);
                                        }else {
                                            list.add("!");
                                            list.addAll(b);
                                        }
                                    }
                                    case ("|") -> {
                                        list.add("&");
                                        List<String> d = r.pop();
                                        List<String> b = r.pop();
                                        if (Objects.equals(d.get(0), "!")){
                                            d.remove(0);
                                            list.addAll(d);
                                        }else {
                                            list.add("!");
                                            list.addAll(d);
                                        }
                                        if (Objects.equals(b.get(0), "!")){
                                            b.remove(0);
                                            list.addAll(b);
                                        }else {
                                            list.add("!");
                                            list.addAll(b);
                                        }
                                    }
                                }
                            } else {
                                list.add(a);
                                list.addAll(r.pop());
                                list.addAll(r.pop());
                            }
                        } else {
                        list.add(a);
                        list.addAll(r.pop());
                        list.addAll(r.pop());
                    }
                    r.push(list);
                    }
                }
            }
        Collections.reverse(r);
        this.operators = r.pop();
        while (valDeMorgan()) deMorgan();
    }

    public Boolean valDeMorgan() {
        Stack<String> p = new Stack<String>();
        List<String> l = this.operators;
        Collections.reverse(l);
        for (String operator: l) { // [=>, &, *, <=>, *, !, *, *]
            p.push(operator);
        }
        Collections.reverse(l);

        while (p.size() != 0){
            if ("!".equals(p.peek())) {
                p.pop();
                if ((Objects.equals(p.peek(), "&")) | (Objects.equals(p.peek(), "|"))) return true;
            } else {
                p.pop();
            }
        }

        return false;
    }

    public void simDistributivity(){
        Stack<List<String>> r = new Stack<List<String>>();
        Stack<String> p = new Stack<String>();

        List<String> l = this.operators;

        for (String operator: l) {
            p.push(operator);
        }


    }

    public void setOperators(List<String> operators) {
        this.operators = operators;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public List<Literal> getLiterals() {
        return literals;
    }

    public void setLiterals(List<Literal> literals) {
        this.literals = literals;
    }

    /*
        literals: [.getName()=A, .getName()=B, .getName()=C]
        operators: ['=>','&','*','*','*']

        return: "(A&B)=>C"
     */
    public String getLogform() {
        Stack<String> s = new Stack<String>();
        int k = 0;
        for (String key : this.operators) {
            if (this.LOperators.containsKey(key)) {
                s.push(key);
            } else {
                s.push(this.literals.get(k++).getName());
            }
        }
        /*
            s = ["C", "B", "A", "&", "=>"]
         */
        Stack<String> l = new Stack<String>();
        while (s.size() != 0) {
            if (s.peek().equals("!")) {
                Operator operator = this.LOperators.get(s.pop());
                l.push(operator.toString(l.pop()));
            } else if (this.LOperators.containsKey(s.peek())) {
                Operator operator = this.LOperators.get(s.pop());
                if (l.size() == 2) {
                    l.push(operator.toString(l.pop(), l.pop()));
                } else {
                    l.push("(" + operator.toString(l.pop(), l.pop()) + ")");
                }
            } else {
                l.push(s.pop());
            }
        }
        return l.pop();
    }
    /*
        literals: [.getName()=A, .getName()=B, .getName()=C]
        operators: ['=>','&','*','*','*']

        (A&B)=>C

        A - яблоко красное
        B - яблоко ароматное
        С - яблоко вкусное

        return: "ЕСЛИ(яблоко красное И яблоко ароматное), ТО(яблоко вкусное)"
     */
    public String getDesform() {
        Stack<String> s = new Stack<String>();
        int k = 0;
        for (String key : this.operators) {
            if (this.LOperators.containsKey(key)) {
                s.push(key);
            } else {
                s.push(this.literals.get(k++).getDescription());
            }
        }
        Stack<String> l = new Stack<String>();
        while (s.size() != 0) {
            String key = s.pop();
            if (key.equals("!")) {
                Operator operator = this.LOperators.get(key);
                if (l.peek().indexOf('(') == 0) {
                    l.push(operator.getDescription(l.pop()));
                } else {
                    l.push(operator.getDescription("(" + l.pop() + ")") );
                }
            } else if (this.LOperators.containsKey(key)) {
                Operator operator = this.LOperators.get(key);
                if (l.size() == 2) {
                    l.push(operator.getDescription(l.pop(), l.pop()));
                } else {
                    l.push("(" + operator.getDescription(l.pop(), l.pop()) + ")");
                }
            } else {
                l.push(key);
            }
        }
        return l.pop();
    }

}

