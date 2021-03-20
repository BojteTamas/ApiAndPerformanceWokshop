package api.testing.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Builder(toBuilder = true, builderClassName = "builder")
public class ItemsItem {
    String cookie;
    String id;
    @JsonProperty("prod_id")
    int prodId;
}