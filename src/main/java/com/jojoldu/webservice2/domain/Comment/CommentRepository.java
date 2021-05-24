package com.jojoldu.webservice2.domain.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Long>{

    @Query("SELECT c FROM Comment c where c.toppost= ?1")
    List<Comment> findAllCommentforeachpost(Long id);
    
}
