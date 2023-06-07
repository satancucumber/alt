package LETI.alt.test;

import LETI.alt.Logical.ResMethod;
import LETI.alt.controller.ResMethodController;
import LETI.alt.models.Formula;
import LETI.alt.models.Literal;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResMethodTest {
    @BeforeAll
    static void setUp() {
        System.out.println("START TESTING");
    }
    @BeforeEach
    void setupThis(){
        System.out.println("START TEST");
    }

    @Tag("RESOLUTION")
    @Test
    void testResMethod()
    {
        System.out.println("======TEST ONE EXECUTED=======");
        List<Formula> formulas = new ArrayList<Formula>();

        Literal literal1 = new Literal();
        literal1.setName("А");
        literal1.setDescription("Яблоко красное");

        Literal literal2 = new Literal();
        literal2.setName("B");
        literal2.setDescription("Яблоко зелёное");


        Literal literal3 = new Literal();
        literal3.setName("C");
        literal3.setDescription("Яблоко желтое");

        Formula formula1 = new Formula();
        formula1.setLiterals(Arrays.asList(literal1, literal2));
        formula1.setOperators(Arrays.asList("!","&","*","*"));
        Formula formula2 = new Formula();
        formula2.setLiterals(Arrays.asList(literal2, literal3));
        formula2.setOperators(Arrays.asList("|","*","*"));
        Formula formula3 = new Formula();
        formula3.setLiterals(Arrays.asList(literal1, literal2, literal3));
        formula3.setOperators(Arrays.asList("!","!","=>","*","&","*","*"));

        formulas.add(formula1);
        formulas.add(formula2);
        formulas.add(formula3);

        ResMethodController res = new ResMethodController();
        List<List<Formula>> list = res.create(formulas);
        for (List<Formula> formlist : list) {
            System.out.println(formlist.size());
           for (Formula f : formlist) {
               System.out.println(f.getLogform());
               System.out.println(f.getDesform());
           }
        }

        List<List<String>> listString = res.listString();
        for (List<String> l : listString) {
            System.out.println("____________");
            for (String s : l) {
                System.out.println(s);
            }
        }

    }

    @Tag("MAKE_RESOLUTION")
    @Test
    void testMakeRes() {
        Literal literal1 = new Literal();
        literal1.setName("А");
        literal1.setDescription("Яблоко красное");
        literal1.setSuspect(false);
        literal1.setNegative(true);

        Literal literal2 = new Literal();
        literal2.setName("B");
        literal2.setDescription("Яблоко зелёное");
        literal2.setSuspect(false);
        literal2.setNegative(true);

        Literal literal3 = new Literal();
        literal3.setName("C");
        literal3.setDescription("Яблоко желтое");
        literal3.setSuspect(true);
        literal3.setNegative(false);

        Literal literal4 = new Literal();
        literal4.setName("А");
        literal4.setDescription("Яблоко красное");
        literal4.setSuspect(false);
        literal4.setNegative(false);

        Literal literal5 = new Literal();
        literal5.setName("B");
        literal5.setDescription("Яблоко зелёное");
        literal5.setSuspect(false);
        literal5.setNegative(false);

        List<Literal> list1 = new ArrayList<>(List.of(literal1));
        List<Literal> list2 = new ArrayList<>(List.of(literal2));
        List<Literal> list3 = new ArrayList<>(Arrays.asList(literal4, literal5, literal3));
        List<List<Literal>> list = new ArrayList<>(Arrays.asList(list1, list2, list3));
        ResMethod resolution = new ResMethod();
        resolution.setRes(list);
        for (List<Literal> i : resolution.getRes()) {
            for (Literal j: i) System.out.println(j.getName() + " " + j.getNegative());
            System.out.println("_");
        }
        System.out.println("==================");
        resolution.makeRes(0);
        for (List<Literal> k : resolution.getRes()) {
            for (Literal l: k) System.out.println(l.getName() + " " + l.getNegative());
            System.out.println("_");
        }
        System.out.println("================== SOLUTION ===================");
        for (List<List<Literal>> m: resolution.getSolution()) {
            for (List<Literal> n : m) {
                for (Literal o: n) System.out.println(o.getName() + " " + o.getNegative());
                System.out.println("_");
            }
            System.out.println("!!");
        }
        System.out.println("================== CONDITION ==================");
        for (List<Literal> u : resolution.getCondition()) {
            for (Literal v: u) System.out.println(v.getName() + " " + v.getNegative());
            System.out.println("_");
        }
    }
    @AfterEach
    void tearThis(){
        System.out.println("END TEST");
    }
    @AfterAll
    static void tear(){
        System.out.println("END TESTING");
    }
}
