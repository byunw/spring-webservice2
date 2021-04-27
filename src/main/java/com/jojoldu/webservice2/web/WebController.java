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

    @GetMapping("")
    public String show_home(Model model){

        model.addAttribute("posts",postrepository.findAll());
        return "page/home";

    }

    @GetMapping("/delete/{id}")
    public String deletepost(@PathVariable Long id){
        postrepository.deleteById(id);
        return "deleteresultpage";
    }

    @GetMapping("/writepost")
    public String write_post(HttpSession session){

        Object sessionedUser=session.getAttribute("sessioneduser");

        if(sessionedUser==null){
            return "page/loginpage";
        }

        return "page/writepostpage";
    }

    @PostMapping("/savepost")
    public String save_post(String title,String content,HttpSession session){
        //                           사용자가 친 제목, 사용자가 친 게시글 내용,User object
        postrepository.save(new Posts(title,content,(User)session.getAttribute("sessioneduser")));
        return "redirect:/";
    }
    
    @GetMapping("/postdetail/{id}")
    public String show_detail(@PathVariable Long id,Model model){

        model.addAttribute("post",postrepository.getOne(id));
        return "page/showdetail";

    }

}
