package com.jojoldu.webservice2.web;

import com.jojoldu.webservice2.domain.Users.User;
import com.jojoldu.webservice2.domain.Users.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserController{

    private UsersRepository usersRepository;

    @PostMapping("/create")
    public String create_save(User user){

        //사용자가 회원가입페이지에서 입력한 데이터를 콘솔에 찍어주는 놈
        //System.out.println("User info: "+user);
        //사용자가 입력한 정보가 USER table에 저장시키는 코드
        usersRepository.save(user);
        return "userdatasave";

    }

}


