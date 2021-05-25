package lombok;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreateUpdateLogin {
    @JsonIgnore
    private String name;
    @JsonIgnore
    private Integer id;
    private String email;
    private  String createdAt;
}