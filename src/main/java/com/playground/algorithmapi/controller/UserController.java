package com.playground.algorithmapi.controller;

import com.playground.algorithmapi.entity.User;
import com.playground.algorithmapi.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理控制器
 */
@Log4j2
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "用户管理", description = "用户相关接口")
public class UserController {

    private final IUserService userService;

    @Operation(summary = "获取所有用户")
    @GetMapping
    public List<User> getAllUsers() {
        log.info("获取所有用户列表");
        return userService.list();
    }

    @Operation(summary = "根据ID获取用户")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        log.info("根据ID获取用户: {}", id);
        return userService.getById(id);
    }

    @Operation(summary = "创建用户")
    @PostMapping
    public boolean createUser(@RequestBody User user) {
        log.info("创建用户: {}", user);
        return userService.save(user);
    }

    @Operation(summary = "更新用户")
    @PutMapping("/{id}")
    public boolean updateUser(@PathVariable Long id, @RequestBody User user) {
        log.info("更新用户: ID={}, User={}", id, user);
        user.setId(id);
        return userService.updateById(user);
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        log.info("删除用户: {}", id);
        return userService.removeById(id);
    }
}
