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
        System.out.println("======TEST IMPLICATION EXECUTED=======");
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
        System.out.println("======TEST DE MORGAN EXECUTED=======");
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
        Literal lit1 = new Literal();
        lit1.setName("A");
        Literal lit2 = new Literal();
        lit2.setName("B");
        Literal lit3 = new Literal();
        lit3.setName("C");
        Literal lit4 = new Literal();
        lit4.setName("D");
        Literal lit5 = new Literal();
        lit5.setName("E");
        Literal lit6 = new Literal();
        lit6.setName("F");
        List<Literal> lit = Arrays.asList(lit1, lit2, lit3, lit4, lit5);
        List<String> list = Arrays.asList("|", "&", /*"|",*/ "!", "*", /*"*",*/ "&", "*", "*", "&", "*", "*");
        System.out.println(list);
        formula.setLiterals(lit);
        formula.setOperators(list);
        formula.simDistributivity();
        System.out.println(formula.getLogform());
        for (int i = 0; i < formula.getLiterals().size(); i++) System.out.println(formula.getLiterals().get(i).getName());
    }
    @Tag("KNF")
    @Test
    void testToKNF()
    {
        System.out.println("======TEST KNF EXECUTED=======");
        Formula formula = new Formula();
        Literal lit1 = new Literal();
        lit1.setName("A");
        Literal lit2 = new Literal();
        lit2.setName("B");
        Literal lit3 = new Literal();
        lit3.setName("C");
        Literal lit4 = new Literal();
        lit4.setName("D");
        List<Literal> lit = Arrays.asList(lit1, lit2, lit3, lit1, lit2, lit1, lit4, lit3, lit4);
        List<String> op = Arrays.asList("&", "|", "|", "!", "*", "*", "*", "&", "|", "|", "*", "!", "*", "|", "*", "*", "|", "!", "*", "!", "*" );
        formula.setLiterals(lit);
        formula.setOperators(op);

        System.out.println(formula.getLogform());

        List<List<Literal>> dis = formula.toKNF();

        for (List<Literal> list : dis) {
            System.out.println("________________");
            for (Literal literal : list) {
                System.out.println(literal.getName() + " " + literal.getNerative());
            }
        }

    }

    @Tag("DESFORM")
    @Test
    void testGetDesform()
    {
        System.out.println("======TEST THREE EXECUTED=======");

        List<Literal> literals1 = new ArrayList<Literal>();
        List<Literal> literals2 = new ArrayList<Literal>();
        List<Literal> literals3 = new ArrayList<Literal>();

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
        formula3.setLiterals(Arrays.asList(literal1, literal2, literal1, literal3));
        formula3.setOperators(Arrays.asList("&", "|", "!", "*", "*", "|", "!", "*", "*"));


        System.out.println(formula1.getOperators());
        System.out.println(formula1.getLiterals().size());
        for (Literal literal : formula1.getLiterals()) {
            System.out.println(literal.getName());
        }

        System.out.println(formula2.getOperators());
        System.out.println(formula2.getLiterals().size());
        for (Literal literal : formula2.getLiterals()) {
            System.out.println(literal.getName());
            System.out.println(literal.getDescription());
        }

        System.out.println(formula3.getOperators());
        System.out.println(formula3.getLiterals().size());
        for (Literal literal : formula3.getLiterals()) {
            System.out.println(literal.getName());
        }

        System.out.println(formula1.getDesform());
        System.out.println(formula2.getDesform());
        System.out.println(formula3.getDesform());

        System.out.println(formula1.getLogform());
        System.out.println(formula2.getLogform());
        System.out.println(formula3.getLogform());



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