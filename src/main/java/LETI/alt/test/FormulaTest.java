package LETI.alt.test;

import LETI.alt.models.Formula;
import org.junit.jupiter.api.*;

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
        List<String> list = Arrays.asList("*", "=>", "(", "(", "!", "*", "<=>", "*", ")", "&", "!", "(", "*", "|", "(", "*", "&", "*", ")", ")", ")");
        System.out.println(list);
        formula.setOperators(list);
        System.out.println(formula.getOperators());
        System.out.println(formula.toPolish());
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
