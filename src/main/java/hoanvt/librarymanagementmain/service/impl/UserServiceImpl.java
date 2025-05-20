package hoanvt.librarymanagementmain.service.impl;

import hoanvt.librarymanagementmain.dto.UserRequestDTO;
import hoanvt.librarymanagementmain.dto.UserResponseDTO;
import hoanvt.librarymanagementmain.entity.User;
import hoanvt.librarymanagementmain.repository.UserRepository;
import hoanvt.librarymanagementmain.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Date;
import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFullname(user.getFullName());
        dto.setPhoneNumber(user.getPhone());
        dto.setIdentityNumber(user.getIdentityNumber());
        dto.setAge(user.getAge());
        dto.setBirthday(user.getBirthday());
        dto.setAddress(user.getAddress());
        return dto;
    }

    private User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setFullName(dto.getFullname());
        user.setPhone(dto.getPhoneNumber());
        user.setIdentityNumber(dto.getIdentityNumber());
        user.setAge(dto.getAge());
        user.setBirthday(dto.getBirthday());
        user.setAddress(dto.getAddress());
        return user;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = toEntity(dto);
        user = userRepository.save(user);
        return toResponseDTO(user);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.map(this::toResponseDTO).orElse(null);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // update all fields except id
            user.setUsername(dto.getUsername());
            user.setPassword(dto.getPassword());
            user.setFullName(dto.getFullname());
            user.setPhone(dto.getPhoneNumber());
            user.setIdentityNumber(dto.getIdentityNumber());
            user.setAge(dto.getAge());
            user.setBirthday(dto.getBirthday());
            user.setAddress(dto.getAddress());
            user = userRepository.save(user);
            return toResponseDTO(user);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}