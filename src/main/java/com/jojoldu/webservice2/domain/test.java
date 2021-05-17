package com.jojoldu.webservice2.domain;

import com.jojoldu.webservice2.domain.Users.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
public class test{

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    
    public test(String name){
        this.name=name;
    }


}

