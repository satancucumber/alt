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
    @Tag("Text")
    @Test
    void testToText()
    {
        System.out.println("======TEST ONE EXECUTED=======");

        Literal literal1n = new Literal();
        literal1n.setName("А");
        literal1n.setDescription("Яблоко красное");
        literal1n.setNegative(true);

        Literal literal1 = new Literal();
        literal1.setName("А");
        literal1.setDescription("Яблоко красное");
        literal1.setNegative(false);

        Literal literal2 = new Literal();
        literal2.setName("B");
        literal2.setDescription("Яблоко ароматное");
        literal2.setNegative(false);

        Literal literal2n = new Literal();
        literal2n.setName("B");
        literal2n.setDescription("Яблоко ароматное");
        literal2n.setNegative(true);

        Literal literal3 = new Literal();
        literal3.setName("C");
        literal3.setDescription("Яблоко вкусное");
        literal3.setNegative(false);

        ResMethodController res = new ResMethodController();

        List<List<List<Literal>>> result = new ArrayList<List<List<Literal>>>(
                Arrays.asList(
                    Arrays.asList(
                        Arrays.asList(literal1),
                        Arrays.asList(literal1n,literal2n, literal3),
                        Arrays.asList(literal2n, literal3)
                        ),
                    Arrays.asList(
                        Arrays.asList(literal2n, literal3),
                        Arrays.asList(literal2),
                        Arrays.asList(literal3)
                    )
                ));

        List<List<String>> text = res.toText(result);

        for (List<String> list : text) {
            for (String str : list) {
                System.out.println(str);
            }
            System.out.println("=============");
        }
        System.out.println(text);
    }
    @Tag("Plot")
    @Test
    void testPlot() {

        Literal n = new Literal();
        n.setName("N");
        n.setDescription("Преступление произошло ночью");
        n.setSuspect(false);
        Literal lb = new Literal();
        lb.setName("LB");
        lb.setSuspect(false);
        lb.setDescription("Брюс пришел на работу утром");
        Literal s = new Literal();
        s.setSuspect(false);
        s.setDescription("Сигнализация работала");
        s.setName("S");
        Literal lc = new Literal();
        lc.setDescription("Карл прошел на работу утром");
        lc.setName("LC");
        lc.setSuspect(false);
        Literal ld = new Literal();
        ld.setName("LD");
        ld.setDescription("Дилан пришел на работу утром");
        ld.setSuspect(false);
        Literal mc = new Literal();
        mc.setDescription("Карл ушел последним");
        mc.setName("MC");
        mc.setSuspect(false);
        Literal md = new Literal();
        md.setDescription("Дилан ушел последним");
        md.setName("MD");
        md.setSuspect(false);
        Literal ma = new Literal();
        ma.setDescription("Алекс ушел последним");
        ma.setName("MA");
        ma.setSuspect(false);
        Literal a = new Literal();
        a.setName("A");
        a.setSuspect(true);
        a.setDescription("Алекс преступник");

        Formula f1 = new Formula();
        f1.setLiterals(Arrays.asList(n));
        f1.setOperators(Arrays.asList("!","*"));
        Formula f2 = new Formula();
        f2.setLiterals(Arrays.asList(lb,lc,ld));
        f2.setOperators(Arrays.asList("!","&","!","*","|","*","*"));
        Formula f3 = new Formula();
        f3.setLiterals(Arrays.asList(lb,s));
        f3.setOperators(Arrays.asList("=>","*","*"));
        Formula f4 = new Formula();
        f4.setLiterals(Arrays.asList(lb,lc,ld));
        f4.setOperators(Arrays.asList("|","*","|","*","*"));
        Formula f5 = new Formula();
        f5.setLiterals(Arrays.asList(mc,md,ma));
        f5.setOperators(Arrays.asList("&","*","&","*","*"));
        Formula f6 = new Formula();
        f6.setLiterals(Arrays.asList(s,n,a));
        f6.setOperators(Arrays.asList("=>","&","*","!","*","*"));

        List<Formula> formulas = Arrays.asList(f1,f3,f4,f5,f6);

        ResMethodController res = new ResMethodController();

        List<List<Formula>> list = res.create(formulas);

        for (List<Formula> formlist : list) {
            System.out.println(formlist.size());
           for (Formula f : formlist) {
               System.out.println(f.getLogform());
               System.out.println(f.getDesform());
           }
        }

            List<List<List<String>>> listString = res.listStr();

            for (List<List<String>> l : listString) {
                System.out.println("____________");
                for (List<String> str : l) {
                    System.out.println(str);
                }
            }

            List<List<List<Literal>>> lit = res.getSteps();

            for (List<List<Literal>> l : lit) {
                System.out.println("шаг_шаг_шаг_шаг_шаг");
                for (List<Literal> d : l) {
                    System.out.println("первый_второй_результат");
                    for (Literal str : d) {
                        System.out.println("__________________");
                        System.out.println(str.getName() + " " + str.getNegative());
                    }
                }
            }

            List<List<String>> steps = res.listSteps();
            for (List<String> str : steps) {
                System.out.println("____________");
                System.out.println(str);

            }




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
        literal1.setSuspect(false);

        Literal literal2 = new Literal();
        literal2.setName("B");
        literal2.setDescription("Яблоко ароматное");
        literal2.setSuspect(false);


        Literal literal3 = new Literal();
        literal3.setName("C");
        literal3.setDescription("Яблоко вкусное");
        literal3.setSuspect(true);

        Formula formula1 = new Formula();
        formula1.setLiterals(Arrays.asList(literal1));
        formula1.setOperators(Arrays.asList("*"));
        Formula formula2 = new Formula();
        formula2.setLiterals(Arrays.asList(literal2));
        formula2.setOperators(Arrays.asList("*"));
        Formula formula3 = new Formula();
        formula3.setLiterals(Arrays.asList(literal1, literal2, literal3));
        formula3.setOperators(Arrays.asList("=>","&","*","*","*"));

        formulas.add(formula1);
        formulas.add(formula2);
        formulas.add(formula3);

        ResMethodController res = new ResMethodController();

        List<List<Formula>> list = res.create(formulas);

//        for (List<Formula> formlist : list) {
//            System.out.println(formlist.size());
//           for (Formula f : formlist) {
//               System.out.println(f.getLogform());
//               System.out.println(f.getDesform());
//           }
//        }

        List<List<List<String>>> listString = res.listStr();

//        for (List<List<String>> l : listString) {
//            System.out.println("____________");
//            for (List<String> s : l) {
//                System.out.println(s);
//            }
//        }

        List<List<List<Literal>>> lit = res.getSteps();

        for (List<List<Literal>> l : lit) {
            System.out.println("шаг_шаг_шаг_шаг_шаг");
            for (List<Literal> d : l) {
                System.out.println("первый_второй_результат");
                for (Literal s : d) {
                    System.out.println("__________________");
                    System.out.println(s.getName() + " " + s.getNegative());
                }
            }
        }

        List<List<String>> steps = res.listSteps();
        for (List<String> s : steps) {
            System.out.println("____________");
                System.out.println(s);

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
