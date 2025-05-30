package hoanvt.librarymanagementmain.service.impl;

import hoanvt.librarymanagementmain.dto.UserRequestDTO;
import hoanvt.librarymanagementmain.dto.UserResponseDTO;
import hoanvt.librarymanagementmain.dto.UserSearchRequestDTO;
import hoanvt.librarymanagementmain.service.UserService;
import hoanvt.librarymanagementmain.entity.User;
import hoanvt.librarymanagementmain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


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
        dto.setIsDeleted(user.getIsDeleted());
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
        user.setIsDeleted(dto.getIsDeleted());
        return user;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user = userRepository.save(user);
        return toResponseDTO(user);

    }

    public void updatePassword(Long userId, String newPassword) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
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
            user.setIsDeleted(dto.getIsDeleted());
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

    @Override
    public Page<UserResponseDTO> searchUsers(UserSearchRequestDTO requestDTO) {
        Pageable pageable = PageRequest.of(requestDTO.getPage(), requestDTO.getSize());
        return userRepository.search(
                requestDTO.getUsername(),
                requestDTO.getEmail(),
                pageable
        ).map(this::toResponseDTO);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoleGroups().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleGroupName()));
        });
        return authorities;
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}