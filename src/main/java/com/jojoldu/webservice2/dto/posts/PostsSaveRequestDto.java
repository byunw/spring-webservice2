package com.jojoldu.webservice2.dto.posts;

import com.jojoldu.webservice2.domain.Users.User;
import com.jojoldu.webservice2.domain.posts.Posts;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class PostsSaveRequestDto{

    private String title;
    private String content;
    private User author;

    public Posts To_Entity(){

        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

    }


}
