package com.jojoldu.webservice2.web;

import com.jojoldu.webservice2.domain.posts.PostsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
public class WebController{

    private PostsRepository postrepository;

    @GetMapping("/showtitle")
    public String showtitle(Model model){
        model.addAttribute("posts",postrepository.findAll());
        return "showtitle2";
    }

    //when localhost:8080/delete is the browser, the Post object with Id value 1 is deleted from Posts table
    @GetMapping("/delete/{id}")
    public String deletepost(@PathVariable Long id){
        postrepository.deleteById(id);
        return "deleteresultpage";
    }

    @GetMapping("")
    public String show_home(){
        return "home";
    }

    @GetMapping("/users/form")
    public String form(){
        return "loginform";
    }


}
