package com.aitabidalla.user_service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public UserDto createUser(@RequestBody UserEntity user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{userId}")
    public String getUserNameOfUser(@PathVariable Long userId) {
        return userService.getUserNameById(userId);
    }

}
