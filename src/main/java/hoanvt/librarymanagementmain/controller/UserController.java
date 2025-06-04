package hoanvt.librarymanagementmain.controller;

import hoanvt.librarymanagementmain.config.TokenProvider;
import hoanvt.librarymanagementmain.dto.*;
import hoanvt.librarymanagementmain.dto.ApiResponse;
import hoanvt.librarymanagementmain.model.AuthToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import hoanvt.librarymanagementmain.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody UserLoginRequestDTO loginUser) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getUsername(),
                            loginUser.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenUtil.generateToken(authentication);

            // Chuẩn hóa response (nếu bạn dùng cấu trúc code/message/data)
            ApiResponse<AuthToken> response = ApiResponse.success(
                    new AuthToken(token)
            );
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            // Sai username/password
            ApiResponse<Object> response = ApiResponse.error("auth.failed", "Username or password is incorrect.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (Exception ex) {
            ApiResponse<Object> response = ApiResponse.error("auth.error", "Authentication failed.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/adminping", method = RequestMethod.GET)
    public String adminPing() {
        return "Only Admins Can Read This";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/userping", method = RequestMethod.GET)
    public String userPing() {
        return "Any User Can Read This";
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userDto) {
        UserResponseDTO createdUser = userService.createUser(userDto);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userDto) {
        UserResponseDTO updatedUser = userService.updateUser(id, userDto);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/{id}/assign-roles")
    public ResponseEntity<?> assignRoleGroupsToUser(
            @PathVariable Long id,
            @RequestBody AssignRoleGroupsRequestDTO request) {
        userService.assignRoleGroupsToUser(id, request.getRoleGroupIds());
        return ResponseEntity.ok("Role groups assigned successfully");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        if (token == null || !jwtTokenUtil.validateToken(token, null)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        // If you have a blacklist mechanism, add the token to the blacklist here.
        // jwtTokenUtil.invalidateToken(token); // Uncomment and implement if supported

        return ResponseEntity.ok("Logged out successfully");
    }

    // Utility method to extract Bearer token from Authorization header
    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }


//    @PostMapping("/change-password")
//    public ResponseEntity<?> changePassword() {
//        ArrayList<Long> userIds = new ArrayList<>();
//        userIds.add(1L);
//        userIds.add(4L);
//        userIds.add(5L);
//        userIds.add(6L);
//        userIds.add(7L);
//        userIds.add(8L);
//        userIds.add(9L);
//        for (Long i : userIds) {
//            userService.changePassword(i, "newPassword123");
//        }
//        return ResponseEntity.ok("Password updated successfully!");
//    }
}
