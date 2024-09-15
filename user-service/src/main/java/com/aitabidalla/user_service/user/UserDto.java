package com.aitabidalla.user_service.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDto {
    public long id ;
    public String userName;
}
