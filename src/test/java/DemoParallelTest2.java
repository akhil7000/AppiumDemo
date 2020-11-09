import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 ** Demo Test - for parallel execution 2
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DemoParallelTest2 {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @BeforeAll
    public void setUp() {
        logger.info("BeforeAll setup");
    }


    @BeforeEach
    public void setupAll(){
        logger.info("BeforeEach setup");
    }


    @Test
    public void test2Demo1() throws InterruptedException {
        logger.info("Test1");
        Thread.sleep(5000);

    }

    @Test
    public void test2Demo2() throws InterruptedException {
        logger.info("Test2");
        Thread.sleep(5000);

    }

    @ParameterizedTest
    @ValueSource(strings = {"Parameter1","Parameter2"})
    public void test2Demo3(String test)  {
        logger.info("Test3: "+test);
    }

    @Tag("Royal")
    @Test
    public void test2DemoTag() throws InterruptedException {
        logger.info("Only for a given tag");
        Thread.sleep(5000);
    }

    @AfterAll
    public void teardown(){
        logger.info("AfterAll setup");
    }

    @AfterEach
    public void teardownEach(){
        logger.info("AfterEach teardown");
    }

}
