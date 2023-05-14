package LETI.alt.test;

import org.junit.jupiter.api.*;

public class ResMethodTest {
    @BeforeAll
    static void setUp() {
        System.out.println("START TESTING");
    }
    @BeforeEach
    void setupThis(){
        System.out.println("START TEST");
    }

//    @Tag("RESOLUTION")
//    @Test
//    void testResMethod()
//    {
//        System.out.println("======TEST ONE EXECUTED=======");
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
