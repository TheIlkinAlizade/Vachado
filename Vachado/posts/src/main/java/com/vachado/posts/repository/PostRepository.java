package com.vachado.posts.repository;

import com.vachado.posts.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByPostNumber (Long postNumber);

    @Transactional
    @Modifying
    void deleteByPostNumber(Long postNumber);

    Optional<List<Post>> findAllByAccountNumber (Long accountNumber);

    List<Post> findTop10ByOrderByCreatedAtDesc ();

}
