package test;

import api.testing.Request;
import api.testing.RestAssuredClient;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Slf4j
public class RunningTest {
    RestAssuredClient restAssuredClient;

    @BeforeMethod
    public void prepareData() {
        restAssuredClient = new RestAssuredClient();
        new ObjectMapper().registerModule(new Jdk8Module())
                .setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
    }

    @Test
    public void testingTest() {
        Request request = Request.builder().build();
    }
}