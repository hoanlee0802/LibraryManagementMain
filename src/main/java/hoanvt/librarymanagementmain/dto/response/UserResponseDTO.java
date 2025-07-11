package hoanvt.librarymanagementmain.dto.response;

import lombok.Data;
import java.util.Date;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String fullname;
    private String phoneNumber;
    private String identityNumber;
    private Integer age;
    private Date birthday;
    private String address;
    private Integer IsActive;
    private Integer IsDeleted;
}