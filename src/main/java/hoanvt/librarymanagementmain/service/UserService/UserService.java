package hoanvt.librarymanagementmain.service.UserService;

import hoanvt.librarymanagementmain.dto.UserRequestDTO;
import hoanvt.librarymanagementmain.dto.UserResponseDTO;
import hoanvt.librarymanagementmain.entity.User;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userDto);

    UserResponseDTO getUserById(Long id);

    UserResponseDTO updateUser(Long id, UserRequestDTO userDto);

    void deleteUser(Long id);

    List<UserResponseDTO> getAllUsers();
}