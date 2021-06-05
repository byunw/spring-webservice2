package com.jojoldu.webservice2.domain.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

//repository정의해서 DB 접근
//repository는 Spring Data JPA의 핵심
//Spring Data JPA는 스프링이 제공하는 모듈중 1개
public interface CommentRepository extends JpaRepository<Comment,Long>{

        @Query("select c FROM Comment c where c.toppost.id = :id")
        List<Comment> findAllCommentforeachpost(@Param("id") Long id);

        //직접 쿼리작성
        @Query("delete c FROM Comment c where c.toppost.id= :id")
        void deleteassociatedcomment(@Param("id") Long id);

}
