import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 ** Demo Test - for parallel execution 1
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DemoParallelTest1 {
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
    public void testDemo1() throws InterruptedException {
        logger.info("Test1");
        Thread.sleep(5000);
    }

    @Test
    public void testDemo2() throws InterruptedException {
        logger.info("Test2");
        Thread.sleep(5000);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Parameter1","Parameter2"})
    public void testDemo3(String test)  {
        logger.info("Test3: "+test);
    }

    @Tag("Royal")
    @Test
    public void testDemoTag() throws InterruptedException {
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
