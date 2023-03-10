package com.example.BanRyeohaedyuo.controller;

import com.example.BanRyeohaedyuo.common.ResponseHandler;
import com.example.BanRyeohaedyuo.controller.dto.login.SignInRequest;
import com.example.BanRyeohaedyuo.controller.dto.login.SignUpRequest;
import com.example.BanRyeohaedyuo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody SignUpRequest request){
        return ResponseHandler.generateResponse("회원가입이 완료되었습니다.", HttpStatus.OK,userService.singUp(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Object> singIn(@RequestBody SignInRequest request){
        return ResponseHandler.generateResponse("로그인이 완료되었습니다.", HttpStatus.OK,userService.signIn(request));
    }

}
