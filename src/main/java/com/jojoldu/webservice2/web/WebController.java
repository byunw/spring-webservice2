package com.jojoldu.webservice2.web;

import com.jojoldu.webservice2.domain.Users.User;
import com.jojoldu.webservice2.domain.posts.Posts;
import com.jojoldu.webservice2.domain.posts.PostsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

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

    //equals() is used to check the value of a variable

    @PostMapping("/savepost")
    public String save_post(String title,String content,HttpSession session){

        if((!title.equals("")) && (!content.equals(""))){

           Posts currentpost=new Posts(title,content,(User)session.getAttribute("sessioneduser"));
           LocalDate now=LocalDate.now();
           currentpost.setCreatedDate(now);
           currentpost.setModifiedDate(now);
           postrepository.save(currentpost);
           return "redirect:/";

        }

        else{
            return "page/pleasetypesth";
        }

    }

    @GetMapping("/postdetail/{id}")
    public String show_detail(@PathVariable Long id,Model model,HttpSession session){

        model.addAttribute("post",postrepository.getOne(id));
        Posts post=postrepository.getOne(id);
        Object loginuser=session.getAttribute("sessioneduser");

        if(loginuser==null){
            return "page/showdetail_notlogin";
        }

        else{

            User User_Object=(User) loginuser;

            if(post.getAuthor().getUserId().equals(User_Object.getUserId())){
                return "page/showdetailself";
            }

            else{
                return "page/deleteotherpost";
            }

        }

    }

    @DeleteMapping("/deletepost/{id}")
    public String delete_post(@PathVariable Long id){
        postrepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/modifyform/{id}")
    public String show_updatepage(@PathVariable Long id,Model model){
        model.addAttribute("post",postrepository.getOne(id));
        return "page/modifypost";
    }

    @PutMapping("/updatepost/{id}")
    public String update_post(@PathVariable Long id,String title,String contents,Model model){

        if((!title.equals("")) && (!contents.equals(""))){
            Posts post=postrepository.getOne(id);
            post.setTitle(title);
            post.setContent(contents);
            postrepository.save(post);
            return "redirect:/";
        }
        
        model.addAttribute("post",postrepository.getOne(id));
        return "page/fillbothfields";

    }


}
