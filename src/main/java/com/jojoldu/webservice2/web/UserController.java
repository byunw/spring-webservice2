package com.jojoldu.webservice2.web;

import com.jojoldu.webservice2.domain.Users.User;
import com.jojoldu.webservice2.domain.Users.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class UserController{

    private UsersRepository usersRepository;

    //회원가입기능
    @PostMapping("/create")
    public String create_save(User user){

        try{
            usersRepository.save(user);
        }

        //이미 다른사람이 사용한 사용자아이디로 회원가입하려면 에러뜰고
        //catch부분안에있는 return "page/trydifferentid"이 실행이됨

        catch (Exception e){
            return "page/trydifferentid";
        }

        return "redirect:/";

    }

    //회원가입페이지 보여줌
    @GetMapping("/users/form")
    public String register_form(){
        return "page/registerform";
    }

    @GetMapping("/loginpage")
    public String show_loginpage(){
        return "page/loginpage";
    }

    @PostMapping("/login")
    public String login(String userId,String password,HttpSession session){

        User user=usersRepository.findByUserId(userId);

        //로그인할때 타입한 사용자아이디가 user table에 존재하지않는경우
        if(user==null){
            return "page/idnotexist";
        }

        //해당사용자아이디의 패스워드랑 로그인할당시에 친 패스워드랑 같은경우
        if(user.getPassword().equals(password)){
            session.setAttribute("sessioneduser",user);
            return "redirect:/";
        }

        //해당 사용자아이디의 비빌번호랑 유저가 친 비빌번호가 다를경우
        if(!user.getPassword().equals(password)){
            return "page/retypepassword";
        }

        return "";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){

        session.removeAttribute("sessioneduser");
        return "redirect:/";

    }

    @GetMapping("/test")
    public String show(){
        return "page/test";
    }


}


