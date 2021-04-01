package com.jojoldu.webservice2.web;

import com.jojoldu.webservice2.domain.posts.Posts;
import com.jojoldu.webservice2.domain.posts.PostsRepository;
import com.jojoldu.webservice2.dto.posts.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class WebRestController{

    private PostsRepository postrepository;

    @PostMapping("/update/{content}")
    public String update_field(@PathVariable String content){

        Posts post=postrepository.findByContent(content);
        post.setContent("chicken");
        postrepository.save(post);
        return "content value of an entity is successfully updated";
    }

    @PostMapping("/update2/{id}")
    public void update_field2(@PathVariable long id){
        Posts post=postrepository.getOne(id);
        post.setContent("chicken");
        postrepository.save(post);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello!";
    }


    @PostMapping("/posts")
    public String save(@RequestBody PostsSaveRequestDto dto){
        postrepository.save(dto.To_Entity());
        return "successfully saved into Posts table";
    }



}
