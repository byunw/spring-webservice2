package com.jojoldu.webservice2.web;

import com.jojoldu.webservice2.domain.Users.User;
import com.jojoldu.webservice2.domain.Users.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserController{

    private UsersRepository usersRepository;

    @GetMapping("/users/form")
    public String register_form(){
        return "registerform";
    }

    @PostMapping("/create")
    public String create_save(User user){
        usersRepository.save(user);
        return "userdatasave";
    }

    @GetMapping("/loginpage")
    public String show_loginpage(){
        return "loginpage";
    }

    @PostMapping("/login")
    public String login(String userId,String password){

        //로그인 정보(사용자아이디,비빌번호)가 회원가입할때 기입한 정보랑 일치하면 로그인 시켜주기
        User user=usersRepository.findByUserId(userId);

        //사용자가 로그인시 타입한 아이디가 데이터베이스에 존재하지않는 경우
        //사용자가 아이디와 패스워드가 둘다 데이터베이스에 존재하지않는 경우
        //로그인시 타입한 사용자 아이디를 가지는 entity는 존재하지않지만 로그인시 입력한 패스워드를 가지는 entity는 존재하는경우
            //발생원인 1: 회원가입 건너뛰고 바로 로그인하는경
            //발생원인 2: 회원가입은했지만 아이디를 잘못기억하는경우
        if(user==null){
            return "idnotexist";
        }

        //사용자가 로그인할때 친 사용자아이디와 패스워드를 가지는 entity가 데이터베이스에 존재하는경우
        //회원가입을했기때문에 로그인시 치는 사용자아이디/비빌번호 값을 가진 entity가 존재

        //회원가입한 사용자아이디/패스워드가 로그인시 입력된경우
        if(user.getPassword().equals(password)){
            return "loginsuccessful";
        }

        //회원가입한 사용자아이디인데 해당아이디 비빌번호랑 다른경우
        if(!user.getPassword().equals(password)){
            return "retypepassword";
        }

        //this line of code to get rid of missing return statement error
        //for the other cases except the above 3 cases, this method returns ""
        return "";

    }





}


