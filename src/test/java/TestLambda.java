import com.monkgirl.java8inaction.chapter3.Lambda;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author powersi
 * @version 0.1
 * @since 2021-03-16 11:05:10
 */
class TestLambda {

    @BeforeAll
    static void beforeAll() {
        System.out.println("==========Junit5 Test begin==========");
    }

    @Test
    void testRunnable() {
        Lambda.process(() -> System.out.println("Let's Go!"));
    }

    @Test
    void testFile() throws IOException {
        //String readLine = Lambda.processFile(BufferedReader::readLine);
        String readLine = Lambda.processFile((BufferedReader reader) -> reader.readLine() + "\n" + reader.readLine());
        System.out.println(readLine);
    }

    @Test
    void testSort(){
        List<String> list = Arrays.asList("r", "B", "t", "s", "F", "c");
        System.out.println("Before: " + list.toString());
        Lambda.sort(list);
        System.out.println("After: " + list.toString());
    }

    @Test
    void testMethodReference(){
        Lambda.methodReference();
    }

    @AfterAll
    static void afterAll() {
        System.out.println("===========Junit5 Test end===========");
    }
}
