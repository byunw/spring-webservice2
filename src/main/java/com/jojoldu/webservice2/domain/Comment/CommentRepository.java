package com.jojoldu.webservice2.domain.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long>{

        @Query(value="SELECT c FROM Comment c where c.post_id= :id")
        List<Comment> findAllCommentforeachpost(@Param("id") Long id);
        
}
