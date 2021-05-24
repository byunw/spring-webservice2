package com.jojoldu.webservice2.domain.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long>{

    //문제는 line 11,12번째에있음
    @Query(value="SELECT c FROM Comment c where c.toppost= :id")
    List<Object[]> findAllCommentforeachpost(@Param("id") Long id);
    
}
