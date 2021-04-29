package com.jojoldu.webservice2.web;

import com.jojoldu.webservice2.domain.posts.PostsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class PostController{

    private PostsRepository postrepository;

    //
    //    @DeleteMapping("/{id}")
    //    public String delete(@PathVariable Long id, Model model, HttpSession session){
    //
    //
    //        Question question=questionRepository.findById(id).get();
    //        Result result=valid(session,question);
    //        if(!result.isValid()) {
    //            model.addAttribute("errorMessage",result.getErrorMessage());
    //            return "/user/login";
    //
    //        }
    //
    //        questionRepository.deleteById(id);
    //
    //
    //        return "redirect:/";
    //
    //
    //
    //    }



}
