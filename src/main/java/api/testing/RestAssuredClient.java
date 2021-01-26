package api.testing;

import com.google.inject.Provider;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static java.util.Optional.ofNullable;

public class RestAssuredClient {
    private final Provider<RequestSpecification> requestSpecification;
    RestAssuredConfig config =
            RestAssured.config()
                    .httpClient(
                            HttpClientConfig.httpClientConfig()
                                    .setParam("http.connection.timeout", 60000)
                                    .setParam("http.socket.timeout", 60000));

    public RestAssuredClient() {
        RestAssured.registerParser("text/html", Parser.JSON);
        RestAssured.baseURI = "https://api.demoblaze.com";
        requestSpecification = RestAssured::given;
    }

    public Response sendRequest(Request request) {
        RequestSpecification specification =
                requestSpecification.get()
                        .contentType(ContentType.JSON)
                        .config(config);
        Response response;
        if (ofNullable(request.getBody()).isPresent()) {
            specification = specification.body(request.getBody());
        }

        switch (request.getMethod()) {
            case POST:
                response = specification.log().all().post(request.getEndpoint()).then().extract().response();
                break;
            case GET:
                response = specification.get(request.getEndpoint()).then().extract().response();
                break;
            case PUT:
                response = specification.put(request.getEndpoint()).then().extract().response();
                break;
            case HEAD:
                response = specification.head(request.getEndpoint()).then().extract().response();
                break;
            case OPTIONS:
                response = specification.options(request.getEndpoint()).then().extract().response();
                break;
            case PATCH:
                response = specification.patch(request.getEndpoint()).then().extract().response();
                break;
            case DELETE:
                response = specification.delete(request.getEndpoint()).then().extract().response();
                break;
            default:
                throw new IllegalArgumentException("No method type found");
        }
        return response;
    }
}
