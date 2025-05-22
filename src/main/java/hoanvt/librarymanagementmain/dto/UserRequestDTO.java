package hoanvt.librarymanagementmain.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.Date;

@Data
public class UserRequestDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String fullname;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String identityNumber;

    @NotNull
    @Min(0)
    private Integer age;

    @NotNull
    private Date birthday;

    @NotBlank
    private String address;

    @NotNull
    @Min(0)
    @Max(1)
    private Integer IsActive;

    @NotNull
    @Min(0)
    @Max(1)
    private Integer IsDeleted;
}