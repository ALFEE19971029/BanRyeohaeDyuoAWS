package com.example.BanRyeohaedyuo.service;

import com.example.BanRyeohaedyuo.controller.dto.login.SignInRequest;
import com.example.BanRyeohaedyuo.controller.dto.login.SignInResponse;
import com.example.BanRyeohaedyuo.controller.dto.login.SignUpRequest;
import com.example.BanRyeohaedyuo.controller.dto.login.SignUpResponse;
import com.example.BanRyeohaedyuo.domain.User;
import com.example.BanRyeohaedyuo.exception.InvalidArgumentException;
import com.example.BanRyeohaedyuo.repository.UserRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private boolean isValidUserName(String userName){
        if(userRepository.existsByUserName(userName)) {
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String passwordCheck) {
        if(password.equals(passwordCheck)) {
            return true;
        }
        return false;
    }


    public SignUpResponse singUp(SignUpRequest request){

        //1. 중복체크
        if(!isValidUserName(request.getUserName())){
            throw new InvalidArgumentException("중복된 이름입니다.");
        }
        //2. 비밀번호 체크
        if(!isValidPassword(request.getPassword(), request.getPasswordChecked())){
            throw new InvalidArgumentException("비밀번호가 다릅니다.");
        }
        User newUser = User
            .builder()
            .userName(request.getUserName())
            .password(request.getPassword())
            .build();
        return SignUpResponse.of(userRepository.save(newUser));
    }

    public SignInResponse signIn(SignInRequest request){

        //1. 아이디 확인
        User user = userRepository.findUserByUserName(request.getUserName()).orElse(null);
        if(user==null){
            throw new InvalidArgumentException("존재하지 않는 아이디입니다.");
        }
        //2. 비밀번호 확인

        if(!request.getPassword().equals(user.getPassword())){
            throw new InvalidArgumentException("잘못된 비밀번호 입니다.");
        }
        return SignInResponse.of(userRepository.save(user),"asdfasdf","asdfasdf");
    }
}
