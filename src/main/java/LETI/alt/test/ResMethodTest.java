package LETI.alt.test;

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

    @AfterEach
    void tearThis(){
        System.out.println("END TEST");
    }
    @AfterAll
    static void tear(){
        System.out.println("END TESTING");
    }
}
