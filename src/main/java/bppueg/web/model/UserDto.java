package bppueg.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @Null
    private UUID id;
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
