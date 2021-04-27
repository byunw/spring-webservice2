package com.jojoldu.webservice2.web;

import com.jojoldu.webservice2.domain.Users.User;
import com.jojoldu.webservice2.domain.posts.Posts;
import com.jojoldu.webservice2.domain.posts.PostsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class WebController{

    private PostsRepository postrepository;


    @GetMapping("/delete/{id}")
    public String deletepost(@PathVariable Long id){
        postrepository.deleteById(id);
        return "deleteresultpage";
    }

    //show_home은 Posts(table)에 존재하는 entity들을 homepage에 보여주는 코드
    @GetMapping("")
    public String show_home(Model model){

        model.addAttribute("posts",postrepository.findAll());
        return "page/home";

    }

    @GetMapping("/writepost")
    public String write_post(HttpSession session){

        //check if the current user has logged
            //if the user has logged in, show a Post write up page
            //if the user has not logged in, tell the user to login first
        Object sessionedUser=session.getAttribute("sessioneduser");

        if(sessionedUser==null){
            return "page/loginpage";
        }

        return "page/writepostpage";
    }

    @PostMapping("/savepost")
    public String save_post(String title,String content,HttpSession session){

        postrepository.save(new Posts(title,content,(User)session.getAttribute("sessioneduser")));
        return "redirect:/";
        

    }



}
