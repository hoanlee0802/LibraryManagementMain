package hoanvt.librarymanagementmain.dto;

import lombok.Data;
import java.util.Date;

@Data
public class UserRequestDTO {
    private String username;
    private String password;
    private String fullname;
    private String phoneNumber;
    private String identityNumber;
    private Integer age;
    private Date birthday;
    private String address;
    private Integer IsActive;
    private Integer IsDeleted;
}