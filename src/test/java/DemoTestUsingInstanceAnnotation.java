import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 ** Demo Test - for TestInstance annotation - PER_CLASS
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DemoTestUsingInstanceAnnotation {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    int count=0;


    @BeforeEach
    public void setupAll(){
        logger.info("BeforeEach setup");
    }


    @Test
    public void testDemo1() {
        logger.info("Test1");
        logger.info("Count: "+String.valueOf(++count));
    }

    @Test
    public void testDemo2() {
        logger.info("Test2");
        logger.info("Count: "+String.valueOf(++count));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Parameter1","Parameter2"})
    public void testDemo3(String test)  {
        logger.info("Test3: "+test);
        logger.info("Count: "+String.valueOf(++count));
    }

    @Test
    public void testDemoTag() {
        logger.info("Only for a given tag");
        logger.info("Count: "+String.valueOf(++count));
    }


    @AfterEach
    public void teardownEach(){
        logger.info("AfterEach teardown");
    }

}
