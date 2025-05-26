package hoanvt.librarymanagementmain.service;

import hoanvt.librarymanagementmain.dto.UserRequestDTO;
import hoanvt.librarymanagementmain.dto.UserResponseDTO;
import hoanvt.librarymanagementmain.dto.UserSearchRequestDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userDto);

    UserResponseDTO getUserById(Long id);

    UserResponseDTO updateUser(Long id, UserRequestDTO userDto);

    void deleteUser(Long id);

    List<UserResponseDTO> getAllUsers();

    Page<UserResponseDTO> searchUsers(UserSearchRequestDTO requestDTO);
}