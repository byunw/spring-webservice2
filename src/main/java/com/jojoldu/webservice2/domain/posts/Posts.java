package com.jojoldu.webservice2.domain.posts;

import com.jojoldu.webservice2.domain.BaseTimeEntity;
import com.jojoldu.webservice2.domain.Users.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class Posts extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=300,nullable=false)
    private String title;

    @Column(columnDefinition="TEXT",nullable=false)
    private String content;

    @Column(name="comment_number")
    private int comment_number=0;
    
    //JPA annotation
    @ManyToOne
    @JoinColumn(name="author_id")
    private User author;


    //fields LocalDate createdDate and LocalDate modifiedDate exist b/c the current class
    //is inheriting BaseTimeEntity
    //private LocalDate createdDate;
    //private LocalDate modifiedDate;

    public Posts(String title, String content, User author){
        this.title=title;
        this.content=content;
        this.author=author;
    }

}
