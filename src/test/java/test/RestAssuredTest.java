package test;

import api.testing.Request;
import api.testing.RestAssuredClient;
import api.testing.models.AddProduct;
import api.testing.models.ItemsItem;
import api.testing.models.UserCredentials;
import api.testing.models.ViewCartResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import us.abstracta.jmeter.javadsl.core.DslThreadGroup;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;
import us.abstracta.jmeter.javadsl.http.DslHttpSampler;

import java.time.Instant;
import java.util.UUID;

import static api.testing.RestAssuredClient.getRequestFullPath;
import static io.restassured.http.Method.POST;
import static org.eclipse.jetty.http.MimeTypes.Type.APPLICATION_JSON;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

@Slf4j
public class RestAssuredTest {
    RestAssuredClient restAssuredClient;
    ObjectMapper objectMapper;
    Response response;
    String cookie;

    @BeforeMethod
    public void prepareData() {
        restAssuredClient = new RestAssuredClient();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module())
                .setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
    }

    @Test
    @SneakyThrows
    public void addProduct() {
        UserCredentials userCredentials = UserCredentials.builder()
                .username("Tamas")
                .password("dGFtYXM=")
                .build();
        String loginBody = objectMapper.writeValueAsString(userCredentials);

        Request loginRequest = Request.builder()
                .method(POST)
                .endpoint("/login")
                .body(loginBody)
                .build();
        response = restAssuredClient.sendRequest(loginRequest);
        cookie = getCookieFromResponse(response);

        /**
         * Partea a doua
         */

        AddProduct addProduct = AddProduct.builder()
                .id(UUID.randomUUID().toString())
                .cookie(cookie)
                .prodId(9)
                .flag(true)
                .build();

        String addProductBody = objectMapper.writeValueAsString(addProduct);

        Request addProductRequest = Request.builder()
                .method(POST)
                .endpoint("/addtocart")
                .body(addProductBody)
                .build();

        response = restAssuredClient.sendRequest(addProductRequest);
        log.info(response.getBody().prettyPrint());

//        DslHttpSampler post =
//                httpSampler(getRequestFullPath(addProductRequest))
//                        .post(preProcessorVars -> {
//                            AddProduct product = AddProduct.builder()
//                                    .id(UUID.randomUUID().toString())
//                                    .cookie(cookie)
//                                    .prodId(9)
//                                    .flag(true)
//                                    .build();
//                            try {
//                                return objectMapper.writeValueAsString(product);
//                            } catch (JsonProcessingException e) {
//                                e.printStackTrace();
//                            }
//                            return null;
//                        }, APPLICATION_JSON);
//
//        DslThreadGroup dslThreadGroup =
//                threadGroup(5, 5, post,
//                        influxDbListener("http://localhost:8086/write?db=jmeter"),
//                        jtlWriter("test" + Instant.now().toString().replace(":", "-") + ".jtl")
//                );
//        TestPlanStats stats = testPlan(dslThreadGroup).run();

        /**
         * partea 3
         */
        AddProduct viewCart = AddProduct.builder()
                .cookie(cookie)
                .flag(true)
                .build();

        String viewCartBody = objectMapper.writeValueAsString(viewCart);

        Request viewCartRequest = Request.builder()
                .method(POST)
                .endpoint("/viewcart")
                .body(viewCartBody)
                .build();

        response = restAssuredClient.sendRequest(viewCartRequest);
        log.info(response.getBody().prettyPrint());

        ViewCartResponse viewCartResponse = response.as(ViewCartResponse.class);

        viewCartResponse.getItems().stream()
                .map(itemsItem -> itemsItem.getId())
                .forEach(id -> deleteItemRequest(id));
    }

    @SneakyThrows
    private void deleteItemRequest(String productId)  {
        ItemsItem deleteItem = ItemsItem.builder()
                .id(productId)
                .build();

        String deleteItemBody = objectMapper.writeValueAsString(deleteItem);

        Request deleteItemRequest = Request.builder()
                .method(POST)
                .endpoint("/deleteitem")
                .body(deleteItemBody)
                .build();

        response = restAssuredClient.sendRequest(deleteItemRequest);
        log.info(response.getBody().prettyPrint());
    }

    String getCookieFromResponse(Response response) {
        return response.getBody().prettyPrint().trim()
                .replaceAll("\"", "")
                .split(" ")[1];
    }
}