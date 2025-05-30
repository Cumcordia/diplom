package com.bazarweb.bazarweb.controller.User;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bazarweb.bazarweb.dto.UserDTO;
import com.bazarweb.bazarweb.service.Admin.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/users")
//@PreAuthorize("hasRole('ADMIN')") // Ограничение доступа только для администраторов
@RequiredArgsConstructor
public class UserAdminController {

    private final AdminService adminService;

    // Получение списка всех пользователей
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Получение информации о пользователе по ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        UserDTO user = adminService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Удаление пользователя
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Изменение роли пользователя
    @PatchMapping("/{id}/role")
    public ResponseEntity<UserDTO> updateUserRole(@PathVariable int id, @RequestParam String role) {
        UserDTO updatedUser = adminService.updateUserRole(id, role);
        return ResponseEntity.ok(updatedUser);
    }
}
