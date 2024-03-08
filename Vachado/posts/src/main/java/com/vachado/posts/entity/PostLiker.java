package com.vachado.posts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_likers",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"post_number", "account_number"})})
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class PostLiker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_number")
    private Long postId;

    @Column(name = "account_number")
    private Long accountNumber;

    public PostLiker(Long postId, Long accountNumber) {
        this.postId = postId;
        this.accountNumber = accountNumber;
    }
}
