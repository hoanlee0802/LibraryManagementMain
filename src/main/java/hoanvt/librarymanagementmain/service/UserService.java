package hoanvt.librarymanagementmain.service;

import hoanvt.librarymanagementmain.dto.UserRequestDTO;
import hoanvt.librarymanagementmain.dto.UserResponseDTO;
import hoanvt.librarymanagementmain.dto.UserSearchRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userDto);

    UserResponseDTO getUserById(Long id);

    UserResponseDTO updateUser(Long id, UserRequestDTO userDto);

    void deleteUser(Long id);

    List<UserResponseDTO> getAllUsers();

    Page<UserResponseDTO> searchUsers(UserSearchRequestDTO requestDTO);

    void changePassword(Long userId, String rawPassword);

    void assignRoleGroupsToUser(Long id, Set<Long> roleGroupIds);


}