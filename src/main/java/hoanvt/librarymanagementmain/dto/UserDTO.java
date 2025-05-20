package hoanvt.librarymanagementmain.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


@Data
public class UserDTO {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private String identityNumber;
    private Integer age;
    private Date birthday;
    private String address;
    private Boolean isActive;
    // Add other fields as needed
}