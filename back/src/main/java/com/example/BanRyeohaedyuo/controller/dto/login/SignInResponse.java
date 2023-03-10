package com.example.BanRyeohaedyuo.controller.dto.login;

import com.example.BanRyeohaedyuo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignInResponse {

    private String userName;

    private String authToken;

    private String refreshToken;

    public static SignInResponse of(User user,String authToken, String refreshToken){
        return new SignInResponse(user.getUserName(),authToken,refreshToken);
    }

}
