package com.jojoldu.webservice2.domain.Comment;

import com.jojoldu.webservice2.domain.posts.Posts;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Comment{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
