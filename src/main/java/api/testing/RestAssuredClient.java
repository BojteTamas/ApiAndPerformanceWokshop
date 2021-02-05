package api.testing;

import com.google.inject.Provider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static java.util.Optional.ofNullable;

public class RestAssuredClient {
    private final Provider<RequestSpecification> requestSpecification;

    public RestAssuredClient() {
        RestAssured.registerParser("text/html", Parser.JSON);
        RestAssured.baseURI = "https://api.demoblaze.com";
        requestSpecification = RestAssured::given;
    }

    public Response sendRequest(Request request) {
        Response response;
        RequestSpecification specification =
                requestSpecification.get()
                        .contentType(ContentType.JSON);

        if (ofNullable(request.getBody()).isPresent()) {
            specification = specification.body(request.getBody());
        }

        switch (request.getMethod()) {
            case POST:
                response = specification.post(request.getEndpoint()).then().extract().response();
                break;
            case GET:
                response = specification.get(request.getEndpoint()).then().extract().response();
                break;
            default:
                throw new IllegalArgumentException("No method type found");
        }
        return response;
    }

    public static String getRequestFullPath(Request request) {
        return RestAssured.baseURI + RestAssured.basePath + request.getEndpoint();
    }
}
