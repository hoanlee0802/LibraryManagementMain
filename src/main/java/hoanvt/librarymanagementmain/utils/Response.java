package hoanvt.librarymanagementmain.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
public class Response<T> {
    private String code;
    private String message;
    private T data;
}
