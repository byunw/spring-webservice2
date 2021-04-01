package com.jojoldu.webservice2.domain.posts;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.jojoldu.webservice2.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    @Column(columnDefinition = "TEXT",nullable=false)
    private String content;

    private String author;

    @Builder
    public Posts(String title,String content,String author){
        this.title=title;
        this.content=content;
        this.author=author;
    }

}
