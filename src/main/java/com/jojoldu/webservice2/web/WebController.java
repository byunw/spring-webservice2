package com.jojoldu.webservice2.web;

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

        //로그인 하지않은 경우: ex, 홈페이지에서 바로 게시글 작성 버튼누르는 경우
        if(sessionedUser==null){
            return "page/loginpage";
        }

        //로그인 성공한경우(회원가입할떄 작성한 사용자아이디/패스워드를 로그인시 작성한경우),게시글 작성 버튼눌렀을때 게시글 작성 페이지 보여주기
        return "page/writepostpage";
    }

    @PostMapping("/savepost")
    public String save_post(Posts post){

        postrepository.save(post);
        return "redirect:/";


    }



}
