package hoanvt.librarymanagementmain.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;


public class Utils {
    public static <T> ResponseEntity<?> success(T data) {
        return ResponseEntity.ok(new Response<>("SUCCESS", "Thành công", data));
    }

    public static <T> ResponseEntity<?> error(String code, String message) {
        return ResponseEntity.badRequest().body(new Response<>(code, message, null));
    }
}