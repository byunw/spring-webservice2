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


    @PostMapping("/create")
    public String create_save(User user){

        try{
            usersRepository.save(user);
        }

        catch (Exception e){
            return "page/trydifferentid";
        }

        return "redirect:/";

    }
    
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

        //case 1: 입력한 패스워드를 값으로 가지는 entity는 User(table)하나 입력한 사용자아이디값을 가지는 entity는 존재하지않을경우
        //case 2: 입력한 패스워드 값을 가지는 entity도 없고 입력한 사용자아이디값을 가지는 entity도 없는경우
        if(user==null){
            return "page/idnotexist";
        }

        if(user.getPassword().equals(password)){
            session.setAttribute("sessioneduser",user);
            return "redirect:/";
        }

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


}


