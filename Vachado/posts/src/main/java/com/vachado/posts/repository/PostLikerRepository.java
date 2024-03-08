package com.vachado.posts.repository;

import com.vachado.posts.entity.PostLiker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikerRepository extends JpaRepository<PostLiker, Long> {

        boolean existsByPostIdAndAccountNumber(Long postId, Long accountNumber);

}
