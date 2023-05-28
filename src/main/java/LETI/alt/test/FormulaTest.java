package LETI.alt.test;

import LETI.alt.models.Formula;
import LETI.alt.models.Literal;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormulaTest {
    @BeforeAll
    static void setUp() {
        System.out.println("START TESTING");
    }
    @BeforeEach
    void setupThis(){
        System.out.println("START TEST");
    }
    // "*", "=>", "(", "(", "!", "*", "<=>", "*", ")", "&", "!", "(", "*", "|", "(", "*", "&", "*", ")", ")", ")"
    // "(", "*", "&", "!", "*", ")", "=>", "!", "(", "*", "|", "*", ")"
    @Tag("TO_POLISH")
    @Test
    void testToPolish()
    {
        System.out.println("======TEST ONE EXECUTED=======");
        Formula formula = new Formula();
        List<String> list = Arrays.asList("(", "*", "&", "(", "*", "=>", "!", "*", ")", ")", "=>", "*");
        System.out.println(list);
        formula.setOperators(list);
        System.out.println(formula.getOperators());
        System.out.println(formula.toPolish());
    }

    @Tag("IMPLICATION")
    @Test
    void testSimImplication()
    {
        System.out.println("======TEST TWO EXECUTED=======");
        Formula formula = new Formula();
        List<String> list = Arrays.asList("=>", "&", "*", "=>", "*", "!", "*", "*");
        System.out.println(list);
        formula.setOperators(list);
        formula.simImplication();
        System.out.println(formula.getOperators());
    }

    @Tag("EQUIVALENCE")
    @Test
    void testSimEquivalence()
    {
        System.out.println("======TEST TWO EXECUTED=======");
        Formula formula = new Formula();
        List<String> list = Arrays.asList("=>", "&", "*", "<=>", "*", "!", "*", "*");
        System.out.println(list);
        formula.setOperators(list);
        formula.simEquivalence();
        System.out.println(formula.getOperators());
    }

    @Tag("DEMORGAN")
    @Test
    void testDeMorgan()
    {
        System.out.println("======TEST THREE EXECUTED=======");
        Formula formula = new Formula();
        List<String> list = Arrays.asList("|", "!", "&", "*", "|", "!", "*", "!", "*", "*");
        System.out.println(list);
        formula.setOperators(list);
        formula.deMorgan();
        System.out.println(formula.getOperators());
    }

    @Tag("DISTRIBUTIVITY")
    @Test
    void testSimDistributivity()
    {
        System.out.println("======TEST FOUR EXECUTED=======");
        Formula formula = new Formula();
        Literal lit1 = new Literal("A");
        Literal lit2 = new Literal("B");
        Literal lit3 = new Literal("C");
        Literal lit4 = new Literal("D");
        Literal lit5 = new Literal("E");
        Literal lit6 = new Literal("F");
        List<Literal> lit = Arrays.asList(lit1, lit2, lit3, lit4, lit5);
        List<String> list = Arrays.asList("|", "|", /*"|",*/ "!", "*", /*"*",*/ "&", "*", "*", "|", "*", "*");
        System.out.println(list);
        formula.setLiterals(lit);
        formula.setOperators(list);
        formula.simDistributivity();
        System.out.println(formula.getOperators());
        for (int i = 0; i < formula.getLiterals().size(); i++) System.out.println(formula.getLiterals().get(i).getName());
    }
    //    @Tag("LOGFORM")
//    @Test
//    void testGetLogform()
//    {
//        System.out.println("======TEST TWO EXECUTED=======");
//        Formula formula = new Formula();
//
//    }
//
//    @Tag("DESFORM")
//    @Test
//    void testGetDesform()
//    {
//        System.out.println("======TEST THREE EXECUTED=======");
//        Formula formula = new Formula();
//
//    }
    @AfterEach
    void tearThis(){
        System.out.println("END TEST");
    }
    @AfterAll
    static void tear(){
        System.out.println("END TESTING");
    }
}
