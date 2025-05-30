package com.bazarweb.bazarweb.service.Admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bazarweb.bazarweb.dto.UserDTO;
import com.bazarweb.bazarweb.enums.UserRole;
import com.bazarweb.bazarweb.exception.UserNotFoundException;
import com.bazarweb.bazarweb.model.User.User;
import com.bazarweb.bazarweb.repository.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    /**
     * Получить всех пользователей.
     */
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail(), null, null, user.getRole(), null, null))
                .toList();
    }

    /**
     * Получить пользователя по ID.
     */
    public UserDTO getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с ID " + id + " не найден"));
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), null, null, user.getRole(), null, null);
    }

    /**
     * Удалить пользователя по ID.
     */
    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Пользователь с ID " + id + " не найден");
        }
        userRepository.deleteById(id);
    }

    /**
     * Обновить роль пользователя.
     */
    public UserDTO updateUserRole(int id, String role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с ID " + id + " не найден"));
        user.setRole(UserRole.valueOf(role));
        userRepository.save(user);
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), role, null, user.getRole(), null, null);
    }
}
