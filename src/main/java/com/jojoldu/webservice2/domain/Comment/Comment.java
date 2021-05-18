package com.jojoldu.webservice2.domain.Comment;

import com.jojoldu.webservice2.domain.posts.Posts;

import javax.persistence.*;

@Entity
public class Comment{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String content;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Posts toppost;
    
    //constructor
    public Comment(String content,Posts toppost){
        this.content=content;
        this.toppost=toppost;
    }

}
