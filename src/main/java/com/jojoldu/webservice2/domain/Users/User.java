package com.jojoldu.webservice2.domain.Users;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class User{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false,length=20,unique=true)
    private String userId;

    private String password;
    private String name;
    private String email;

    public void setUserId(String userId) {
        this.userId=userId;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    //this method is used to return the attribute values of an User object
    @Override
    public String toString() {
        return userId+" "+password+" "+name+" "+email;
    }

}
