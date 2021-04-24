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
    @GeneratedValue
    private Long id;

    @Column(length=300,nullable=false)
    private String title;

    @Column(columnDefinition="TEXT",nullable=false)
    private String content;

    @ManyToOne
    @JoinColumn(name="author_id",referencedColumnName="id")
    private User author;
    

    public Posts(String title,String content){
        this.title=title;
        this.content=content;
    }

}
