package com.jojoldu.webservice2.web;

import com.jojoldu.webservice2.domain.Comment.Comment;
import com.jojoldu.webservice2.domain.Comment.CommentRepository;
import com.jojoldu.webservice2.domain.Users.User;
import com.jojoldu.webservice2.domain.posts.Posts;
import com.jojoldu.webservice2.domain.posts.PostsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

//@Controller is indicating that WebController class serves the role of a controller
//@AllArgsConstructor generates a constructor with parameter for each field in the class
    //example
        //@AllArgsConstructor
        //public class Person{
        //      private String firstname;
        //      private String lastname;
        //}
        //constructor generated b/c of @AllArgsContructor
        //public Person(String firstname,String lastname){
        //      this.firstname=firstname;
        //      this.lastname=lastname;
        //}
//since WebController class is Controller class and not have fields this class should not use @AllArgsConstructor
@Controller
public class WebController{
    

    private PostsRepository postrepository;
    private CommentRepository commentrepository;

    @GetMapping("")//@GetMapping is same as @RequestMapping(method=RequestMethod.GET)
    public String show_home(Model model){
        model.addAttribute("posts",postrepository.findAll());
        return "page/home";
    }

    @DeleteMapping("/deletepost/{id}")
    public String delete_post(@PathVariable Long id){

        commentrepository.DeleteAssociatedComment(id);
        postrepository.deleteById(id);
        return "redirect:/";

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

        //게시글 작성 페이지에 제목값이랑 내용값이 둘다있을경우
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

    @PostMapping("/savecomment/{id}")
    public String save_comment(@PathVariable Long id,String content,Model model){

        commentrepository.save(new Comment(content,postrepository.getOne(id)));
        //현재 게시글을 의미하는 post object를 value로 model에 post: post object식으로 넣어줌
        model.addAttribute("post",postrepository.getOne(id));
        model.addAttribute("comments",commentrepository.findAllCommentforeachpost(id));
        //commentrepository.findAllCommentforeachpost(id) returns a list containing Comment objects whose post_id value is same as id value
        return "page/showcomment";

    }

    @GetMapping("/postdetail/{id}")
    public String show_detail(@PathVariable Long id,Model model,HttpSession session){

        model.addAttribute("post",postrepository.getOne(id));
        Posts post=postrepository.getOne(id);
        Object loginuser=session.getAttribute("sessioneduser");

        //로그인 안한 상태
        if(loginuser==null){
            model.addAttribute("comments",commentrepository.findAllCommentforeachpost(id));
            return "page/showdetail_notlogin";
        }

        //로그인 상태
        else{

            User User_Object=(User) loginuser;

            //로그인 한 상태에서 자신이 작성한 글 상세보기 눌렀을 경우
            if(post.getAuthor().getUserId().equals(User_Object.getUserId())){
                model.addAttribute("comments",commentrepository.findAllCommentforeachpost(id));
                return "page/showdetailself";
            }

            //로그인한상태에서 남에 글 상세보기 눌렀을경우
            else{
                model.addAttribute("comments",commentrepository.findAllCommentforeachpost(id));
                return "page/showdetail";
            }

        }

    }

    @GetMapping("/modifyform/{id}")
    public String show_updatepage(@PathVariable Long id,Model model){
        model.addAttribute("post",postrepository.getOne(id));
        return "page/modifypost";
    }

    @PutMapping("/updatepost/{id}")
    public String update_post(@PathVariable Long id,String title,String contents,Model model){

        //수정할때 제목이랑 게시글내용 둘다 채웠을 경우
        if((!title.equals("")) && (!contents.equals(""))){

            Posts post=postrepository.getOne(id);
            post.setTitle(title);
            post.setContent(contents);
            //updating the modified_date of the posts object representing the current post being updated
            post.setModifiedDate(LocalDate.now());
            postrepository.save(post);
            return "redirect:/";

        }

        model.addAttribute("post",postrepository.getOne(id));
        return "page/fillbothfields";


    }


}
