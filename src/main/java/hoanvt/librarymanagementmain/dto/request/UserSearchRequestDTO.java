package hoanvt.librarymanagementmain.dto.request;

import lombok.Data;

@Data
public class UserSearchRequestDTO {
    private int page = 0;
    private int size = 10;
    private String username;
    private String email;
}