package com.jojoldu.webservice2.domain.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.stream.Stream;

public interface CommentRepository extends JpaRepository<Comment,Long>{

    @Query(value= "select c FROM Comment c where c.toppost= :id")
    Stream<Comment> findAllCommentforeachpost(Long id);
    
}
