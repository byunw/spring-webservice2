package com.jojoldu.webservice2.domain.Users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

//@Entity informs JPA that this class User and its objects should be persisted(meaning this class' objects should be stored inside database)
//b/c of @Entity a class named User will be mapped by default to a database table called user
//sometimes, @Table can be used along with @Entity to indicate which database table the corresponding class should be stored
@Entity
@Getter
@NoArgsConstructor
public class User{

    //@Id is JPA id annotation which designates field id as primary key in the corresponding database table in this case user
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//primary key auto-increment되고 순차적으로 해줌
    private Long id;

    @Column(nullable=false,length=20,unique=true)
    private String userId;
    private String password;
    private String password2;
    private String name;

    public void setUserId(String userId){
        this.userId = userId;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setPassword2(String password2){
        this.password2=password2;
    }

    public void setName(String name){
        this.name = name;
    }


}
