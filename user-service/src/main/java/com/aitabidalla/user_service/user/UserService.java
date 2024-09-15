package com.aitabidalla.user_service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto saveUser(UserEntity user)
    {
        UserEntity checkUser = userRepository.findByUserName(user.getUserName());
        if(checkUser != null)
        {
            throw new RuntimeException("user already exists with the same username");
        }
        UserEntity savedUser = userRepository.save(user);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(savedUser, userDto);
        return userDto;
    }

    public String getUserNameById(long id)
    {
        UserDto userDto = new UserDto();
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("no user found")).getUserName();


    }


}
