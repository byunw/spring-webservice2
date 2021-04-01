package com.jojoldu.webservice2.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts,Long>{

    Posts findByContent(String content);

}
