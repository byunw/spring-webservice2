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


