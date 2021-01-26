package test;

import api.testing.RestAssuredClient;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RestAssuredTest {
    RestAssuredClient restAssuredClient;

    @BeforeMethod
    public void prepareData() {
        restAssuredClient = new RestAssuredClient();
    }

    @Test
    public void addProduct() {
    }
}