package com.jojoldu.webservice2.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity{
    
    //automatically creates date value for create_date column
    @CreatedDate
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDateTime createdDate;

    //automatically creates data value for modifiedDate column
    @LastModifiedDate
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDateTime modifiedDate;


}
