package com.jojoldu.webservice2.domain.Users;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)// @GeneratedValue(strategy=GenerationType.AUTO)와 같다. JPA 구현체가 아무 전략을 써서 primary-key값을 생성함
    private Long id;
    
    @Column(nullable=false,length=20,unique=true)
    private String userId;
    private String password;
    private String name;

    public void setUserId(String userId){
        this.userId = userId;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setName(String name){
        this.name = name;
    }


}
