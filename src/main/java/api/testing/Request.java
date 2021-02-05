package api.testing;

import io.restassured.http.Method;
import lombok.*;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Builder(toBuilder = true, builderClassName = "builder")
public class Request {
    Method method;
    String endpoint;
    String body;
}