package hoanvt.librarymanagementmain.service;

import hoanvt.librarymanagementmain.dto.request.UserRequestDTO;
import hoanvt.librarymanagementmain.dto.response.UserResponseDTO;
import hoanvt.librarymanagementmain.dto.request.UserSearchRequestDTO;
import org.springframework.data.domain.Page;

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