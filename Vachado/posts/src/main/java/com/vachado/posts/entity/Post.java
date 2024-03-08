package com.vachado.posts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Post extends BaseEntity {

    @Id
    @Column(name = "post_number")
    private Long postNumber;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "likes")
    private Long like;

    @Column(name = "account_number")
    private Long accountNumber;

}