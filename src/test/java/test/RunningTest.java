package test;

import api.testing.Request;
import api.testing.RestAssuredClient;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Slf4j
public class RunningTest {
    RestAssuredClient restAssuredClient;

    @BeforeMethod
    public void prepareData() {
        restAssuredClient = new RestAssuredClient();
    }

    @Test
    public void testingTest() {
        Request request = Request.builder().build();
    }
}