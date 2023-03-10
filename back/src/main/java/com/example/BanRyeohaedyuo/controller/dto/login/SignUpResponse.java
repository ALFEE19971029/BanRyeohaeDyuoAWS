package com.example.BanRyeohaedyuo.controller.dto.login;

import com.example.BanRyeohaedyuo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignUpResponse {

    private String userName;

    public static SignUpResponse of(User user){
        return new SignUpResponse(user.getUserName());
    }

}
